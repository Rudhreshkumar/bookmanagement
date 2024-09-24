package com.jwt.securirty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.securirty.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
