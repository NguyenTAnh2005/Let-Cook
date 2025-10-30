package com.example.let__cook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddDatabase extends AppCompatActivity {
    Food food_added;
    Ingredients ingrd_added;
    Recipes rec_added;
    DatabaseSQLite databaseSQLite;
    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_database);

        Button back=(Button)findViewById(R.id.nav6_act1_str_8);
        Button ok=(Button) findViewById(R.id.nav6_act1_str_9);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);
        //
        EditText edt_dif=(EditText) findViewById(R.id.nav6_act1_str_11_value);
        EditText edt_desc=(EditText) findViewById(R.id.nav6_act1_str_12_value);
        EditText edt_name=(EditText) findViewById(R.id.nav6_act1_str3_value);
        EditText edt_ingre=(EditText) findViewById(R.id.nav6_act1_str4_value);
        EditText edt_note=(EditText) findViewById(R.id.nav6_act1_str5_value);
        EditText edt_rec=(EditText) findViewById(R.id.nav6_act1_str6_value);
        EditText edt_link=(EditText) findViewById(R.id.nav6_act1_str7_value);

        RadioGroup taggroup=(RadioGroup) findViewById(R.id.radioGroupTag);
        String value_tag=((RadioButton) findViewById(taggroup.getCheckedRadioButtonId())).getTag().toString();


        // Tự tạo id
        Cursor cursor=databaseSQLite.getCursorData("SELECT Count(ID_FOOD) FROM Food WHERE type_food='"+value_tag+"';");
        int count=0;
        while (cursor.moveToNext()){
            count=cursor.getInt(0);
        }
        cursor.close();
        count++;// stt cua id moi
        String index=String.valueOf(count);


        // CODE CHAT GPT
        // Xu ly khi chon OK
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddDatabase.this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn thêm món ăn này?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi người dùng nhấn OK
                                String id=value_tag+"_"+index;
                                String name=edt_name.getText().toString();
                                Double dif=Double.parseDouble(edt_dif.getText().toString());
                                String img="default_food.png";
                                String desc=edt_desc.getText().toString();
                                String link=edt_link.getText().toString();
                                String note=edt_note.getText().toString();
                                String tag =value_tag;
                                String ingre=edt_ingre.getText().toString();
                                String rec=edt_rec.getText().toString();

                                if((!name.isEmpty()) && dif!=null && desc!=null && link!=null && note!=null && (!ingre.isEmpty()) && (!rec.isEmpty())){
                                    // Neu nhu cac truong thong tin ko trong va hop le
                                    // Thêm ngầm CSDL
                                        // 1. Thêm dữ liệu vào bảng Food
                                    String sqlFood = "INSERT INTO Food VALUES (" +
                                            "'" + id + "', " + "'" + name + "', " + dif + ", " +
                                            "'" + img + "', " + "'" + desc + "', " + "'" + link + "', " +
                                            "'" + note + "', " + "'" + tag + "'" + ")";
                                    databaseSQLite.QuerySQL(sqlFood);

                                        // 2. Thêm dữ liệu vào bảng Ingredients
                                    String sqlIngre = "INSERT INTO Ingredients VALUES (" +
                                            "1, " +  // ID mặc định
                                            "'" + id + "', " + "'" + ingre + "'" + ")";
                                    databaseSQLite.QuerySQL(sqlIngre);

                                        // 3. Thêm dữ liệu vào bảng Recipes
                                    String sqlRecipe = "INSERT INTO Recipes VALUES (" +
                                            "1, " +  // ID mặc định
                                            "'" + id + "', " + "'" + rec + "'" + ")";
                                    databaseSQLite.QuerySQL(sqlRecipe);
                                    Intent intent = new Intent(AddDatabase.this, nav_food.class);
                                    startActivity(intent);
                                }
                                else {
                                    helperFunction.ToastMessage(AddDatabase.this,"Vui lòng điền đủ và đúng thông tin!");
                                }
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi người dùng nhấn Hủy
                                dialog.dismiss(); // Đóng dialog
                            }
                        })
                        .show();
            }
        });

    }

}