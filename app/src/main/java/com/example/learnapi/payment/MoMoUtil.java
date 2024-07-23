package com.example.learnapi.payment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

public class MoMoUtil {

    public static void openMoMoApp(Context context) {
        // Kiểm tra xem ứng dụng MoMo có được cài đặt hay không
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.vnpay");

        if (intent != null) {
            // Mở ứng dụng MoMo
            context.startActivity(intent);
        } else {
            // Thông báo cho người dùng rằng ứng dụng MoMo không được cài đặt
            Toast.makeText(context, "MoMo app is not installed on your device.", Toast.LENGTH_LONG).show();
        }
    }



    public static void openMoMoWithUrl(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to open MoMo app.", Toast.LENGTH_LONG).show();
        }
    }
}

