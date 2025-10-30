package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class nav_account extends AppCompatActivity {
    MainActivity context;
    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_account);

        helperFunction.checkedLogin(nav_account.this);

        // chuyen lien ket dieu huong cung cap
        LinearLayout nav_1=(LinearLayout) findViewById(R.id.nav_1);
        LinearLayout nav_2=(LinearLayout) findViewById(R.id.nav_2);
        LinearLayout nav_3=(LinearLayout) findViewById(R.id.nav_3);
        LinearLayout nav_4=(LinearLayout) findViewById(R.id.nav_4);
        LinearLayout nav_6=(LinearLayout) findViewById(R.id.nav_6);


        helperFunction.ConvertActivityNoReturn(nav_1,nav_account.this, MainActivity.class);
        helperFunction.ConvertActivityNoReturn(nav_2,nav_account.this, nav_food.class);
        helperFunction.ConvertActivityNoReturn(nav_3,nav_account.this, nav_postblog.class);
        helperFunction.ConvertActivityNoReturn(nav_4,nav_account.this, nav_favorite.class);
        helperFunction.ConvertActivityNoReturn(nav_6,nav_account.this, nav_uad.class);

        // chuyen lien ket dieu huong khac cap
        LinearLayout act_1= (LinearLayout)findViewById(R.id.account_act1);
        LinearLayout act_2= (LinearLayout)findViewById(R.id.account_act2);
        LinearLayout act_3= (LinearLayout)findViewById(R.id.account_act3);

        helperFunction.ConvertActivityHaveReturn(act_1,nav_account.this, AccountInfo.class);
        helperFunction.ConvertActivityHaveReturn(act_2,nav_account.this, AccountChangeInfo1.class);
        helperFunction.ConvertActivityHaveReturn(act_3,nav_account.this, AccountChangeInfo2.class);

        // su kien cho nut dang xuat, danh sach yeu thich
        LinearLayout favorite=(LinearLayout)findViewById(R.id.account_act5);
        LinearLayout log_out=(LinearLayout) findViewById(R.id.account_act6);
        helperFunction.ConvertActivityNoReturn(favorite,nav_account.this,nav_favorite.class);

        // Su kien cho nut dang xuat
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở SharedPreferences
                SharedPreferences pref = getSharedPreferences("login_pref", MODE_PRIVATE);

                // Mở Editor để chỉnh sửa
                SharedPreferences.Editor editor = pref.edit();

                // Xoá toàn bộ dữ liệu đăng nhập
                editor.clear();       // Xoá tất cả
                editor.apply();       // Lưu thay đổi (hoặc dùng commit())

                Intent convertView=new Intent(nav_account.this, MainActivity.class);
                startActivity(convertView);
                finish();
            }
        });


    }
}