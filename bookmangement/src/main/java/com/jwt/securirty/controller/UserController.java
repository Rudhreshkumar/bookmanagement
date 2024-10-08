package com.jwt.securirty.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.jwt.securirty.dto.MessageDto;
import com.jwt.securirty.exception.InvalidIsbnException;
import com.jwt.securirty.model.Book;
import com.jwt.securirty.model.UserInfo;
import com.jwt.securirty.repo.UserRepository;
import com.jwt.securirty.service.BookService;

@RestController
 public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello, User!";
    }
 
    @PostMapping("/auth/signup")
    public void signup(@RequestBody UserInfo userInfo) {
    	userInfo.setRole("ROLE_USER"); 
    	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    	userRepository.save(userInfo);
    }
 

    @PostMapping("/user/addbook")
    public void addBook(@RequestBody Book book) {
    	bookService.addBook(book);
    }
    
    @GetMapping("/user/allbooks")
    public List<Book> allBooks(){
    	return bookService.allBooks();
    }
    
    @GetMapping("/user/getbook/{isbn}")
    public ResponseEntity<?>  getBookByIsbn(@PathVariable String isbn,MessageDto dto)
    {
    	try {
    		Book book = bookService.getBookByIsbn(isbn);
			return ResponseEntity.ok(book);
    		
		} catch (InvalidIsbnException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
			
		}
    }
    
    @PutMapping("/user/updatebook/{isbn}")
    public ResponseEntity<?> updateBookByIsbn(@PathVariable String isbn,@RequestBody Book book, MessageDto dto)
    {
    	try {
    		Book updatedBook = bookService.updateBookByIsbn(isbn,book);
    		return ResponseEntity.ok(updatedBook);
    		
			
		} catch (InvalidIsbnException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
    }
    
    @DeleteMapping("/user/deletebook/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@PathVariable String isbn,MessageDto dto)
    {
    	try {
			
    		bookService.deleteBookByIsbn(isbn);
    		dto.setMsg("book deleted successfully");
    		return ResponseEntity.ok(dto);
    		
    		
		} catch (InvalidIsbnException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
    }

    
}
