package com.example.let__cook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import org.w3c.dom.Text;

import java.io.InputStream;

public class food_detail extends AppCompatActivity {
    Food foodselected;

    DatabaseSQLite databaseSQLite;
    HelperFunction helperFunction;

    TextView FI_str2_value;
    TextView FI_str3_value;
    TextView FI_str4_value;
    TextView FI_str5_value;
    TextView FI_str6_value;
    TextView FI_str7_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        SharedPreferences pref=getSharedPreferences("login_pref",MODE_PRIVATE);
        String id_user=pref.getString("id_user","null");

         FI_str2_value=(TextView) findViewById(R.id.name_food);
         FI_str3_value=(TextView) findViewById(R.id.desccrible_value);
         FI_str4_value=(TextView) findViewById(R.id.ingredients_value);
         FI_str5_value=(TextView) findViewById(R.id.notes_value);
         FI_str6_value=(TextView) findViewById(R.id.recipes_value);
         FI_str7_value=(TextView) findViewById(R.id.linkfood_value);
        ImageView FI_imgpath=(ImageView) findViewById(R.id.img_food);
        ImageView favorite_icon=findViewById(R.id.icon_favorite);

        databaseSQLite = new DatabaseSQLite(this, "APP_TEACHING_COOK.sqlite", null, 1);

        // LAy du lieu bundle duoc gui tu nav_food
        String ingredients_value="",step_value="";
        Food selectedFood=(Food) getIntent().getSerializableExtra("selected_food");
        // check trang thai favorite
        check_favorite_mode(favorite_icon,id_user,selectedFood.getId_food());

        Log.d("tag",selectedFood.getId_food()+selectedFood.getName_food());
        String id_food=selectedFood.getId_food();
        if(selectedFood!=null){
            // Lay du lieu tu bundle duoc send den

            FI_str2_value.setText(selectedFood.getName_food());
            FI_str3_value.setText(selectedFood.getDesc_food());

            // Them nguyen lieu
            Cursor cursor_ingre= databaseSQLite.getCursorData("SELECT * FROM Ingredients WHERE id_food='"+selectedFood.getId_food()+"';");
            while (cursor_ingre.moveToNext()){
                ingredients_value+="-"+cursor_ingre.getString(2)+" \n";
            }
            FI_str4_value.setText(ingredients_value);// Nguyeen lieu
            FI_str5_value.setText(selectedFood.getNote_food());
            // Them buoc che bien
            Cursor cursor_rec=databaseSQLite.getCursorData("SELECT * FROM Recipes WHERE id_food='"+selectedFood.getId_food()+"';");
            while (cursor_rec.moveToNext()){
                step_value+="* Bước "+cursor_rec.getInt(0)+": "+cursor_rec.getString(2)+" \n\n";
            }
            FI_str6_value.setText(step_value);//recipes
            FI_str7_value.setText(selectedFood.getLink_cook());
            FI_imgpath.setImageResource(selectedFood.getImgResId(food_detail.this));
        }

        // Them su kien cho text Quay lai
        TextView back=(TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // chuyen doi TT icon

        change_mode_icon(favorite_icon,id_user,id_food);

    }

    // khoi tao ham chuyen doi trang thai trai tim va xu ly them mon an vao danh sach yeu thich
    public void change_mode_icon(ImageView favorite_icon,String id_user, String id_food){
        favorite_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                boolean isLoggedIn = prefs.getBoolean("tickLogIn", false);
                if(!isLoggedIn){
                    new AlertDialog.Builder(food_detail.this)
                            .setTitle("Thông báo")
                            .setMessage("Bạn cần đăng nhập để sử dụng tính năng này!")
                            .setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent =new Intent(food_detail.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // ko lam j ca
                                }
                            })
                            .show();
                }
                else if(favorite_icon.getTag().equals("white")){
                    favorite_icon.setTag("red");
                    favorite_icon.setImageResource(R.drawable.favorite_red_icon);
                    // Xu ly them mon an vao danh sach yeu thich
                    databaseSQLite.QuerySQL("INSERT INTO FAVORITE VALUES('"+id_user+"', '"+id_food+"');");
                    helperFunction.ToastMessage(food_detail.this,"Đã thêm món ăn vào danh sách yêu thích!");
                    printfavoriteValue();

                }
                else {
                    favorite_icon.setTag("white");
                    favorite_icon.setImageResource(R.drawable.favorite_icon);
                    databaseSQLite.QuerySQL("DELETE FROM FAVORITE WHERE ID_USER='"+id_user+"' AND ID_FOOD='"+id_food+"';");
                    helperFunction.ToastMessage(food_detail.this,"Đã xóa món ăn khỏi danh sách yêu thích!");
                }
            }
        });
    }

    public void check_favorite_mode(ImageView favorite_icon,String id_user,String id_food){
        Cursor cursor=databaseSQLite.getCursorData("SELECT * FROM FAVORITE WHERE ID_USER='"+id_user+"' AND ID_FOOD='"+id_food+"';");
        boolean isFavorite=cursor.moveToFirst();// cursor se lay dong du lieu dau tien => neu co thi true, khong thi false
        if(isFavorite==true){
            favorite_icon.setImageResource(R.drawable.favorite_red_icon);
            favorite_icon.setTag("red");
        }
        else{
            favorite_icon.setImageResource(R.drawable.favorite_icon);
            favorite_icon.setTag("white");
        }
    }

    public void printfavoriteValue(){
        Cursor cursor = databaseSQLite.getReadableDatabase().rawQuery("SELECT * FROM Favorite", null);
        int count=1;
        while (cursor.moveToNext())
        {
            Log.d("Check_favorite",count+"___ ID_USER: "+cursor.getString(0)+" - ID FOOD: "+cursor.getString(1));
            count++;
        }
        cursor.close();
    }


}