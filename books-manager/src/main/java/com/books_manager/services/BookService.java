package com.books_manager.services;

import com.books_manager.dto.BookDTO;

import java.util.List;

public interface  BookService {

    List<BookDTO> getAllbooks();

    BookDTO createBook(BookDTO bookDTO);

    int deleteBook(int id);

    BookDTO getBookById(int id);

    int updateBook(int id, String name, String author);
}
