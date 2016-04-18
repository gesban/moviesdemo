package com.jonathangama.model;

import android.graphics.Bitmap;

/**
 * Created by Jonathan Gama on 4/11/2016.
 */
public class Book {
    private String  bookId;
    private String  bookTitle;
    private String  bookUrl;
    private String  bookAuthor;
    private Integer bookSelected;
    private Bitmap  bookImage;


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookSelected() {
        return bookSelected;
    }

    public void setBookSelected(Integer bookSelected) {
        this.bookSelected = bookSelected;
    }

    public Bitmap getBookImage() {
        return bookImage;
    }

    public void setBookImage(Bitmap bookImage) {
        this.bookImage = bookImage;
    }
}
