package org.javacream.books.warehouse.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBooksService implements BooksService {

	@Autowired
	private StoreService storeService;
	@Autowired
	private IsbnGenerator isbnGenerator;

	@Autowired
	@Qualifier("generatorsMap")
	private Map<Set<String>, Function<Map<String, Object>, Book>> generators;

	@Autowired
	private BooksRepository booksRepository;

	@Override
	public String newBook(String title, double price, Map<String, Object> options) throws BookException {
		String isbn = isbnGenerator.next();
		Function<Map<String, Object>, Book> generator = generators.get(options.keySet());
		Book book = generator.apply(options);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setPrice(price);
		booksRepository.save(book);
		return isbn;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Optional<Book> result = booksRepository.findById(isbn);
		if (!result.isPresent()) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		Book book = result.get();
		book.setAvailable(storeService.getStock("books", isbn) > 0);
		return book;
	}

	public Book updateBook(Book bookValue) throws BookException {
		if (bookValue.getPrice() <= 0) {
			throw new BookException(BookException.BookExceptionType.CONSTRAINT, "price <= 0");
		}
		if (! booksRepository.existsById(bookValue.getIsbn())) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, bookValue.getIsbn());
		}
		booksRepository.save(bookValue);
		return bookValue;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		try {
			booksRepository.deleteById(isbn);
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}

	}

	public Collection<Book> findAllBooks() {
		return booksRepository.findAll().stream().map((Book b) -> {
			b.setAvailable(storeService.getStock("books", b.getIsbn()) > 0);
			return b;
		}).collect(Collectors.toList());
	}

}
