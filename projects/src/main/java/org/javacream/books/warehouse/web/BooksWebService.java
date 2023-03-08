package org.javacream.books.warehouse.web;

import java.util.HashMap;
import java.util.List;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/books")
@CrossOrigin
public class BooksWebService {
	@Autowired
	private BooksService booksService;


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findBooks() {
		try {
			return booksService.booksList();
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book findBookByIsbn(@PathVariable("isbn") String isbn) {
		try {
			return booksService.findBookByIsbn(isbn);
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "{title}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String newBook(@PathVariable("title") String title, @RequestParam(defaultValue = "9.99", value = "price") double price) {
		try {
			return booksService.newBook(title, price, new HashMap<String, Object>());
		} catch (BookException be) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping(path = "{isbn}")
	public void deleteBookByIsbn(@PathVariable("isbn") String isbn) {
		try {
			booksService.deleteBookByIsbn(isbn);
		} catch (BookException be) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	@PutMapping(path = "{isbn}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@PathVariable("isbn") String isbn, @RequestBody Book toUpdate) {
		try {
			Book book = booksService.findBookByIsbn(isbn);
			book.setTitle(toUpdate.getTitle());
			book.setPrice(toUpdate.getPrice());
			return booksService.updateBook(book);
		} catch (BookException be) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}