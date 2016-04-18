package com.jonathangama.dao;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */

import com.jonathangama.model.Book;

import java.util.ArrayList;

public class GlobalVariables {

    private int activeFragmentIndex=0;
    private ArrayList<Book> books;
    private String webServiceURL ="http://de-coding-test.s3.amazonaws.com/books.json";
    private String webServiceImageURL ="http://i.ebayimg.com/00/";
    private Book book;
    private int debug=0; //change to 1 here if you want to activate the toast and logs

    public static void setInstance(GlobalVariables instance) {
        GlobalVariables.instance = instance;
    }

  	private static GlobalVariables instance;

	public static GlobalVariables getInstance() {
		if (instance == null)
			instance = new GlobalVariables();
		return instance;
	}


    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getWebServiceURL() {
        return webServiceURL;
    }

    public String getWebServiceImageURL() {
        return webServiceImageURL;
    }

    public int getActiveFragmentIndex() {
        return activeFragmentIndex;
    }

    public void setActiveFragmentIndex(int activeFragmentIndex) {
        this.activeFragmentIndex = activeFragmentIndex;
    }

    public int getDebug() {
        return debug;
    }

}