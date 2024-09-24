package com.jwt.securirty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.securirty.exception.InvalidIsbnException;
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

	public Book getBookByIsbn(String isbn) throws InvalidIsbnException {
		// TODO Auto-generated method stub
		Optional<Book> book=bookRepository.getBookByIsbn(isbn);
		if(book.isEmpty())
			throw new InvalidIsbnException("book with given isbn number not exists");
		return book.get();
	}

	public Book updateBookByIsbn(String isbn, Book book) throws InvalidIsbnException {
		// TODO Auto-generated method stub
		Optional<Book> oldBook=bookRepository.getBookByIsbn(isbn);
		if(oldBook.isEmpty())
			throw new InvalidIsbnException("book with given isbn number not exists");
		
		Book bookDB=oldBook.get();
		bookDB.setTitle(book.getTitle());
		bookDB.setAuthor(book.getAuthor());
		bookDB.setYear(book.getYear());
		
		return bookRepository.save(bookDB);
	
	}

	public void deleteBookByIsbn(String isbn) throws InvalidIsbnException {
		Optional<Book> book=bookRepository.getBookByIsbn(isbn);
		if(book.isEmpty())
			throw new InvalidIsbnException("book with given isbn number not exists");
		
		Book bookDB=book.get();
		
		bookRepository.delete(bookDB);
		
		
	}
	
	

}
