package com.books_manager.services;

import com.books_manager.dto.BookDTO;
import com.books_manager.entities.Book;
import com.books_manager.mapper.BookMapper;
import com.books_manager.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    
    private final BookRepository bookRepository;


    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllbooks() {
        Optional<List<Book>> allBooks = bookRepository.findAllBooks();

        return allBooks.map(books -> books.stream().map(bookMapper::convertFromEntityToDto).collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.convertFromDtoToEntity(bookDTO);

        int savedBook = bookRepository.saveBook(book.getName());
        return savedBook > 1 ? bookMapper.convertFromEntityToDto(book) : null;
    }

    public int deleteBook(int id){
        return bookRepository.deleteBookById(id);
    }

    public BookDTO getBookById(int id) {
        return bookRepository.findBookById(id)
                .map(bookMapper::convertFromEntityToDto)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public int updateBook(int id, String name, String author) {
        return bookRepository.updateBook(id,name, author);
    }




    
}
