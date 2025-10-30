package com.example.let__cook;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AccountChangeInfo1 extends AppCompatActivity {
    DatabaseSQLite db;
    HelperFunction helperFunction;
    EditText edtFirstName, edtLastName;
    TextView textV_fullname,textV_id;
    RadioGroup radioGroupGender;
    TextView txtBirthDate;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_info1);

        db = new DatabaseSQLite(this, "APP_TEACHING_COOK.sqlite", null, 1);
        helperFunction = new HelperFunction();

        textV_fullname=findViewById(R.id.nav5_act1_str_1);
        textV_id=findViewById(R.id.nav5_act1_str_2);

        edtFirstName = findViewById(R.id.nav5_act2_str_1_value);
        edtLastName = findViewById(R.id.nav5_act2_str_2_value);
        radioGroupGender = findViewById(R.id.nav5_gender);
        txtBirthDate = findViewById(R.id.nav5_act2_str_4_value);
        Button btnCancel = findViewById(R.id.nav5_act2_str_5);
        Button btnOK = findViewById(R.id.nav5_act2_str_6);

        SharedPreferences pref = getSharedPreferences("login_pref", MODE_PRIVATE);
        userId = pref.getString("id_user", null);

        if (userId != null) {
            loadUserInfo(userId);
        }

        // Sự kiện chọn ngày
        txtBirthDate.setOnClickListener(view -> {
            new SingleDateAndTimePickerDialog.Builder(AccountChangeInfo1.this)
                    .bottomSheet()
                    .curved()
                    .displayYears(true)
                    .displayDays(true)
                    .displayHours(false)
                    .displayMinutes(false)
                    .listener(date -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        txtBirthDate.setText(sdf.format(date));
                    })
                    .display();
        });

        // Hủy
        btnCancel.setOnClickListener(view -> finish());

        // Xử lý khi  chọn xác nhận cập nhật
        btnOK.setOnClickListener(view -> {
            String firstName = edtFirstName.getText().toString().trim();
            String lastName = edtLastName.getText().toString().trim();
            String birthDateStr = txtBirthDate.getText().toString().trim();

            int genderId = radioGroupGender.getCheckedRadioButtonId();
            boolean gender;
                RadioButton selected = findViewById(genderId);
                gender=selected.getTag().equals("true"); // So sanh voi true la nu=> tra ve true hoac false

            // Chuyển ngày về định dạng yyyy-MM-dd để lưu vào database
            String birthFormatted = convertDateFormat(birthDateStr, "dd/MM/yyyy", "yyyy-MM-dd");

            db.QuerySQL("UPDATE Account SET " +
                    "f_name = '" + firstName + "', " +
                    "l_name = '" + lastName + "', " +
                    "birth_year = '" + birthFormatted + "', " +
                    "Gender = '" + (gender?1:0) + "' " +
                    // Neu nhu gender la true => nam => them 1
                    "WHERE id_user = '" + userId + "'");

            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    // Hiển thị thông tin user thông qua biến pref get id
    private void loadUserInfo(String idUser) {
        Cursor cursor = db.getCursorData("SELECT id_user, f_name, l_name, birth_year, Gender FROM Account WHERE id_user = '" + idUser + "'");
        if (cursor.moveToFirst()) {

            // Hien thi ben tren
            textV_fullname.setText(cursor.getString(1)+" "+ cursor.getString(2));
            textV_id.setText("ID: "+cursor.getString(0));

            //Hien thi thong tin ben duoi
            edtFirstName.setText(cursor.getString(1));
            edtLastName.setText(cursor.getString(2));

            String birthDate = cursor.getString(3);
            txtBirthDate.setText(convertDateFormat(birthDate, "yyyy-MM-dd", "dd/MM/yyyy"));

            boolean gender = cursor.getInt(4) == 1;
            if (gender) {
                ((RadioButton) findViewById(R.id.nav5_act2_gender_value_2)).setChecked(true); // Nam
            } else {
                ((RadioButton) findViewById(R.id.nav5_act2_gender_value_1)).setChecked(true); // Nữ
            }
        }
        cursor.close();
    }

    private String convertDateFormat(String dateStr, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat from = new SimpleDateFormat(fromFormat, Locale.getDefault());
            SimpleDateFormat to = new SimpleDateFormat(toFormat, Locale.getDefault());
            Date date = from.parse(dateStr);
            return to.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr; // fallback nếu lỗi
        }
    }
}
