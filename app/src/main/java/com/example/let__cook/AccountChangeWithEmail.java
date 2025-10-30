package com.example.let__cook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import com.example.let__cook.DatabaseSQLite;
import com.example.let__cook.EmailSender;

public class AccountChangeWithEmail extends AppCompatActivity {
    private String generatedOTP;
    private String userEmail;
    private String userID;
    private DatabaseSQLite database;
    private Context context;
    private String newEmail;
    EditText otpField;
    HelperFunction helperFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_with_email);

        context = this;
        database = new DatabaseSQLite(AccountChangeWithEmail.this, "APP_TEACHING_COOK.sqlite", null, 1);

        // Lấy email và userID từ SharedPreferences
        SharedPreferences prefs = getSharedPreferences("login_pref", MODE_PRIVATE);
        userEmail = prefs.getString("email", null);
        userID = prefs.getString("id_user", null);

        // Lấy email mới từ Intent
        newEmail = getIntent().getStringExtra("newEmail");

        generatedOTP = generateOTP();

        Button btnSend_otp = findViewById(R.id.nav5_act3_email_str_3);
        Button btnConfirm = findViewById(R.id.nav5_act3_email_str_5);

        btnSend_otp.setOnClickListener(v -> {
            otpField = findViewById(R.id.nav5_act3_email_str_3_value);
            EmailSender.sendEmail(context, userEmail, "Mã OTP xác nhận",
                    "Mã OTP của bạn là: " + generatedOTP);
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = otpField.getText().toString().trim();
                if (!enteredOTP.equals(generatedOTP)) {
                    helperFunction.ToastMessage(AccountChangeWithEmail.this,"OTP không đúng!");
                    return;
                }
                database.updateEmailById(userID, newEmail);
                database.QuerySQL("UPDATE ACCOUNT SET EMAIL ='"+newEmail+"' WHERE id_user='"+userID+"';");
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", newEmail);
                editor.apply();
                helperFunction.ToastMessage(AccountChangeWithEmail.this,"Đổi email thanh công!");
                finish();
            }
        });

        Button btn_Cancel=findViewById(R.id.nav5_act3_email_str_4);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //otp
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}

//database = new DatabaseSQLite(AccountChangeWithEmail.this, "APP_TEACHING_COOK.sqlite", null, 1);
