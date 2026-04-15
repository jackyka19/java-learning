package com.example.springbootlibrary_practice;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private String image_url;
    private int price;
    private Date published_date;
    private Date created_date;
    private Date last_modified_date;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

}
