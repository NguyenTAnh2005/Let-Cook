package com.example.let__cook;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AccountInfo extends AppCompatActivity {
    DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        Button btn_back=(Button)findViewById(R.id.nav5_act1_str_9);
        db=new DatabaseSQLite(this,"APP_TEACHING_COOK.sqlite",null,1);


        TextView text1=(TextView) findViewById(R.id.nav5_act1_str_1);
        TextView text2=(TextView) findViewById(R.id.nav5_act1_str_2);
        TextView text3=(TextView) findViewById(R.id.nav5_act1_str_3_value);
        TextView text4=(TextView) findViewById(R.id.nav5_act1_str_4_value);
        TextView text5=(TextView) findViewById(R.id.nav5_act1_str_5_value);
        TextView text6=(TextView) findViewById(R.id.nav5_act1_str_6_value);
        TextView text7=(TextView) findViewById(R.id.nav5_act1_str_7_value);
        TextView text8=(TextView) findViewById(R.id.nav5_act1_str_8_value);

        SharedPreferences pref=getSharedPreferences("login_pref",MODE_PRIVATE);
        String id=pref.getString("id_user",null);
        Cursor cursor=db.getCursorData("SELECT * FROM ACCOUNT WHERE ID_USER='"+id+"';");
        while (cursor.moveToNext()){
            text1.setText(cursor.getString(1)+" "+cursor.getString(2));
            text2.setText("ID: "+cursor.getString(0));
            text3.setText(cursor.getString(1));
            text4.setText(cursor.getString(2));
            String gender="";
            if(cursor.getInt(7)==1){
                gender="Nữ";
            }
            else{
                gender="Nam";
            }
            text5.setText(gender);
            text6.setText(cursor.getString(3));
            text7.setText(cursor.getString(4));
            text8.setText(cursor.getString(6));
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}