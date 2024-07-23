package com.example.learnapi.activity;

import com.example.learnapi.controller.AccoutController;
import com.example.learnapi.controller.base.baseactivity.baseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;


import com.example.learnapi.R;
import com.example.learnapi.module.Account;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.User;
import com.example.learnapi.setupgeneral.Setup_Text;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class Login_Activity extends baseActivity<AccoutController> {

    private EditText edt_email, edt_password;
    private TextView txt_check_login;
    private Button btn_login;

    Setup_Text setup_text;
    SharedPreferences sharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        controller = new AccoutController(this);
        setup_text = new Setup_Text();
        sharedPreferences = getSharedPreferences("Customer", Context.MODE_PRIVATE);
        SetUpComponent();
        Set_Event();
        //CheckLogin();
    }

    private void Set_Event() {
        login();
    }

    public void CreateAccount(View view) {
        Intent intent_register = new Intent(Login_Activity.this, Register_Activity.class);
        startActivity(intent_register);
    }

    public void IntentHome()
    {
        Intent intenthome = new Intent(this, Home_Activity.class);
        startActivity(intenthome);
    }

    private void login() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                Customer customer = new Customer();
                customer.setEmail(email);
                customer.setPassWord(password);
                controller.checkUserLogin(customer);

            }
        });
    }
    public void saveAccount(Customer customer) {
        SharedPreferences sharedPreferences = getSharedPreferences("Customer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", customer.getIdCustomer());
        editor.putString("name",customer.getUserName());
        editor.putString("email",customer.getEmail());
        editor.putString("gender",customer.getGender());
        editor.putString("phone",customer.getPhone());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = dateFormat.format(customer.getAge());

        editor.putString("date", dateString);
        editor.apply();
    }

    public void GetMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void InforErr(String message)
    {
        txt_check_login.setText(message);
    }

    private void SetUpComponent() {
                edt_email = findViewById(R.id.edt_email_login);
                edt_password = findViewById(R.id.edt_pass_login);
                btn_login = findViewById(R.id.btn_login);
                txt_check_login = findViewById(R.id.txtchecklogin);
            }
}