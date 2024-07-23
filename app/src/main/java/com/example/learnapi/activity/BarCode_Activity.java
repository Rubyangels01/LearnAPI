package com.example.learnapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.learnapi.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarCode_Activity extends AppCompatActivity {
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
        imageView = findViewById(R.id.barcode);
        String customerId = "123"; // Thay đổi thành ID thực tế của khách hàng
        String customerName = "John Doe"; // Thay đổi thành tên thực tế của khách hàng

        // Tạo nội dung cho mã QR
        String qrContent = customerId + "|" + customerName;

        // Tạo mã QR từ nội dung
        Bitmap qrCode = generateQRCode(qrContent);

        // Hiển thị mã QR trong ImageView

        imageView.setImageBitmap(qrCode);
    }
    private Bitmap generateQRCode(String content) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            return barcodeEncoder.createBitmap(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}