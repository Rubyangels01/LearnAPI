package com.example.learnapi.activity;


import static com.example.learnapi.setupgeneral.Setup_Text.RandomID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnapi.R;
import com.example.learnapi.api.ApiService;

import com.example.learnapi.controller.account.EditController;
import com.example.learnapi.controller.account.RegisterController;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.databinding.ActivityEditAccountBinding;
import com.example.learnapi.databinding.ActivityRegisterLayoutBinding;
import com.example.learnapi.module.Customer;
import com.example.learnapi.setupgeneral.Setup_Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends baseActivity<RegisterController> {

    EditText edt_email, edt_name, edt_phone, edt_pass, edt_newpass;
    Button btn_register;
    TextView txt_anno_email, txt_anno_name,txt_anno_phone, txt_anno_pass;
    Setup_Text setup_text = new Setup_Text();
    ActivityRegisterLayoutBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Check_Register();


    }

    private void Check_Register() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Setup_Text.Check_Edit_Register(binding.edtemailRegiter, binding.edtnameRegister, binding.edtphoneRegister
                        , binding.edtpassRegister, binding.edtconfirmpassRegister,
                        binding.txtcheckEmail, binding.txtcheckName, binding.txtcheckPhone, binding.txtcheckPass))
                {
                    Customer customer = new Customer();
                    customer.setEmail(binding.edtemailRegiter.getText().toString());
                    customer.setPassWord(binding.edtpassRegister.getText().toString());
                    customer.setUserName(binding.edtnameRegister.getText().toString());
                    customer.setPhone(binding.edtphoneRegister.getText().toString());

                     controller.RegisterAccount(customer);
                }

            }
        });
    }


    public  void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void TransToLogin()
    {
        Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
        startActivity(intent);
    }
}