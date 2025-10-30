package com.example.let__cook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nav_uad extends AppCompatActivity {
    MainActivity context;
    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_uad);

        helperFunction.checkedLogin(nav_uad.this);
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(nav_uad.this, "APP_TEACHING_COOK.sqlite", null, 1);
        boolean checkaccess= databaseSQLite.checkedIsAdmin(nav_uad.this);
        if(!checkaccess){
            new AlertDialog.Builder(nav_uad.this)
                    .setTitle("Thông báo")
                    .setMessage("Tính năng chỉ dành cho tài khoản có thẩm quyền!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent convertAct=new Intent(nav_uad.this,MainActivity.class);
                            startActivity(convertAct);
                            finish();
                        }
                    })
                    .show();
        }

        // chuyen lien ket dieu huong cung cap
        LinearLayout nav_1=(LinearLayout) findViewById(R.id.nav_1);
        LinearLayout nav_2=(LinearLayout) findViewById(R.id.nav_2);
        LinearLayout nav_3=(LinearLayout) findViewById(R.id.nav_3);
        LinearLayout nav_4=(LinearLayout) findViewById(R.id.nav_4);
        LinearLayout nav_5=(LinearLayout) findViewById(R.id.nav_5);

        helperFunction.ConvertActivityNoReturn(nav_1,nav_uad.this, MainActivity.class);
        helperFunction.ConvertActivityNoReturn(nav_2,nav_uad.this, nav_food.class);
        helperFunction.ConvertActivityNoReturn(nav_3,nav_uad.this, nav_postblog.class);
        helperFunction.ConvertActivityNoReturn(nav_4,nav_uad.this, nav_favorite.class);
        helperFunction.ConvertActivityNoReturn(nav_5,nav_uad.this, nav_account.class);


        // chuyen huong khac lop
        LinearLayout add_database=(LinearLayout) findViewById(R.id.nav6_add_database);
        LinearLayout update_database=(LinearLayout) findViewById(R.id.nav6_update_database);

        helperFunction.ConvertActivityHaveReturn(add_database,nav_uad.this, AddDatabase.class);
        update_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(nav_uad.this,nav_food.class);
                new AlertDialog.Builder(nav_uad.this)

                        .setMessage("Nhấn giữ vào món ăn bạn muốn xóa hoặc cập nhật thông tin !")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ko lam j
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();

            }
        });
    }
}