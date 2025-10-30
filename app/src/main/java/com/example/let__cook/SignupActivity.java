package com.example.let__cook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {
    DatabaseSQLite databaseSQLite;
    HelperFunction helperFunction;

    EditText etLastName, etFirstName, etEmail, etPassword, etConfirmPassword;
    RadioGroup genderGroup;
    TextView birthTextView;
    Button btnSignup, btnBirthPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        helperFunction = new HelperFunction();
        databaseSQLite = new DatabaseSQLite(this, "APP_TEACHING_COOK.sqlite", null, 1);

        etLastName = findViewById(R.id.signup_str_2_value);
        etFirstName = findViewById(R.id.signup_str_3_value);
        etEmail = findViewById(R.id.signup_str_4_value);
        etPassword = findViewById(R.id.sign_up_str_5_value);
        etConfirmPassword = findViewById(R.id.signup_str_6_value);
        genderGroup = findViewById(R.id.signup_gender);
        birthTextView = findViewById(R.id.signup_str_7_value);
        btnBirthPick = findViewById(R.id.signup_str_7);
        btnSignup = findViewById(R.id.sign_up_btn);

        ImageView eyePass1 = findViewById(R.id.sign_up_str_5_value_eye);
        ImageView eyePass2 = findViewById(R.id.signup_str_6_value_eye);

        HelperFunction.hide_appear_eye(eyePass1, etPassword);
        HelperFunction.hide_appear_eye(eyePass2, etConfirmPassword);

        TextView loginFromSignup = findViewById(R.id.signup_str_8_2);
        TextView forgotPassFromSignup = findViewById(R.id.signup_str_9);

        helperFunction.ConvertActivityHaveReturn(loginFromSignup, SignupActivity.this, LoginActivity.class);
        helperFunction.ConvertActivityHaveReturn(forgotPassFromSignup, SignupActivity.this, ForgotPassActivity.class);

        btnSignup.setOnClickListener(v -> {
            String lName = etLastName.getText().toString().trim();
            String fName = etFirstName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            String birth = birthTextView.getText().toString().trim();

            if (lName.isEmpty() || fName.isEmpty() || email.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty() || birth.isEmpty() || birth.equals("Ngày sinh của bạn")) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (databaseSQLite.isEmailExists(email)) {
                Toast.makeText(this, "Email đã tồn tại, vui lòng dùng email khác!", Toast.LENGTH_SHORT).show();
                return;
            }

            int genderId = genderGroup.getCheckedRadioButtonId();
            if (genderId == -1) {
                Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedGender = findViewById(genderId);
            boolean isMale = selectedGender.getText().toString().equalsIgnoreCase("Nam");

            String userId = "USER_" + System.currentTimeMillis();

            boolean success = databaseSQLite.insertUser(userId, fName, lName, birth, email, password, "user", isMale);
            if (success) {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Lỗi khi đăng ký, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBirthPick.setOnClickListener(v -> {
            new SingleDateAndTimePickerDialog.Builder(SignupActivity.this)
                    .bottomSheet()
                    .curved()
                    .displayYears(true)
                    .displayDays(true)
                    .displayHours(false)
                    .displayMinutes(false)
                    .listener((Date date) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = sdf.format(date);
                        birthTextView.setText(formattedDate);
                    })
                    .display();
        });
    }
}