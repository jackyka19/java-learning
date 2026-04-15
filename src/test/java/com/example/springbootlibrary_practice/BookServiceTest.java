package com.example.springbootlibrary_practice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookServiceTest{

//    @Test
//    void contextLoads() {
//    }

    @Autowired
    private BookService bookService;

    @MockitoBean
    private Clock clock;

    @Test
    public void testCreateBookTime() {
//        Instant fixedInstant = Instant.parse("2026-04-12T12:00:00Z");
        Instant fixedInstant = OffsetDateTime.parse("2026-04-12T12:00:00+08:00").toInstant();

        Mockito.when(clock.instant()).thenReturn(fixedInstant);
//        Mockito.when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
        // 同步將 Mock Clock 的時區改為台北/澳門
        Mockito.when(clock.getZone()).thenReturn(ZoneId.of("Asia/Taipei"));

        Book book = new Book();
        book.setTitle("測試書");
        book.setAuthor("測試作者");
        book.setPrice(100);
        book.setImage_url("test.png");
        book.setPublished_date(Date.from(fixedInstant));

        Integer bookId = bookService.createBook(book);

        Book oldbook = bookService.getBookById(bookId);

        Date expectedDate = Date.from(fixedInstant);

        assertEquals(expectedDate.getTime(), oldbook.getCreated_date().getTime(), "存入的時間應該要等於模擬的固定時間");

        System.out.println("測試成功 存入的時間是: " + oldbook.getCreated_date() );

        assertTrue(bookId>0, "錯誤: 書籍更新後ID應該要大於0");

    }

}
