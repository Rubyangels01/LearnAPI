package com.example.learnapi.activity;
// Hôm nay đã tạo được mã code và scan mã và lấy thông tin mã code
// Nhiệm vụ: Vì ko thể hiển thị thông tin trên cùng 1 trang quét
// Nên phari tạo 1 trang để lấy thông tin đó (đây sẽ là chi tiết movie)
// Vậy khi nhân viên quét xong thì sẽ hiển thị trang thông tin chi tiết của khách hàng
// Sau đó xác nhận tạo ticket cho khách hàng
// 1 kh huỷ thì đựcc nhưng nhiều kh huỷ thì sẽ ảnh hưởng
// vì vậy khi kh đặt vé online thì bắt buộc phải thanh toán or cọc truóc tieefn

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnapi.R;
import com.google.zxing.Result;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ScanBarCode_Activity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {
    private static final String TAG = ScanBarCode_Activity.class.getSimpleName();

    private CaptureManager captureManager;
    private DecoratedBarcodeView barcodeView;
    TextView tvid, name;
    private boolean isScanned;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_bar_code);
        tvid = findViewById(R.id.id);
        name = findViewById(R.id.name);
        barcodeView = findViewById(R.id.barcode_scanner);
        isScanned = false;
        barcodeView.setTorchListener(this);

        captureManager = new CaptureManager(this, barcodeView);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
        barcodeView.decodeSingle(result -> {
            barcodeResult(result.getResult());
            isScanned = true; // Cập nhật cờ khi quét xong
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isScanned) {
            captureManager.decode();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onTorchOn() {
        Log.d(TAG, "Torch turned on");
    }

    @Override
    public void onTorchOff() {
        Log.d(TAG, "Torch turned off");
    }



    public void barcodeResult(Result result) {
        String qrContent = result.getText();

        // Tách chuỗi thành ID và tên khách hàng
        String[] parts = qrContent.split("\\|");
        if (parts.length == 2) {
            String customerId = parts[0];
            String customerName = parts[1];
            Toast.makeText(this, customerId + "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, customerName + "", Toast.LENGTH_SHORT).show();


        } else {
            // Xử lý khi chuỗi từ mã QR không hợp lệ
            Log.e("QRScanActivity", "Invalid QR code format");
        }
    }

}
