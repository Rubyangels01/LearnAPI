package com.example.learnapi.module;

import com.google.gson.annotations.SerializedName;

public class PassWord {
    @SerializedName("CurrentPassword")
    private String currentPassword;

    @SerializedName("PassWord")
    private String passWord;

    @SerializedName("PasswordConfirm")
    private String passwordConfirm;

    public PassWord(String currentPassword, String passWord, String passwordConfirm) {
        this.currentPassword = currentPassword;
        this.passWord = passWord;
        this.passwordConfirm = passwordConfirm;
    }
}
