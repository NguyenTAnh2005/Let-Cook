package com.example.let__cook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateDatabase extends AppCompatActivity {

    HelperFunction helperFunction;
    DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);

        EditText edt_name=(EditText) findViewById(R.id.update_food_item_str_1_value);
        EditText edt_desc=(EditText) findViewById(R.id.update_food_item_str_2_value);
        EditText edt_ingre=(EditText) findViewById(R.id.update_food_item_str_3_value);
        EditText edt_note=(EditText) findViewById(R.id.update_food_item_str_4_value);
        EditText edt_rec=(EditText) findViewById(R.id.update_food_item_str_5_value);
        EditText edt_link=(EditText) findViewById(R.id.update_food_item_str_6_value);
        EditText edt_dif=(EditText) findViewById(R.id.update_food_item_str_9_value);
        Button btn_cancel=(Button)findViewById(R.id.update_food_item_str_7);
        Button btn_ok=(Button)findViewById(R.id.update_food_item_str_8);

        TextView name_food_old=(TextView) findViewById(R.id.name_food);
        ImageView img_food_old=(ImageView) findViewById(R.id.img_food);

        // Khai bao truoc mot so thu can thiet
        databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);

        String ingre=""; // dung de chua text nguyen lieu tu lenh truy xuat database
        String rec="";  // dung de chua text cac buoc che bien dua theo lenh truy xuat database



        // Lay doi tuong food duoc gui den tu nav_food
        Food sended_food=(Food) getIntent().getSerializableExtra("food_updated");
        if (sended_food!=null){
            // bo qua de bi loi truyen du lieu
            name_food_old.setText(sended_food.getName_food());
            img_food_old.setImageResource(sended_food.getImgResId(UpdateDatabase.this));
            edt_name.setText(sended_food.getName_food());
            edt_desc.setText(sended_food.getDesc_food());
            edt_note.setText(sended_food.getNote_food());
            edt_link.setText(sended_food.getLink_cook());
            edt_dif.setText(String.valueOf(sended_food.getDifficulty()));

            // Lay ID cho an toan
            String id=sended_food.getId_food();
            Log.d("tag1",sended_food.getImg_food());

            // Them nguyen lieu

            edt_ingre.setText(getIngreValue(ingre,sended_food.getId_food()));// Nguyeen lieu
            // Them buoc che bien

            edt_rec.setText(getRecValue(rec,sended_food.getId_food()));
        }
        // Su kien huy thay doi
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Su kien thay doi
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UpdateDatabase.this)
                        .setTitle("Warning")
                        .setMessage("Bạn có muốn nhận cập nhật các thông tin này? ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // cap nhat du lieu
                                // Xoa DL cu
                                databaseSQLite.QuerySQL("DELETE FROM ingredients WHERE ID_FOOD='" + sended_food.getId_food() + "';");
                                databaseSQLite.QuerySQL("DELETE FROM recipes WHERE ID_FOOD='" + sended_food.getId_food() + "';");
                                String newName = edt_name.getText().toString();
                                String newDesc = edt_desc.getText().toString();
                                String newNote = edt_note.getText().toString();
                                String newLink = edt_link.getText().toString();
                                String newIngre = edt_ingre.getText().toString();
                                String newRec = edt_rec.getText().toString();
                                Double newDif=Double.parseDouble(edt_dif.getText().toString());

                                // 1. Cập nhật DL món ăn
                                String sqlUpdate = "UPDATE Food SET " +
                                        "name_food = '" + newName + "', " +
                                        "difficulty = " + newDif + ", " +
                                        "img_food = '" + sended_food.getImg_food() + "', " +
                                        "desc_food = '" + newDesc + "', " +
                                        "link_cook = '" + newLink + "', " +
                                        "note_food = '" + newNote + "', " +
                                        "type_food = '" + sended_food.getTag_food() + "' " +
                                        "WHERE id_food = '" + sended_food.getId_food() + "'";

                                databaseSQLite.QuerySQL(sqlUpdate);

                                // 2. Thêm dữ liệu vào bảng Ingredients
                                String sqlIngre = "INSERT INTO Ingredients VALUES (" +
                                        "1, " +
                                        "'" + sended_food.getId_food() + "', " +
                                        "'" + newIngre + "'" +
                                        ")";
                                databaseSQLite.QuerySQL(sqlIngre);
                                // 3. Thêm dữ liệu vào bảng Recipes
                                String sqlRecipe = "INSERT INTO Recipes VALUES (" +
                                        "1, " +
                                        "'" + sended_food.getId_food() + "', " +
                                        "'" + newRec + "'" +
                                        ")";
                                databaseSQLite.QuerySQL(sqlRecipe);
                                helperFunction.ToastMessage(UpdateDatabase.this,"Cập nhật thành công");
//                                Intent intent=new Intent(UpdateDatabase.this,nav_food.class);
//                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ko lam j
                            }
                        })
                        .show();
            }
        });

    }
    public String getIngreValue(String ingre,String id){
        Cursor cursor_ingre= databaseSQLite.getCursorData("SELECT * FROM Ingredients WHERE id_food='"+id+"';");
        while (cursor_ingre.moveToNext()){
            ingre+="-"+cursor_ingre.getString(2)+" \n";
        }
        return ingre;
    }
    public String getRecValue(String rec, String id){
        Cursor cursor_rec=databaseSQLite.getCursorData("SELECT * FROM Recipes WHERE id_food='"+id+"';");
        while (cursor_rec.moveToNext()){
            rec+="* Bước "+cursor_rec.getInt(0)+": "+cursor_rec.getString(2)+" \n\n";
        }
        return rec;
    }

}