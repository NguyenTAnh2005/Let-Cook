package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
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

import com.example.let__cook.DatabaseSQLite;

public class AccountChangeInfo2 extends AppCompatActivity {
    private MainActivity context;
    private HelperFunction helperFunction;
    private DatabaseSQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_info2);

        database = new DatabaseSQLite(AccountChangeInfo2.this, "APP_TEACHING_COOK.sqlite", null, 1);

        helperFunction = new HelperFunction();

        Button btnChangeByPassword = findViewById(R.id.nav5_act3_str_4);
        Button btnChangeByEmail = findViewById(R.id.nav5_act3_str_5);

        EditText etNewEmail = findViewById(R.id.nav5_act3_str_1_value);
        EditText etNewPassword = findViewById(R.id.nav5_act3_str_2_value);
        EditText etConfirmPassword = findViewById(R.id.nav5_act3_str_3_value);

        ImageView eye_1 = findViewById(R.id.nav5_act3_str_2_value_eye);
        ImageView eye_2 = findViewById(R.id.nav5_act3_str_3_value_eye);

        helperFunction.hide_appear_eye(eye_1,etNewPassword);
        helperFunction.hide_appear_eye(eye_2,etConfirmPassword);

        btnChangeByEmail.setOnClickListener(v -> {
            String newEmail = etNewEmail.getText().toString().trim();
            if (newEmail.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email mới", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, AccountChangeWithEmail.class);
            intent.putExtra("newEmail", newEmail);
            startActivity(intent);
        });

        btnChangeByPassword.setOnClickListener(v -> {
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mật khẩu mới", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, AccountChangeWithPassword.class);
            intent.putExtra("new_password", newPassword);
            startActivity(intent);
        });

        // load ten va id len ben dau
        TextView full_name=findViewById(R.id.nav5_act1_str_1);
        TextView id=findViewById(R.id.nav5_act1_str_2);
        SharedPreferences pref=getSharedPreferences("login_pref",MODE_PRIVATE);
        id.setText("ID: "+pref.getString("id_user",null));

        Cursor cursor=database.getCursorData("SELECT f_name,l_name From account where id_user='"+pref.getString("id_user",null)+"';");
        while (cursor.moveToNext()){
            full_name.setText(cursor.getString(0)+" "+cursor.getString(1));
        }
    }
}