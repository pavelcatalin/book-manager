package com.books_manager.repositories;

import com.books_manager.entities.Book;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM books", nativeQuery = true)
    //Check optional
    Optional<List<Book>> findAllBooks();

    @Query(value = "SELECT * FROM books WHERE id = :id", nativeQuery = true)
     Optional<Book> findBookById(Integer id);

     @Modifying
     @Transactional
     @Query(value = "INSERT INTO books (name,author) VALUES (:name, :author)", nativeQuery = true)
     int saveBook(String name);

    
     @Modifying
     @Transactional
     @Query(value = "DELETE FROM books WHERE id = :id",nativeQuery = true)
     int deleteBookById(Integer id);

     @Modifying
     @Transactional
     @Query(value = "UPDATE books SET name = :name, author = :author WHERE id = :id",nativeQuery = true)
     int updateBook(Integer id, String name, String author);
}
