package com.example.learnapi.activity;


import static com.example.learnapi.setupgeneral.Setup_Text.RandomID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnapi.R;
import com.example.learnapi.api.ApiService;

import com.example.learnapi.module.Customer;
import com.example.learnapi.setupgeneral.Setup_Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {

    EditText edt_email, edt_name, edt_phone, edt_pass, edt_newpass;
    Button btn_register;
    TextView txt_anno_email, txt_anno_name,txt_anno_phone, txt_anno_pass;
    Setup_Text setup_text = new Setup_Text();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);

        SetComponent();

        Check_Register();


    }

    private void Check_Register() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void SetComponent() {
        edt_email = findViewById(R.id.edtemail_regiter);
        edt_name = findViewById(R.id.edtname_register);
        edt_phone = findViewById(R.id.edtphone_register);
        edt_pass = findViewById(R.id.edtpass_register);
        edt_newpass = findViewById(R.id.edtconfirmpass_register);
        btn_register = findViewById(R.id.btn_register);
        txt_anno_email = findViewById(R.id.txtcheck_email);
        txt_anno_name = findViewById(R.id.txtcheck_name);
        txt_anno_phone = findViewById(R.id.txtcheck_phone);
        txt_anno_pass = findViewById(R.id.txtcheck_pass);

    }

    public void TransToLogin(View view)
    {
        Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
        startActivity(intent);
    }
}