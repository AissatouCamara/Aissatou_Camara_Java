package com.company.chatterbook.models;

public class ChatterPost {

    private String text; // user post

    //constructor that takes a user post as parameter
    public ChatterPost(String text) {
        this.text = text;
    }

    //getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
