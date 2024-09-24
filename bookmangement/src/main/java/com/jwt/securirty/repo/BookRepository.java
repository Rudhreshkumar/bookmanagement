package com.jwt.securirty.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.securirty.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("Select book from Book book where isbn = ?1")
	Optional<Book> getBookByIsbn(String isbn);

}
