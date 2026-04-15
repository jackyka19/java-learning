package com.example.springbootlibrary_practice;

import java.util.Date;

public interface BookDaoInterface {

    public Book getBookById(Integer BookId);

    public Integer postBook(Book book, Date now);

    public void putBookById(Integer bookId, Book book, Date now);

    public void deleteBookById(Integer BookId);

}
