package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookDao implements BookDaoInterface{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Book getBookById(Integer bookId){
        String sql = "SELECT book_id, title, author, image_url, price, published_date, created_date, last_modified_date " +
                "FROM oldbook WHERE book_id = :bookId";

        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);
        List<Book> bookList = namedParameterJdbcTemplate.query(sql, map, new BookRowMapper());

        if (bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Integer postBook(Book book, Date now){
        String sql = "INSERT INTO oldbook(title, author, image_url, price, published_date, created_date, last_modified_date) " +
                 "VALUES (:title, :author, :image_url, :price, :published_date, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor());
        map.put("image_url", book.getImage_url());
        map.put("price", book.getPrice());
        map.put("published_date", book.getPublished_date());
        Date date = new Date();
        map.put("created_date", date);
        map.put("last_modified_date", date);

        KeyHolder keyholder = new GeneratedKeyHolder();

//        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyholder);

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("author", book.getAuthor())
                        .addValue("image_url",book.getImage_url())
                        .addValue("price", book.getPrice())
                        .addValue("published_date", book.getPublished_date())
                        .addValue("created_date", now)
                        .addValue("last_modified_date", now), keyholder);
//                        .addValue("created_date", date)
//                        .addValue("last_modified_date", date), keyholder);

        Integer bookId = keyholder.getKey().intValue();

        return bookId;
    }

    @Override
    public void putBookById(Integer bookId, Book book, Date now){
        String sql = "UPDATE oldbook " +
                "SET title = :title, author = :author, image_url = :image_url, price = :price, published_date = :published_date, last_modified_date = :last_modified_date " +
                "WHERE book_id = :book_id";

        Map<String, Object> map = new HashMap<>();
        map.put("book_id", bookId);
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor());
        map.put("image_url", book.getImage_url());
        map.put("price", book.getPrice());
        map.put("published_date", book.getPublished_date());

//        Date date = new Date();
//        map.put("last_modified_date", date);
        map.put("last_modified_date", now);

        namedParameterJdbcTemplate.update(sql, map);

//        return getBookById(bookId);
    }

    @Override
    public void deleteBookById(Integer bookId){
        String sql = "DELETE FROM oldbook WHERE book_id = :book_id";

        Map<String, Object> map = new HashMap<>();
        map.put("book_id", bookId);

        namedParameterJdbcTemplate.update(sql,map);
    }
}
