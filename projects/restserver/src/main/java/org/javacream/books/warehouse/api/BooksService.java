package org.javacream.books.warehouse.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javacream.util.Ordering;

public interface BooksService {

	String newBook(String title, double price, Map<String, Object> options) throws BookException;

	Book findBookByIsbn(String isbn) throws BookException;

	Book updateBook(Book bookDetailValue) throws BookException;

	void deleteBookByIsbn(String isbn) throws BookException;

	Collection<Book> findAllBooks();

	default List<Book> findBooksByType(Class<? extends Book> type) {
		return findAllBooks().stream().filter((book) -> book.getClass().isAssignableFrom(type))
				.collect(Collectors.toList());
	}

	default List<Book> findBooksByPriceRange(double minPrice, double maxPrice) {
		return findAllBooks().stream().filter((book) -> (book.getPrice() >= minPrice) && (book.getPrice() <= maxPrice))
				.sorted().collect(Collectors.toList());
	}

	default List<Book> findBooksByTitleCriterion(String expression) {
		return findAllBooks().stream().filter((book) -> book.getTitle().matches(expression))
				.collect(Collectors.toList());
	}

	default List<Book> booksList(Ordering ordering) {
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

	default List<Book> booksList() {
		return booksList(Ordering.ASCENDING);
	}


}