package com.example.learnapi.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnapi.R;
import com.example.learnapi.api.ApiService;
import com.example.learnapi.module.Customer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class useractivity extends AppCompatActivity {
    private TextView txtid;
    private TextView txtname;
    private TextView txtage;
    Button btninsert;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid = findViewById(R.id.id);
        txtname = findViewById(R.id.name);
        txtage = findViewById(R.id.age);
        Button btncall = findViewById(R.id.btncall);
        btninsert = findViewById(R.id.btninsert);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });

    }

    private void InsertData() {
        // Tạo một đối tượng Customer mới với dữ liệu cần thêm
        Customer customer = new Customer();
//        customer.setId(7);
//        customer.setAge(22);
//        customer.setName("Tina");
//        customer.setAddress("HCM");

        // Gọi phương thức addCustomer từ ApiService để gửi yêu cầu POST
//        ApiService.apiservice.addCustomer(customer).enqueue(new Callback<Customer>() {
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                if(response.isSuccessful()) {
//                    // Xử lý khi thêm dữ liệu thành công
//                    Customer addedCustomer = response.body();
//                    txtid.setText(addedCustomer.getId() + "");
//                    txtname.setText(addedCustomer.getName());
//                    txtage.setText(addedCustomer.getAge() + "");
//                } else {
//                    // Xử lý khi thêm dữ liệu không thành công
//                    Toast.makeText(useractivity.this, "Fail to add data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable throwable) {
//                // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu
//                // Ví dụ: Hiển thị thông báo lỗi cho người dùng
//            }
//        });
    }


//    public void clickAPI(final Callback<List<Customer>> callback) {
//        ApiService.apiservice.getData().enqueue(new Callback<List<Customer>>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
//                if (response.isSuccessful()) {
//                    List<Customer> dataList = response.body();
//                    if(dataList != null) {
//                        callback.onResponse(call, response);
//                    }
//                } else {
//                    Toast.makeText(useractivity.this, "Customer List is empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Customer>> call, Throwable throwable) {
//                Toast.makeText(useractivity.this,  "Fail to get customer list", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
