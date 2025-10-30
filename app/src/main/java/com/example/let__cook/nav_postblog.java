package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nav_postblog extends AppCompatActivity {
    MainActivity context;
    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_postblog);
        helperFunction.checkedLogin(nav_postblog.this);

        // chuyen lien ket dieu huong cung cap
        LinearLayout nav_1=(LinearLayout) findViewById(R.id.nav_1);
        LinearLayout nav_2=(LinearLayout) findViewById(R.id.nav_2);
        LinearLayout nav_4=(LinearLayout) findViewById(R.id.nav_4);
        LinearLayout nav_5=(LinearLayout) findViewById(R.id.nav_5);
        LinearLayout nav_6=(LinearLayout) findViewById(R.id.nav_6);

        helperFunction.ConvertActivityNoReturn(nav_1,nav_postblog.this, MainActivity.class);
        helperFunction.ConvertActivityNoReturn(nav_2,nav_postblog.this, nav_food.class);
        helperFunction.ConvertActivityNoReturn(nav_4,nav_postblog.this, nav_favorite.class);
        helperFunction.ConvertActivityNoReturn(nav_5,nav_postblog.this, nav_account.class);
        helperFunction.ConvertActivityNoReturn(nav_6,nav_postblog.this, nav_uad.class);
    }
}