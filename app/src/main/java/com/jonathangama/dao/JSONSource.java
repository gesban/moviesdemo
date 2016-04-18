package com.jonathangama.dao;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */

import android.util.Log;

import com.jonathangama.model.*;
import com.jonathangama.model.Error;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONSource {

    public JSONSource()
    {

    }

    public ArrayList<Book> getBooksFromJSON(String json)
    {
        ArrayList<Book> books;
        books = new ArrayList<Book>();

        try {
            JSONObject jsonObject;
            books.clear();

            JSONArray jsonArray = new JSONArray(json);

            for (Integer i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);


                Book book;
                book = new Book();

                String bookId = "";
                String bookTitle = "";
                String bookAuthor= "";
                String bookUrl= "";


                if(jsonObject.has("title"))
                {
                    bookTitle = jsonObject.getString("title");
                }

                if(jsonObject.has("author"))
                {
                    bookAuthor = jsonObject.getString("author");
                }

                if(jsonObject.has("imageURL"))
                {
                    bookUrl = jsonObject.getString("imageURL");
                }

                book.setBookId(bookId);
                book.setBookTitle(bookTitle);
                book.setBookAuthor(bookAuthor);
                book.setBookUrl(bookUrl);

                books.add(book);
            }
        } catch (JSONException e) {
            com.jonathangama.model.Error error;
            error = new Error();
            error.setCause(e.getCause().toString());
            error.setMessage(e.getMessage());
            Log.e("web service", error.toString());
        }

        return books;

    }

    public ArrayList<Error> getErrorsFromJSON(String json)
    {
        ArrayList<Error> errors;
        errors = new ArrayList<Error>();

        try {
            JSONObject jsonObject;
            errors.clear();

            JSONArray jsonArray = new JSONArray(json);

            for (Integer i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                Error error;
                error = new Error();

                String cause = "";
                String message = "";

                error.setCause(cause);
                error.setMessage(message);

                errors.add(error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return errors;

    }


}
