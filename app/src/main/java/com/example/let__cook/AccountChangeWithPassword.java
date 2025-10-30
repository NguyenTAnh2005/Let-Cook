package com.example.let__cook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.let__cook.DatabaseSQLite;

public class AccountChangeWithPassword extends AppCompatActivity {
    private DatabaseSQLite database;
    private String userID;
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_with_password);

        database = new DatabaseSQLite(AccountChangeWithPassword.this, "APP_TEACHING_COOK.sqlite", null, 1);

        //Nhận dữ liệu từ Intent (bên Info 2)
        Intent intent = getIntent();
        SharedPreferences pref=getSharedPreferences("login_pref",MODE_PRIVATE);
        userID=pref.getString("id_user",null);
        newPassword = intent.getStringExtra("new_password");

        //Ẩn/hiện pass
        EditText oldPassField = findViewById(R.id.nav5_act3_password_str_1_value);
        ImageView eyeIcon = findViewById(R.id.nav5_act3_password_str_1_value_eye);
        HelperFunction.hide_appear_eye(eyeIcon, oldPassField);

        //Xác nhận
        Button btnConfirm = findViewById(R.id.nav5_act3_password_str_4);
        btnConfirm.setOnClickListener(v -> {
            String oldPassword = oldPassField.getText().toString().trim();
            if (oldPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu hiện tại", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cursor=database.getCursorData("SELECT pass_word FROM account Where id_user='"+userID+"';");
                String cursor_old_pass="";
                while (cursor.moveToNext()){
                    cursor_old_pass=cursor.getString(0);
                }
            cursor.close();

            if(oldPassword.equals(cursor_old_pass)){
                    database.QuerySQL("UPDATE Account SET pass_word = '" + newPassword + "' WHERE id_user = '" + userID + "';");
                    Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    finish();
            }
            else {
                Toast.makeText(this, "Mật khẩu hiện tại không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}