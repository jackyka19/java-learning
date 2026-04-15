package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.Date;

@Component
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private Clock clock;

    public Book getBookById(Integer bookId){
        return bookDao.getBookById(bookId);
    }

    public Integer createBook(Book book){
        Date now = Date.from(clock.instant());
        return bookDao.postBook(book, now);
//        return bookDao.postBook(book);
    }

    public void updateBookById(Integer bookId, Book book){
//        if (bookDao.getBookById(bookId) == null) {
//            return ;
//        }

        Date now = Date.from(clock.instant());

        bookDao.putBookById(bookId, book, now);
    }

    public void deleteBookById(Integer bookId){
        bookDao.deleteBookById(bookId);
    }

}
