package com.jonathangama.model;

/**
 * Created by Jonathan Gama on 4/11/2016.
 */

public class Error {
    private String cause;
    private String message;

    public Error()
    {
        this.setCause("");
        this.setMessage("");
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString()
    {
        return "cause +" + getCause() + " message=" + getMessage();
    }
}
