package com.company.chatterbook.models;

import java.util.List;
import java.util.Objects;

public class User {
    private String name; //username
    private List<ChatterPost> chatterPosts; //list of chatterPosts

    //constructor that takes in a username as parameter
    public User(String name) {
        this.name = name;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(chatterPosts, user.chatterPosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chatterPosts);
    }
}
