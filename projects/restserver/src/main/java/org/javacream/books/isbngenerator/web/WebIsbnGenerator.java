package org.javacream.books.isbngenerator.web;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class WebIsbnGenerator {
	@Autowired
	IsbnGenerator isbnGenerator;
	@Timed(value = "isbngenerator.next.time", description = "Time taken to create an isbn")
	@PostMapping(path = "isbn", produces = MediaType.TEXT_PLAIN_VALUE)
	public String nextIsbn() {
		return isbnGenerator.next();
	}
}