package com.jwt.securirty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.securirty.model.Book;
import com.jwt.securirty.repo.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

	public void addBook(Book book) {
		bookRepository.save(book);
		
	}

	public List<Book> allBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}
	
	

}
