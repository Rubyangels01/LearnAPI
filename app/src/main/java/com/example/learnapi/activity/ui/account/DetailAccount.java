package com.example.learnapi.activity.ui.account;

import static com.example.learnapi.controller.movie.HomePageController.IDUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learnapi.controller.account.DetailAccountController;
import com.example.learnapi.controller.account.EditController;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.databinding.ActivityDetailAccountBinding;
import com.example.learnapi.module.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailAccount extends baseActivity<DetailAccountController> {
    ActivityDetailAccountBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new DetailAccountController(this);
        sharedPreferences = getSharedPreferences("customer", Context.MODE_PRIVATE);
        setEvent();
    }

    private void setEvent() {
        try {

            controller.DisplayInfor(IDUser);
        }catch (Exception err)
        {
            showAlertDialog(err.getMessage());
        }

        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailAccount.this, EditAccount.class);
                intent.putExtra("Customer", Inforcustomer());
                startActivity(intent);
            }
        });
    }
    public  void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void displayInformation(Customer customer) {

        binding.edtemail.setText(customer.getEmail());
        binding.edtname.setText(customer.getUserName());
        binding.edtPhone.setText(customer.getPhone());

        if (customer.getAge() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = dateFormat.format(customer.getAge());
            binding.edtbirthdate.setText(formattedDate);
        }

        binding.edtgender.setText(customer.getGender());
    }
    public Customer Inforcustomer()
    {
        String username = binding.edtname.getText().toString();

        String email = binding.edtemail.getText().toString();
        String phone = binding.edtPhone.getText().toString();
        String gender = binding.edtgender.getText().toString();
        Date age = null;
        if (!binding.edtbirthdate.getText().toString().isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                age = dateFormat.parse(binding.edtbirthdate.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return new Customer(IDUser,username,email,phone,age,gender);
    }
    public Customer getCustomer() {
        String username = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String gender = sharedPreferences.getString("gender", "");
        String dateString = sharedPreferences.getString("date", "");
        Date age = null;
        if (!dateString.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                age = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int id = sharedPreferences.getInt("id", 0);

        return new Customer(id, username, email, phone, age, gender);
    }


}
