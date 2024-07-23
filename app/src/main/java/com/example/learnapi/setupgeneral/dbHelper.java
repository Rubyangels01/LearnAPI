package com.example.learnapi.setupgeneral;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class dbHelper {
    public static String PORT = "192.168.1.8";
    public static <T> ArrayList<T> convertToObject(ResData resData, Class<T> clazz) {
        List<Object> dataList = (List<Object>) resData.getData(); // Assumed data is a list of objects
        ArrayList<T> listT = new ArrayList<>();
        Gson gson = new Gson();
        if (dataList != null) {
            for (Object obj : dataList) {
                try {
                    LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) obj; // Cast each object to LinkedTreeMap
                    T object = gson.fromJson(gson.toJsonTree(map), clazz); // Convert LinkedTreeMap to object using Gson
                    listT.add(object);
                } catch (ClassCastException | IllegalStateException e) {
                    e.printStackTrace();
                    // Handle the exception or log it as needed
                }
            }
        }
        return listT;
    }

    public static  <T> T convertObject(ResData resData, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(resData.getData()), clazz);
    }
    public static String ConvertStringToDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        String formattedDate = null;
        try {
            // Chuyển đổi chuỗi ngày đầu vào thành đối tượng Date
            date = inputFormat.parse(dateStr);
            // Định dạng đối tượng Date thành chuỗi ngày theo định dạng yyyy-MM-dd
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String ConvertDate(Date birthDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String birthDateString = dateFormat.format(birthDate);
        return birthDateString;
    }



    public static String ConvertDateToDay(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);


        try {
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                return outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Invalid Date"; // Trả về giá trị mặc định nếu có lỗi
    }
    public static String Datetoday(String dateStr)
    {

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Định dạng của chuỗi kết quả đầu ra chỉ lấy ngày
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateStr);
            String day = outputFormat.format(date);
            return day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static String convertStringToDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Chuyển đổi chuỗi sang đối tượng Date
            Date date = dateFormat.parse(dateStr);
            // Chuyển đổi đối tượng Date ngược lại thành chuỗi
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // hoặc bạn có thể ném ra một ngoại lệ tùy vào nhu cầu
        }
    }
    public static String formatDate(String dateString) {

        try {
            // Định dạng đầu vào
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = inputFormat.parse(dateString);

            // Định dạng đầu ra
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd 'tháng' MM, yyyy HH:mm");
            String formattedDate = outputFormat.format(date);

           return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String ConvertDateInSql(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateStr);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> ArrayList<T> parseJsonArray(JsonObject jsonObject, String key, Class<T> clazz) {
        Gson gson = new Gson();
        JsonArray jsonArray = jsonObject.getAsJsonArray(key);
        Type type = TypeToken.getParameterized(ArrayList.class, clazz).getType();
        return gson.fromJson(jsonArray, type);
    }
    public static boolean isFridayOrSaturday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // Trong Calendar, Chủ Nhật là 1, Thứ Hai là 2, ... Thứ Bảy là 7
            return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String convertDateToDetaildate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd 'tháng' MM, yyyy", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDate);
            if (date != null) {
                return outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }






}
