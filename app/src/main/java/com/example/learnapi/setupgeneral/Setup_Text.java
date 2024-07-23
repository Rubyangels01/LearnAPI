package com.example.learnapi.setupgeneral;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.UUID;

public class Setup_Text {

    @SuppressLint("SetTextI18n")
    public boolean Check_Edt_Login(EditText email,EditText pass, TextView info_email)
    {
        String email1 = email.getText().toString().trim();
        String pass1 = pass.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.+[a-zA-Z]+";
        if (email1.isEmpty() || pass1.isEmpty()) {
            info_email.setText("Vui lòng không để trống email hoặc mật khẩu");
            return false;
        }
        else if (!email1.matches(emailPattern)) {
            info_email.setText("Email không hợp lệ");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean isValidPhoneNumber(String phoneNumber) {
        String cleanedPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        if (!cleanedPhoneNumber.matches("\\d+")) {
            return false;
        }
        return cleanedPhoneNumber.length() == 10 || cleanedPhoneNumber.length() == 11;
    }
    public boolean isNameValid(String name) {
        name = name.trim();
        if (name.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public boolean Check_Edit_Register(EditText edt_email,EditText edt_name, EditText edt_phone, EditText edt_pass, EditText confimpass,
                                       TextView anno_email, TextView anno_name, TextView anno_phone, TextView anno_pass)
    {
        String email = edt_email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.+[a-zA-Z]+";

        if(email.isEmpty())
        {

            anno_email.setText("Vui lòng điền email!");
            return false;
        }
        else if(!email.matches(emailPattern))
        {
            anno_email.setText("Email không hợp lệ!");
            return false;
        }
        else if(edt_name.getText().toString().trim().isEmpty())
        {
            anno_email.setVisibility(View.GONE);
            anno_name.setText("Vui lòng điền họ tên!");
            return false;
        }
        else if(!isNameValid(edt_name.getText().toString()))
        {
            anno_email.setVisibility(View.GONE);
            anno_name.setText("Tên không hợp lệ!");
            return false;
        }
        else if(edt_phone.getText().toString().trim().isEmpty())
        {
            anno_name.setVisibility(View.GONE);
            anno_phone.setText("Vui lòng điền số điện thoại!");
            return false;
        }
        else if(!isValidPhoneNumber(edt_phone.getText().toString()))
        {
            anno_name.setVisibility(View.GONE);
            anno_phone.setText("Số điện thoại không hợp lệ");
            return false;
        }
        else if(edt_pass.getText().toString().trim().isEmpty())
        {
            anno_phone.setVisibility(View.GONE);
            anno_pass.setText("Vui lòng điền mật khẩu!");
            return false;
        }
        else if(!edt_pass.getText().toString().equals(confimpass.getText().toString()))
        {
            anno_phone.setVisibility(View.GONE);
            anno_pass.setText("Mật khẩu không khớp!");
            return false;
        }

        else
        {
            anno_email.setVisibility(View.GONE);
            anno_name.setVisibility(View.GONE);
            anno_phone.setVisibility(View.GONE);
            anno_pass.setVisibility(View.GONE);

            return true;
        }
    }

    public static String RandomID() {
        UUID uuid = UUID.randomUUID();
        String randomID = uuid.toString().replaceAll("-", "");
        return randomID;
    }
    // Hàm random id ghế với điều kiện 2 chữ 3 số
    public static String RandomSeatId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomNum = numbers.charAt(random.nextInt(numbers.length()));
            sb.append(randomNum);
        }
        return sb.toString();
    }
}
