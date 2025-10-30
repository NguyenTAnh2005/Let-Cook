package com.example.let__cook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotLogin extends AppCompatActivity {

    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login);

        Button btn_login=(Button) findViewById(R.id.not_login_str_2);
        Button btn_cancel_login=(Button) findViewById(R.id.not_login_str_3);

        helperFunction.ConvertActivityNoReturn(btn_login,NotLogin.this,LoginActivity.class);
        helperFunction.ConvertActivityNoReturn(btn_cancel_login,NotLogin.this,MainActivity.class);
    }
}