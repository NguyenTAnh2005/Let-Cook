package com.example.let__cook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class nav_food extends AppCompatActivity {
    ArrayList<Food> arr_food;
    Adapter adapter;
    MainActivity mainActivity;
    DatabaseSQLite databaseSQLite;
    HelperFunction helperFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_food);

        databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);

        // Xem quyen access
        boolean isAdmin= databaseSQLite.checkedIsAdmin(nav_food.this);

        // chuyen lien ket dieu huong cung cap
        LinearLayout nav_1=(LinearLayout) findViewById(R.id.nav_1);
        LinearLayout nav_3=(LinearLayout) findViewById(R.id.nav_3);
        LinearLayout nav_4=(LinearLayout) findViewById(R.id.nav_4);
        LinearLayout nav_5=(LinearLayout) findViewById(R.id.nav_5);
        LinearLayout nav_6=(LinearLayout) findViewById(R.id.nav_6);

        helperFunction.ConvertActivityNoReturn(nav_1,nav_food.this, MainActivity.class);
        helperFunction.ConvertActivityNoReturn(nav_3,nav_food.this, nav_postblog.class);
        helperFunction.ConvertActivityNoReturn(nav_4,nav_food.this, nav_favorite.class);
        helperFunction.ConvertActivityNoReturn(nav_5,nav_food.this, nav_account.class);
        helperFunction.ConvertActivityNoReturn(nav_6,nav_food.this, nav_uad.class);

        // Dua dl tu database len ListView
        ListView food_listview=(ListView) findViewById(R.id.nav_food_listview);
        arr_food=new ArrayList<>();
        adapter=new Adapter(nav_food.this,R.layout.food_fi_small,arr_food,helperFunction);
        food_listview.setAdapter(adapter);

        // Lay Dl tu arr_food bo len
        updateArrFood();

        // Lọc DL theo tag Spinner
        Spinner nav_food_spinner=(Spinner) findViewById(R.id.nav_food_spinner);
        List<String>tags=new ArrayList<>();
        tags.add("Tất cả");
        Cursor cursor_tag=databaseSQLite.getCursorData("SELECT id_tag FROM TAG");
        while (cursor_tag.moveToNext())
        {
            String value=cursor_tag.getString(0);
            switch (value){
                case "braise":
                    if(!tags.contains("Kho")){
                        tags.add("Kho");
                    }
                    break;
                case "stir_fry":
                    if(!tags.contains("Xào")){
                        tags.add("Xào");
                    }
                    break;
                case "fry":
                    if(!tags.contains("Chiên")){
                        tags.add("Chiên");
                    }
                    break;
                case "soup":
                    if(!tags.contains("Canh")){
                        tags.add("Canh");
                    }
                    break;
                case "dessert":
                    if(!tags.contains("Bánh")){
                        tags.add("Bánh");
                    }
                    break;
                case "foreign":
                    if(!tags.contains("Khác")){
                        tags.add("Khác");
                    }
                    break;
            }

            // ham chay ra du lieu tag chi co 1 cot
        }
        cursor_tag.close();
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,tags);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nav_food_spinner.setAdapter(arrayAdapter);

        // ham loc
        // Phan nay em tham khao chat GPT
        nav_food_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTag=parent.getItemAtPosition(position).toString();
                
                // Reset lại bộ lọc trước khi nạp data mới
//                adapter.getFilter().filter("");
                if(selectedTag.equals("Tất cả")){
                    helperFunction.getDataByTagAllSpinner(databaseSQLite,adapter,arr_food);
                }
                else {
                    helperFunction.getDataByTagSpinner(databaseSQLite,adapter,arr_food,selectedTag);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // chuyen sang chi tiet thong tin mon an
        food_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedFood=(Food) parent.getItemAtPosition(position);
                // Su dung bundle va gui no qua ben food_detail
                Intent intent = new Intent(nav_food.this, food_detail.class);
                intent.putExtra("selected_food",selectedFood);
                startActivity(intent);
            }
        });

        // them chuc nang sua + xoa database cho admin
        if(isAdmin==true) {
            food_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Food selectedFood = (Food) parent.getItemAtPosition(position);
                    new AlertDialog.Builder(nav_food.this)
                            .setTitle("Sửa hoặc Xóa Database")
                            .setMessage("Bạn muốn thao tác gì với Database?")
                            .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent =new Intent(nav_food.this, UpdateDatabase.class);
                                    intent.putExtra("food_updated",selectedFood);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AlertDialog.Builder(nav_food.this)
                                            .setMessage("Ban co muon xoa | " + selectedFood.getName_food() + " | khong ?")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    databaseSQLite.QuerySQL("DELETE FROM ingredients WHERE ID_FOOD='" + selectedFood.getId_food() + "';");
                                                    databaseSQLite.QuerySQL("DELETE FROM recipes WHERE ID_FOOD='" + selectedFood.getId_food() + "';");
                                                    databaseSQLite.QuerySQL("DELETE FROM FOOD WHERE ID_FOOD='" + selectedFood.getId_food() + "';");
                                                    updateArrFood();
                                                }
                                            })
                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // ko lam j ca
                                                }
                                            })
                                            .show();
                                }
                            })
                            .show();
                    return true;
                }
            });
        }
        // Lọc dữ liệu ở ListView theo từ khóa
        EditText search=(EditText) findViewById(R.id.food_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    // Cap nhat LISTVIEW FILTER
    public void updateArrFood(){
        Cursor cursor_update=databaseSQLite.getCursorData("SELECT * FROM Food");
        arr_food.clear();
        while (cursor_update.moveToNext()){
            String id=cursor_update.getString(0);
            String name=cursor_update.getString(1);
            Double dif=cursor_update.getDouble(2);
            String img_path=cursor_update.getString(3);
            String desc=cursor_update.getString(4);
            String link=cursor_update.getString(5);
            String note=cursor_update.getString(6);
            String type=cursor_update.getString(7);

            arr_food.add(new Food(id,name,dif,img_path,desc,link,note,type));
        }
        adapter.updateArrMain(arr_food);
        adapter.notifyDataSetChanged();
        cursor_update.close();
    }
    @Override
    protected void onResume(){
        super.onResume();
        updateArrFood();
        Cursor cursor=databaseSQLite.getCursorData("SELECT * FROM FOOD");
        int cc=1;
        while (cursor.moveToNext()){
            Log.d("checkDB",cc+" - "+cursor.getString(1));
            cc++;
        }
    }
}