package com.example.let__cook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class nav_favorite extends AppCompatActivity {
    HelperFunction helperFunction;
    Adapter adapter;
    DatabaseSQLite databaseSQLite;
    ArrayList<Food>Favorite_Food;
    ListView list_view;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_favorite);

         helperFunction.checkedLogin(nav_favorite.this);

         databaseSQLite=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);
         // Lay id tai khoan dang dung hien tai
         SharedPreferences pref=getSharedPreferences("login_pref",MODE_PRIVATE);
         user_id=pref.getString("id_user",null);

        // chuyen lien ket dieu huong cung cap
        LinearLayout nav_1=(LinearLayout) findViewById(R.id.nav_1);
        LinearLayout nav_2=(LinearLayout) findViewById(R.id.nav_2);
        LinearLayout nav_3=(LinearLayout) findViewById(R.id.nav_3);
        LinearLayout nav_5=(LinearLayout) findViewById(R.id.nav_5);
        LinearLayout nav_6=(LinearLayout) findViewById(R.id.nav_6);
        list_view=(ListView) findViewById(R.id.favorite_listview);

        helperFunction.ConvertActivityNoReturn(nav_1,nav_favorite.this, MainActivity.class);
        helperFunction.ConvertActivityNoReturn(nav_2,nav_favorite.this, nav_food.class);
        helperFunction.ConvertActivityNoReturn(nav_3,nav_favorite.this, nav_postblog.class);
        helperFunction.ConvertActivityNoReturn(nav_5,nav_favorite.this, nav_account.class);
        helperFunction.ConvertActivityNoReturn(nav_6,nav_favorite.this, nav_uad.class);

        // Khoi tao adapter
        Favorite_Food=new ArrayList<>();
        adapter=new Adapter(nav_favorite.this,R.layout.food_fi_small,Favorite_Food,helperFunction);
        list_view.setAdapter(adapter);
        updateArrFavoriteFood();

        // chuyen sang chi tiet thong tin mon an
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedFood=(Food) parent.getItemAtPosition(position);

                // Su dung bundle va gui no qua ben food_detail
                Intent intent = new Intent(nav_favorite.this, food_detail.class);

                intent.putExtra("selected_food",selectedFood);
                startActivity(intent);
            }
        });

    }
    public void updateArrFavoriteFood(){
        Favorite_Food.clear(); // Xóa danh sách cũ
        Cursor cursor=databaseSQLite.getCursorData("SELECT FOOD.* FROM FAVORITE " +
                                                        "JOIN FOOD ON FAVORITE.ID_FOOD=FOOD.ID_FOOD " +
                                                        "WHERE FAVORITE.ID_USER='"+user_id+"';");
        Log.d("checkwwww", "Cursor count: " + cursor.getCount());
        while (cursor.moveToNext())
        {
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            Double dif=cursor.getDouble(2);
            String img_path=cursor.getString(3);
            String desc=cursor.getString(4);
            String link=cursor.getString(5);
            String note=cursor.getString(6);
            String type=cursor.getString(7);

            Favorite_Food.add(new Food(id,name,dif,img_path,desc,link,note,type));
            Log.d("checkwwww",id+" "+name+" "+user_id);
        }
        adapter.updateArrMain(Favorite_Food);
        adapter.notifyDataSetChanged();
        cursor.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateArrFavoriteFood(); // Load lại danh sách yêu thích
    }
}