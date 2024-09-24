package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jwt.securirty.exception.InvalidIsbnException;
import com.jwt.securirty.model.Book;
import com.jwt.securirty.service.BookService;

@SpringBootTest
class UpdateBookTest {
	
	@Autowired
	BookService bookService;

	@Test
	public void updateBookTest()
	{
		String isbn="a1b1";
		try {
			Book book =new Book();
			book.setTitle("Adventures of Tom Sawyer");
			book.setAuthor("Mark Twain");
			book.setYear(2006);
			bookService.updateBookByIsbn(isbn, book);
			assert(true);
			
		} catch (InvalidIsbnException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			assertFalse(true);
		}
	}
	
}
