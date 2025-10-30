package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    MainActivity context;
    HelperFunction helperFunction;
    DatabaseSQLite databaseSQLite;
    EditText etEmail, etPassword;
    ImageView eyeIcon;
    Button btnLogin;
    boolean passwordHien = false;
    // su dung context de tan dung ham ConvertActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helperFunction = new HelperFunction();
        databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);
        //kiểm tra đã từng đăng nhập chưa
        SharedPreferences sharedPreferences = getSharedPreferences("login_pref", MODE_PRIVATE);


        boolean isLoggedIn = sharedPreferences.getBoolean("tickLogIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // dieu huong man hinh
        TextView signup_from_login=(TextView) findViewById(R.id.login_str_4_2);
        TextView forgotpass_from_login=(TextView) findViewById(R.id.login_str_5);
        //Sang Signup
        helperFunction.ConvertActivityHaveReturn(signup_from_login,LoginActivity.this,SignupActivity.class);
        //Chuyển tới ForgotPass
        helperFunction.ConvertActivityHaveReturn(forgotpass_from_login,LoginActivity.this,ForgotPassActivity.class);

        //Đăng nhập
        EditText etEmail = findViewById(R.id.login_str_2_value);
        EditText etPassword = findViewById(R.id.login_str_3_value);
        Button btnLogin = findViewById(R.id.login_btn);
        eyeIcon=findViewById(R.id.login_str_3_value_eye);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            boolean loginSuccess = databaseSQLite.checkLogin(email, password);
            String id=databaseSQLite.getIdUserFromEmail(email);

            if (loginSuccess) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                // Them du lieu cho SharePreferneces - Vjppro hon Bundle
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("tickLogIn", true);
                editor.putString("email", email);
                editor.putString("id_user",id);
                editor.apply();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        //Ẩn hiện pass
        helperFunction.hide_appear_eye(eyeIcon,etPassword);

        // Đăng nhập mà không có tài khoản
        TextView login_without_account= (TextView) findViewById(R.id.login_str_6);
        helperFunction.ConvertActivityNoReturn(login_without_account,LoginActivity.this, MainActivity.class);
    }
}