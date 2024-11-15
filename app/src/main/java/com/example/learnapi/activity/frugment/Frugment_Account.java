package com.example.learnapi.activity.frugment;

import static com.example.learnapi.adapter.DateAdapter.selectedDate;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHeader;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;
import static com.example.learnapi.controller.movie.HomePageController.IDUser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.activity.ChooseChair;
import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.activity.TestActivity;
import com.example.learnapi.activity.ui.account.DetailAccount;


public class Frugment_Account extends Fragment {
    TextView detailaccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);

        TextView changePasswordTextView = view.findViewById(R.id.changepassword);
        setComponent(view);

        changePasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePasswordDialog();
            }
        });
        Button btnlogout = view.findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogYesNo("Do you sure you want to exit?");
            }
        });

        return view;
    }

    private void setComponent(View view) {
        detailaccount = view.findViewById(R.id.tvdetail);
        detailaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập trước khi chuyển sang DetailAccount
                if (Home_Activity.LOGINED == 1) {
                    Intent intent = new Intent(requireContext(), DetailAccount.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(requireContext(), Frugment_Login.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showChangePasswordDialog() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogchangepass, null);

        EditText etCurrentPassword = dialogView.findViewById(R.id.etCurrentPassword);
        EditText etNewPassword = dialogView.findViewById(R.id.etNewPassword);
        EditText etRetypeNewPassword = dialogView.findViewById(R.id.etRetypeNewPassword);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(dialogView)
                .setTitle("Change Password")
                .setPositiveButton("Change Password", (dialog, which) -> {
                    // Xử lý logic thay đổi mật khẩu ở đây
                    String currentPassword = etCurrentPassword.getText().toString();
                    String newPassword = etNewPassword.getText().toString();
                    String retypeNewPassword = etRetypeNewPassword.getText().toString();
                    Home_Activity activity = (Home_Activity) getActivity();
                    if (activity != null) {
                        activity.handleChangePass(IDUser,currentPassword,newPassword,retypeNewPassword);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    // Phương thức kiểm tra trạng thái đăng nhập
    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }
    public void showAlertDialogYesNo(String message) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Home_Activity activity = (Home_Activity) getActivity();
                if (activity != null) {
                    Home_Activity.LOGINED = 0;
                    activity.updateLoginMenuItem();
                    activity.Checklogin();
                    activity.IntentHome();
                }

            }
        });

        // Nút Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        // Tạo và hiển thị hộp thoại
        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}
