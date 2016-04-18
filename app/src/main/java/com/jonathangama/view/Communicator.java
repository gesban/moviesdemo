package com.jonathangama.view;

import com.jonathangama.model.Book;

/**
 * Created by Jonathan Gama on 4/11/2016.
 */
public interface Communicator {

    public void showBook(Book book);
    public void toast(String string);
}
