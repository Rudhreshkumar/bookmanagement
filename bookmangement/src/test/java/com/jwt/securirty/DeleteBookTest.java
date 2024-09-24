package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jwt.securirty.exception.InvalidIsbnException;
import com.jwt.securirty.service.BookService;

@SpringBootTest
class DeleteBookTest {
	
	
	@Autowired
	BookService bookService;

	@Test
	public void deleteByIsbnTest()
	{
		String isbn="a1b6";
		try {
			bookService.deleteBookByIsbn(isbn);
			assert(true);
			
		} catch (InvalidIsbnException e) {
			System.out.println(e.getMessage());
			assertFalse(true);
		}
		
	}

}
