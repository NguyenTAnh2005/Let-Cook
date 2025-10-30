package com.example.let__cook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DatabaseSQLite databaseSQLite;
    HelperFunction helperFunction;
    ArrayList<Food> Arr_Food_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // chuyen dieu huong cung cap
        LinearLayout nav_2=(LinearLayout) findViewById(R.id.nav_2);
        LinearLayout nav_3=(LinearLayout) findViewById(R.id.nav_3);
        LinearLayout nav_4=(LinearLayout) findViewById(R.id.nav_4);
        LinearLayout nav_5=(LinearLayout) findViewById(R.id.nav_5);
        LinearLayout nav_6=(LinearLayout) findViewById(R.id.nav_6);

        helperFunction.ConvertActivityNoReturn(nav_2,MainActivity.this, nav_food.class);
        helperFunction.ConvertActivityNoReturn(nav_3,MainActivity.this, nav_postblog.class);
        helperFunction.ConvertActivityNoReturn(nav_4,MainActivity.this, nav_favorite.class);
        helperFunction.ConvertActivityNoReturn(nav_5,MainActivity.this, nav_account.class);
        helperFunction.ConvertActivityNoReturn(nav_6,MainActivity.this, nav_uad.class);

        // Tạo DATABASE, TABLE
//        databaseSQLite.QuerySQL("DELETE * FROM FOOD");
        databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);
        // Create Table food
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Account(" +
                "id_user VARCHAR(20) PRIMARY KEY, " +
                "f_name NVARCHAR(20) NOT NULL, " +
                "l_name NVARCHAR(40) NOT NULL, " +
                "birth_year DATETIME NOT NULL, " +
                "email VARCHAR(255) NOT NULL, " +
                "pass_word VARCHAR(255) NOT NULL, " +
                "Access VARCHAR(5), " +
                "Gender Boolean)");
        // 0/1 <=> true/false <=> female/male

        // Tạo bảng Tags
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Tag (" +
                "id_tag VARCHAR(20) PRIMARY KEY, " + // tag food
                "name_tag NVARCHAR(20), " +
                "desc_tag NVARCHAR(4000))");
        // Tạo bảng Food
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Food (" +
                "id_food VARCHAR(20) PRIMARY KEY, " +
                "name_food NVARCHAR(4000) NOT NULL, " +
                "difficulty DOUBLE, " +
                "img_food Varchar(24)," +
                "desc_food NVARCHAR(4000), " +
                "link_cook NVARCHAR(100)," +
                "note_food NVARCHAR(4000)," +
                "type_food VARCHAR(20), " +
                "FOREIGN KEY (type_food) REFERENCES Tag(id_tag) ON DELETE CASCADE)");

        // Tạo bảng Recipes
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Recipes (" +
                "id_recipes Integer, " +
                "id_food VARCHAR(20), " +
                "step NVARCHAR(4000) NOT NULL, " +
                "PRIMARY KEY (id_recipes, id_food), " +
                "FOREIGN KEY (id_food) REFERENCES Food(id_food) ON DELETE CASCADE)");

        // Tạo bảng Ingredients
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Ingredients (" +
                "id_Ingredients INTEGER, " +
                "id_food VARCHAR(20), " +
                "desc_ingre NVARCHAR(4000) NOT NULL, " +
                "PRIMARY KEY (id_Ingredients, id_food), " +
                "FOREIGN KEY (id_food) REFERENCES Food(id_food) ON DELETE CASCADE)");

        // Tạo bảng Post
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Post (" +
                "id_post INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_user VARCHAR(20), " +
                "title NVARCHAR(255) NOT NULL, " +
                "content NVARCHAR(4000), " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "P_Status CHAR(20), " +
                "FOREIGN KEY (id_user) REFERENCES AppUser(id_user) ON DELETE CASCADE)");

        // Tạo bảng Favorite
        databaseSQLite.QuerySQL("CREATE TABLE IF NOT EXISTS Favorite (" +
                "id_user VARCHAR(20), " +
                "id_food VARCHAR(20), " +
                "PRIMARY KEY (id_user, id_food), " +
                "FOREIGN KEY (id_user) REFERENCES AppUser(id_user) ON DELETE CASCADE, " +
                "FOREIGN KEY (id_food) REFERENCES Food(id_food) ON DELETE CASCADE)");



        // Them database
        helperFunction.InsertDatabaseAll(this);

        Arr_Food_Home=new ArrayList<>();
        Cursor cursor=databaseSQLite.getCursorData("SELECT * FROM FOOD");
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            Double dif=cursor.getDouble(2);
            String img_path=cursor.getString(3);
            String desc=cursor.getString(4);
            String link=cursor.getString(5);
            String note=cursor.getString(6);
            String type=cursor.getString(7);
            Arr_Food_Home.add(new Food(id,name,dif,img_path,desc,link,note,type));
        }
        // import random len mau co san
        ImageView i1_img=(ImageView) findViewById(R.id.homeFIsmall_1_img);
        TextView i1_name=(TextView)findViewById(R.id.homeFIsmall_1_name);
        TextView i1_diff=(TextView) findViewById(R.id.homeFIsmall_1_diff);

        ImageView i2_img = findViewById(R.id.homeFIsmall_2_img);
        TextView i2_name = findViewById(R.id.homeFIsmall_2_name);
        TextView i2_diff = findViewById(R.id.homeFIsmall_2_diff);

        ImageView i3_img = findViewById(R.id.homeFIsmall_3_img);
        TextView i3_name = findViewById(R.id.homeFIsmall_3_name);
        TextView i3_diff = findViewById(R.id.homeFIsmall_3_diff);

        ImageView i4_img = findViewById(R.id.homeFIsmall_4_img);
        TextView i4_name = findViewById(R.id.homeFIsmall_4_name);
        TextView i4_diff = findViewById(R.id.homeFIsmall_4_diff);

        ImageView i5_img = findViewById(R.id.homeFIsmall_5_img);
        TextView i5_name = findViewById(R.id.homeFIsmall_5_name);
        TextView i5_diff = findViewById(R.id.homeFIsmall_5_diff);

        ImageView i6_img = findViewById(R.id.homeFIsmall_6_img);
        TextView i6_name = findViewById(R.id.homeFIsmall_6_name);
        TextView i6_diff = findViewById(R.id.homeFIsmall_6_diff);

        ImageView i7_img = findViewById(R.id.homeFIsmall_7_img);
        TextView i7_name = findViewById(R.id.homeFIsmall_7_name);
        TextView i7_diff = findViewById(R.id.homeFIsmall_7_diff);

        ImageView i8_img = findViewById(R.id.homeFIsmall_8_img);
        TextView i8_name = findViewById(R.id.homeFIsmall_8_name);
        TextView i8_diff = findViewById(R.id.homeFIsmall_8_diff);

        ImageView i9_img = findViewById(R.id.homeFIsmall_9_img);
        TextView i9_name = findViewById(R.id.homeFIsmall_9_name);
        TextView i9_diff = findViewById(R.id.homeFIsmall_9_diff);

        ImageView[] imgs = new ImageView[] {
                i1_img, i2_img, i3_img, i4_img, i5_img,
                i6_img, i7_img, i8_img, i9_img
        };

        TextView[] names = new TextView[] {
                i1_name, i2_name, i3_name, i4_name, i5_name,
                i6_name, i7_name, i8_name, i9_name
        };

        TextView[] diffs = new TextView[] {
                i1_diff, i2_diff, i3_diff, i4_diff, i5_diff,
                i6_diff, i7_diff, i8_diff, i9_diff
        };

        // XAO TRON DS ARR FOOD ROI LAY PHAN TU RA THEO INDEX
        Collections.shuffle(Arr_Food_Home);
        for(int i=0;i<9;i++){
            Food food=Arr_Food_Home.get(i);
            imgs[i].setImageResource(food.getImgResId(MainActivity.this));
            names[i].setText(food.getName_food());
            diffs[i].setText(String.valueOf(food.getDifficulty()));

            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,food_detail.class);
                    intent.putExtra("selected_food",food);
                    startActivity(intent);
                }
            });
            names[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,food_detail.class);
                    intent.putExtra("selected_food",food);
                    startActivity(intent);
                }
            });
            names[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,food_detail.class);
                    intent.putExtra("selected_food",food);
                    startActivity(intent);
                }
            });
        }

        // chuyen sang bundle neu nhu an vao anh

        // chuyen sang nav_food khi nhan kham pha
        Button btn_expolre=(Button) findViewById(R.id.nav1_str_4);
        helperFunction.ConvertActivityNoReturn(btn_expolre,MainActivity.this,nav_food.class);


    }

}