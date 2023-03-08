package org.javacream.books.warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.javacream.books.isbngenerator.RandomIsbnGenerator;
import org.javacream.store.MapStoreService;
import org.javacream.util.Ordering;

public class MapBooksService{

	private MapStoreService storeService;
	private RandomIsbnGenerator isbnGenerator;

	private Map<Set<String>, Function<Map<String, Object>, Book>> generators;

	private Map<String, Book> books;

	{
		books = new HashMap<String, Book>();
		isbnGenerator = new RandomIsbnGenerator("ISBN:", "de");
		storeService = new MapStoreService();
		for (int i = 1; i <= 10; i++) {
			Book book = new Book();
			book.setIsbn("ISBN" + i);
			book.setTitle("Title" + i);
			book.setPrice(9.99 * i);
			books.put(book.getIsbn(), book);
		}

		generators = new HashMap<>();
		HashSet<String> books = new HashSet<>();
		generators.put(books, (Map<String, Object> options) -> {
			Book book = new Book();
			return book;
		});

		HashSet<String> schoolBooks = new HashSet<>(books);
		schoolBooks.add("subject");
		schoolBooks.add("year");
		generators.put(schoolBooks, (Map<String, Object> options) -> {
			SchoolBook book = new SchoolBook();
			book.setSubject((String) options.get("subject"));
			book.setYear((Integer) options.get("year"));
			return book;
		});
		HashSet<String> specialistBooks = new HashSet<>(books);
		specialistBooks.add("topic");
		generators.put(specialistBooks, (Map<String, Object> options) -> {
			SpecialistBook book = new SpecialistBook();
			book.setTopic((String) options.get("topic"));
			return book;
		});

	}

	public String newBook(String title, double price, Map<String, Object> options) throws BookException {
		String isbn = isbnGenerator.next();
		Function<Map<String, Object>, Book> generator = generators.get(options.keySet());
		Book book = generator.apply(options);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setPrice(price);
		books.put(isbn, book);
		return isbn;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		int stock = storeService.getStock("books", isbn);
		result.setAvailable(stock > 0);
		return result;
	}

	public Book updateBook(Book bookDetailValue) throws BookException {
		if (bookDetailValue.getPrice() <= 0) {
			throw new BookException(BookException.BookExceptionType.CONSTRAINT, "price <= 0");
		}

		Book value = books.get(bookDetailValue.getIsbn());
		value.setTitle(bookDetailValue.getTitle());
		value.setPrice(bookDetailValue.getPrice());
		value.setAvailable(bookDetailValue.isAvailable());
		return value;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}

	public Collection<Book> findAllBooks() {
		return new ArrayList<Book>(books.values());
	}
	public  List<Book> findBooksByType(Class<? extends Book> type) {
		return findAllBooks().stream().filter((book) -> book.getClass().isAssignableFrom(type))
				.collect(Collectors.toList());
	}

	public  List<Book> findBooksByPriceRange(double minPrice, double maxPrice) {
		return findAllBooks().stream().filter((book) -> (book.getPrice() >= minPrice) && (book.getPrice() <= maxPrice))
				.sorted().collect(Collectors.toList());
	}

	public List<Book> findBooksByTitleCriterion(String expression) {
		return findAllBooks().stream().filter((book) -> book.getTitle().matches(expression))
				.collect(Collectors.toList());
	}

	public List<Book> booksList(Ordering ordering) {
		switch (ordering) {
		case ASCENDING: {
			return findAllBooks().stream().sorted((book1, book2) -> book1.getIsbn().compareTo(book2.getIsbn()))
					.collect(Collectors.toList());
		}
		case DESCENDING: {
			return findAllBooks().stream().sorted((book1, book2) -> book2.getIsbn().compareTo(book1.getIsbn()))
					.collect(Collectors.toList());
		}
		default: {
			return findAllBooks().stream().sorted((book1, book2) -> book1.getIsbn().compareTo(book2.getIsbn()))
					.collect(Collectors.toList());
		}
		}
	}

	public List<Book> booksList() {
		return booksList(Ordering.ASCENDING);
	}

}
