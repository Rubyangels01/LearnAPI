package com.example.learnapi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnapi.R;
import com.example.learnapi.activity.frugment.Frugment_Account;
import com.example.learnapi.activity.frugment.Frugment_Active;
import com.example.learnapi.activity.frugment.Frugment_Home;
import com.example.learnapi.activity.frugment.Frugment_Login;
import com.example.learnapi.activity.frugment.Frugment_Ordering;
import com.example.learnapi.activity.frugment.Frugment_ShowTime;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.HomePageController;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.PassWord;
import com.example.learnapi.module.Theater;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Home_Activity extends baseActivity<HomePageController> implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    TextView nameAccount;
    TextView emailAccount;
    private static final int FRAGMENT_HOME = 0;
    private  static final  int FRAGMENT_SHOWTIME = 1;
    private static final int FRAGMENT_ACTIVE = 2;
    private static final int FRAGMENT_ACCOUNT = 3;
    private static final int FRAGMENT_LOGIN = 4;
    private int currentFragment = FRAGMENT_HOME;
    NavigationView navigationView;
    public static int LOGINED = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        nameAccount = headerView.findViewById(R.id.nameaccount);
        emailAccount =headerView.findViewById(R.id.emailaccount);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new Frugment_Home());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        updateLoginMenuItem();

        controller = new HomePageController(this);
        Checklogin();

        controller.GetListMovieNow();


    }

    public void updateLoginMenuItem() {
        Menu menu = navigationView.getMenu();
        MenuItem loginMenuItem = menu.findItem(R.id.nav_login);
        MenuItem accountMenuItem = menu.findItem(R.id.nav_account);
        MenuItem activeMenuItem = menu.findItem(R.id.nav_active);
        if (LOGINED == 0)
        {
            loginMenuItem.setVisible(true);
            accountMenuItem.setVisible(false);
            activeMenuItem.setVisible(false);

        }
        else {loginMenuItem.setVisible(false);
        activeMenuItem.setVisible(true);
        accountMenuItem.setVisible(true);
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){

        if(currentFragment != FRAGMENT_HOME)
        {

            Frugment_Home frugmentHome = new Frugment_Home();
            replaceFragment(frugmentHome);
            controller.GetListMovieNow();
            currentFragment = FRAGMENT_HOME;
        }
        }else if(id == R.id.nav_showtime)
        {
            replaceFragment(new Frugment_ShowTime());
            controller.GetListTheater();
            currentFragment = FRAGMENT_SHOWTIME;
        }else if(id == R.id.nav_active)
        {
            replaceFragment(new Frugment_Active());
            controller.GetBillofUser(HomePageController.IDUser);
           // controller.GetBillCancelofUser(HomePageController.IDUser);
            currentFragment = FRAGMENT_ACTIVE;
        }else if(id == R.id.nav_account)
        {
            replaceFragment(new Frugment_Account());
            currentFragment = FRAGMENT_ACCOUNT;

        }
        else if(id == R.id.nav_login)
        {
            replaceFragment(new Frugment_Login());
            currentFragment = FRAGMENT_LOGIN;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    public void navigateToHomePage(ArrayList<Movie> movies) {
        Fragment fragment = new Frugment_Home();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("movieList", movies);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null); // Optional: Add to back stack for navigation history
        transaction.commit();
    }



    public void navigateToShowTimePage(ArrayList<Theater> theaters) {
        // Create a new instance of your Fragment
        Fragment fragment = new Frugment_ShowTime();

        // Pass data to the Fragment through arguments (Bundle)
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("theaterList", theaters);
        fragment.setArguments(bundle);

        // Replace the current Fragment with the new Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null); // Optional: Add to back stack for navigation history
        transaction.commit();
    }
    public void navigateToOrderingPage(ArrayList<Bill> bills) {
        Fragment fragment = new Frugment_Active();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("billList", bills);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null); // Optional: Add to back stack for navigation history
        transaction.commit();
    }
    public void navigateToOrderCancelPage(ArrayList<Bill> bills) {
        Fragment fragment = new Frugment_Active();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("billList", bills);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null); // Optional: Add to back stack for navigation history
        transaction.commit();
    }





    public void getErr(String string)
    {
        Toast.makeText(this, string + "", Toast.LENGTH_SHORT).show();
    }

    public Customer getCustomer() {
        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Customer", Context.MODE_PRIVATE);

        // Đọc các thông tin từ SharedPreferences
        String username = sharedPreferences.getString("name", ""); // "" là giá trị mặc định nếu không tìm thấy
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String gender = sharedPreferences.getString("gender", "");

        // Đọc ngày tháng dưới dạng chuỗi từ SharedPreferences
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

        // Tạo đối tượng Customer từ thông tin đã đọc
        Customer customer = new Customer(id, username, email, phone, age, gender);
        return customer;
    }

    public void Checklogin()
    {

        if(LOGINED == 0)
        {
            nameAccount.setVisibility(View.GONE);
            emailAccount.setVisibility(View.GONE);
        }else
        {
            nameAccount.setText(getCustomer().getUserName());
            emailAccount.setText(getCustomer().getEmail());
        }


    }
    public void saveAccount(Customer customer) {
        SharedPreferences sharedPreferences = getSharedPreferences("Customer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", customer.getIdCustomer());
        editor.putString("name", customer.getUserName());
        editor.putString("email", customer.getEmail());
        editor.putString("gender", customer.getGender());
        editor.putString("phone", customer.getPhone());

        Date birthDate = customer.getAge();
        if (birthDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateString = dateFormat.format(birthDate);
            editor.putString("date", dateString);
        } else {
            editor.remove("date"); // Xóa đi nếu không có ngày sinh
        }

        editor.apply();
    }


    public void handleLogin(String email, String password) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassWord(password);
        controller.checkUserLogin(customer);

    }
    public void IntentHome()
    {
        Intent intent = new Intent(this, Home_Activity.class); // Hoặc `Home_Activity` nếu bạn muốn quay lại `Home_Activity`
        startActivity(intent);
    }

    public void handleChangePass(int IDUser, String currentPassword, String newPassword, String retypeNewPassword) {
        PassWord passWord = new PassWord(currentPassword,newPassword,retypeNewPassword);

        controller.ChangePassWord(IDUser,passWord);
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