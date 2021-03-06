package com.example.bookservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookservice.exception.ApplicationException;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.Stock;
import com.example.bookservice.repositories.BookRepository;
import com.example.bookservice.repositories.StockRepository;


@Service
public class BookService {
	
    @Autowired
	private BookRepository bookRepository;
	    
	 @Autowired
	 private StockRepository stockRepository;
	    
	    public List<Book> findAllBooks() {
	    	
	    	List<Book> bookList = bookRepository.findAll();
	    	
	    	if(bookList == null || bookList.size() == 0) {
				throw new ApplicationException();
			}
	    	
	    	return bookList;
	    }

	    @Transactional
		public void insertBooks(Book book) {
			
		 bookRepository.save(book);
			
		}

		public List<Book> findBookByName(String name) {
			
			List<Book> bookList = bookRepository.findBookByBookName(name);
			
			if(bookList == null || bookList.size() == 0) {
				throw new ApplicationException("Book with name " + name + " is not found");
			}
			return bookList;
		}

		public Book findBookById(Integer id) {
			
			Book book = bookRepository.findBookByBookId(id);
			
			if(book == null) {
				throw new ApplicationException("Book with Id " + id + " is not found");
			}
			return book;
		}

		@Transactional
		public void updateBooks(Integer id, String type) {
			Stock stock = null;
			if(type != null && type != "") {
				stock = findBookById(id).getStock();
				if(type.equalsIgnoreCase("plus")) {
					stock.setAvailableCount(stock.getAvailableCount() + 1);
				}
				if(type.equalsIgnoreCase("minus")) {
					stock.setAvailableCount(stock.getAvailableCount() - 1);
				}
			}
			
			if(stock != null) {
				stockRepository.save(stock);
			}
		}

		@Transactional
		public void deleteBooks(Integer bookId) {
			
			Book book = bookRepository.findBookByBookId(bookId);
			
			if(book != null) {
				bookRepository.delete(book);
			}
			
		}

		@Transactional
		public void addBooks(Integer bookId, Integer amount) {
			
			Book book = bookRepository.findBookByBookId(bookId);
			
			if(book != null) {
				book.getStock().setTotalCount(book.getStock().getTotalCount() + amount);
				bookRepository.save(book);
			}
		}
	

}
