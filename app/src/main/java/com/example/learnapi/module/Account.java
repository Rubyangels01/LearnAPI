package com.example.learnapi.module;

public class Account {
    private String email;
    private String password;

    public Account() {

    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
