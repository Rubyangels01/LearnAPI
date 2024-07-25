package com.example.learnapi.activity.frugment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.activity.Login_Activity;
import com.example.learnapi.activity.Register_Activity;

public class Frugment_Login extends Fragment {
    EditText tvemail;
    EditText tvpass;
    Button btnLogin;
    TextView tvregister;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        progressBar = view.findViewById(R.id.progressBar);
        tvemail = view.findViewById(R.id.edt_email_login);
        tvpass = view.findViewById(R.id.edt_pass_login);
        btnLogin = view.findViewById(R.id.btn_login);
        tvregister = view.findViewById(R.id.tvregister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                // Sử dụng Handler để ẩn ProgressBar sau 2 giây (2000 milliseconds)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 3000);
                String email = tvemail.getText().toString();
                String password = tvpass.getText().toString();
                Home_Activity activity = (Home_Activity) getActivity();
                if (activity != null) {
                    activity.handleLogin(email, password);
                }
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getContext();
                if (context != null) {
                    Intent intent_register = new Intent(context, Register_Activity.class);
                    startActivity(intent_register);
                }
            }
        });

        return view;
    }
    public void intentMain() {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, Home_Activity.class);
            startActivity(intent);
        }
    }
    private void CreateAccount(View view) {
        Context context = getContext();
        if (context != null) {
            Intent intent_register = new Intent(context, Register_Activity.class);
            startActivity(intent_register);
        }
    }

}
