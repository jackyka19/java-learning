package com.example.springbootlibrary_practice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setImage_url(rs.getString("image_url"));
        book.setPrice(rs.getInt("price"));
        book.setPublished_date(rs.getTimestamp("published_date"));
        book.setCreated_date(rs.getTimestamp("created_date"));
        book.setLast_modified_date(rs.getTimestamp("last_modified_date"));



        return book;
    }
}
