package com.example.learnapi.activity.frugment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.activity.Home_Activity;

public class Frugment_Login extends Fragment {
    EditText tvemail;
    EditText tvpass;
    Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);

        tvemail = view.findViewById(R.id.edt_email_login);
        tvpass = view.findViewById(R.id.edt_pass_login);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvemail.getText().toString();
                String password = tvpass.getText().toString();
                Home_Activity activity = (Home_Activity) getActivity();
                if (activity != null) {
                    activity.handleLogin(email, password);
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

}
