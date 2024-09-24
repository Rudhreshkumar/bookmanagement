package com.jwt.securirty.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    
}
