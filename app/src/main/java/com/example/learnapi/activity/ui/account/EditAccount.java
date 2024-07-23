package com.example.learnapi.activity.ui.account;

import static com.example.learnapi.controller.movie.HomePageController.IDUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.learnapi.R;
import com.example.learnapi.controller.AccoutController;
import com.example.learnapi.controller.account.EditController;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.databinding.ActivityDetailAccountBinding;
import com.example.learnapi.databinding.ActivityEditAccountBinding;
import com.example.learnapi.module.Customer;
import com.example.learnapi.setupgeneral.dbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditAccount extends baseActivity<EditController> {
    ActivityEditAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new EditController(this);
        binding.edtbirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        Intent intent = getIntent();
        Customer customer = (Customer) intent.getSerializableExtra("Customer");

        if (customer != null) {
            binding.tvemail.setText(customer.getEmail());
            binding.edtname.setText(customer.getUserName());
            binding.edtphone.setText(customer.getPhone());
            binding.edtgender.setText(customer.getGender());

            Date birthDate = customer.getAge();
            if (birthDate != null) {
                binding.edtbirthdate.setText(dbHelper.ConvertDate(birthDate));
            }
        }
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateAccount();
            }
        });
    }

    private void UpdateAccount() {
        String username = binding.edtname.getText().toString();
        String phone = binding.edtphone.getText().toString();
        String gender = binding.edtgender.getText().toString();

        // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
        Date birthday = null;
        String birthdayString = binding.edtbirthdate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
             birthday = dateFormat.parse(birthdayString);

        } catch (Exception err) {

        }


        Customer customer = new Customer(username, phone, birthday, gender);

        try {
            controller.UpdateAccount(IDUser, customer);
        } catch (Exception err) {
            showAlertDialog(err.getMessage());
        }
    }


    private void showDatePickerDialog() {
        // Lấy ngày hiện tại
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditAccount.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Cập nhật EditText với ngày được chọn
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        binding.edtbirthdate.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void IntentDetailUpdate() {
        Intent intent = new Intent(EditAccount.this,DetailAccount.class);
        startActivity(intent);
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
}