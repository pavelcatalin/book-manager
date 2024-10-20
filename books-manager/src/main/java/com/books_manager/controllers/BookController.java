package com.books_manager.controllers;


import com.books_manager.dto.BookDTO;
import com.books_manager.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {


  private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBooks() {

        return ResponseEntity.ok(bookService.getAllbooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable int id) {


        try {
            BookDTO book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        }catch (Exception e){
            return (ResponseEntity<BookDTO>) ResponseEntity.notFound();
        }
    }

    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO book) {
        return (BookDTO) bookService.createBook(book);

    }

    @DeleteMapping("/{id}")
    public int deleteBook(@PathVariable int id){
       return  bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable int id, @RequestBody BookDTO book) {
        int rowsUpdated = bookService.updateBook(id, book.getName(), book.getAuthor());

    if (rowsUpdated > 0) {
        return ResponseEntity.ok("Book updated successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found");
    }
    }
}
