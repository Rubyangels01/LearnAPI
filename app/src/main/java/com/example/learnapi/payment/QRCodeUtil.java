package com.example.learnapi.payment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import android.graphics.Bitmap;
import android.graphics.Color;

public class QRCodeUtil {

    public static Bitmap generateQRCode(String data, int width, int height) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);
            int[] intArray = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    intArray[y * width + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(intArray, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
