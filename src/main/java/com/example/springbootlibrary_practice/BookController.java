package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {

//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
        Book book = bookService.getBookById(bookId);

        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> setBook(@RequestBody Book book) {
        Integer bookId = bookService.createBook(book);

        Book oldbook = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(oldbook);
//        return ResponseEntity.status(200).body("Hello");

    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> putBook(@PathVariable Integer bookId, @RequestBody Book book){

        if (bookService.getBookById(bookId) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        bookService.updateBookById(bookId, book);

        Book oldbook = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(oldbook);

    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId){
        bookService.deleteBookById(bookId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
