package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jwt.securirty.exception.InvalidIsbnException;
import com.jwt.securirty.model.Book;
import com.jwt.securirty.service.BookService;


@SpringBootTest
class GetAllBooksTest {

	@Autowired
	BookService bookService;
	
	@Test
	public void allBooksTest()
	{
		int expNum=4;
		int actualNum=bookService.allBooks().size();
		
		assertEquals(expNum, actualNum);
	}
	
	
	@Test
	public void getBookByIsbnTest() throws InvalidIsbnException {
		
		String isbn="a1b2";
		Book book = bookService.getBookByIsbn(isbn);
		if(book!=null)
			assert(true);
				
	}
	
	@Test
	public void addBook() 
	{
		Book book = new Book();
		book.setTitle("new book");
		book.setAuthor("new author");
		book.setIsbn("a1b1");
		book.setYear(2006);
		try {
			bookService.addBook(book);
			assert(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
