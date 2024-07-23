package com.example.learnapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.learnapi.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class test2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ImageView qrCodeImageView = findViewById(R.id.barcode);

        // Dữ liệu mã QR
        String qrData = "00020101021129370016Vietcombank123456789012345678901234567890123456789012345678901234567890000000500000000000000000000000000001002010100000003000003020200010212040000000000000000";

        // Tạo mã QR
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 400, 400);

            // Chuyển đổi BitMatrix thành Bitmap
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Đặt Bitmap vào ImageView
            qrCodeImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
