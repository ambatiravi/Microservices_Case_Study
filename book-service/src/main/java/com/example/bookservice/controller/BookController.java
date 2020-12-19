package com.example.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;



@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookservice;		
	
	@GetMapping("/allbooks")
	public List<Book> getAllBooks() {
		return bookservice.findAllBooks();
	}
	
	@GetMapping("/getbook/id/{id}")
	public Book getBookById(@PathVariable Integer id) {
		return bookservice.findBookById(id);
	}
	
	@GetMapping("/getbook/name/{name}")
	public List<Book> getBookByName(@PathVariable String name) {
		return bookservice.findBookByName(name);
	}
	
	@PostMapping("/insert")
	public void saveBook(@RequestBody Book book) {
		bookservice.insertBooks(book);
	}
	

	@PostMapping("/addstock")
	public void addStock(@RequestParam(required = true) Integer bookId, @RequestParam(required = true) Integer amount) {
		bookservice.addBooks(bookId, amount);
	}
}
