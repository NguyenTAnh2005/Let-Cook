package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassActivity extends AppCompatActivity {
    HelperFunction helperFunction;

    EditText etEmail, etNewPassword;
    Button btnBack, btnConfirm;
    ImageView ivShowHide;
    DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        etEmail = findViewById(R.id.forgotpass_str_2_value);
        etNewPassword = findViewById(R.id.forgotpass_str_4_value);
        ivShowHide = findViewById(R.id.forgotpass_str_4_value_eye);
        btnBack = findViewById(R.id.forgotpass_str_5);
        btnConfirm = findViewById(R.id.forgotpass_str_6);

        HelperFunction.hide_appear_eye(ivShowHide, etNewPassword);

        db = new DatabaseSQLite(ForgotPassActivity.this, "APP_TEACHING_COOK.sqlite", null, 1);

        Button sendPassBtn = findViewById(R.id.forgotpass_str_4); // Nút gửi mật khẩu
        EditText emailField = findViewById(R.id.forgotpass_str_2_value);

        sendPassBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                return;
            }

            String newPassword = generateRandomPassword();

            if (db.updatePasswordByEmail(email, newPassword)) {
                sendOTPToEmail(email, newPassword);
                Toast.makeText(this, "Mật khẩu mới đã được gửi đến email!", Toast.LENGTH_LONG).show();
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(etNewPassword.getText().toString().equals(newPassword.toString())){
                            // Lay ID user tu email
                            Cursor cursor=db.getCursorData("Select Id_user from Account where email='"+email+"';");
                            String id="";
                            while (cursor.moveToNext())
                            {
                                 id=cursor.getString(0);
                            }

                            SharedPreferences sharedPreferences = getSharedPreferences("login_pref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("tickLogIn", true);
                            editor.putString("email", email);
                            editor.putString("id_user",id);
                            editor.apply();

                            Intent intent=new Intent(ForgotPassActivity.this, MainActivity.class);
                            helperFunction.ToastMessage(ForgotPassActivity.this,"Đăng nhập thành công!");
                            startActivity(intent);
                            finish();
                        }
                        else {
                            helperFunction.ToastMessage(ForgotPassActivity.this,"Đăng nhập không thành công! Vui lòng kiểm tra lại mật khẩu!");
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Email không tồn tại trong hệ thống!", Toast.LENGTH_LONG).show();
            }
        });

        // dieu huong man hinh
        Button btn_back_inForgotPass=(Button) findViewById(R.id.forgotpass_str_5);
        btn_back_inForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // chua biet la se duoc mo tu dau login hay sign up nen cu finish tien trinh de back
            }
        });

        // dang nhap

    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return password.toString();
    }

    private void sendOTPToEmail(String toEmail, String newPass) {
        final String fromEmail = "23050084@student.bdu.edu.vn";
        final String fromPass = "jqmt fvrp brpe gjpv";
        final String subject = "Mật khẩu mới của bạn";
        final String body = "Mật khẩu mới của bạn là: " + newPass;

        new Thread(() -> {
            try {
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, fromPass);
                    }
                });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}