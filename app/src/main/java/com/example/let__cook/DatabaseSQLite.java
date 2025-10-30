package com.example.let__cook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseSQLite extends SQLiteOpenHelper {
    public DatabaseSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    // Ham thuc hien cau lenh SQL
    public void QuerySQL(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    // Tra ve dl dang con tro - dung nhieu cho ham select thong tin cu the
    public Cursor getCursorData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    //Check email vf pass khi Đăng nhập
    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Account WHERE email = ? AND pass_word = ?",
                new String[]{email, password}
        );
        boolean isLoggedIn = cursor.getCount() > 0;
        cursor.close();
        return isLoggedIn;
    }

    // Tra ve id user thong qua email
    public String getIdUserFromEmail(String email){
        String id="";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id_user FROM ACCOUNT WHERE email=? ",new String[]{email});
        while (cursor.moveToNext()){
             id=cursor.getString(0);
        }
        cursor.close();
        return id;
    }
    //kiểm tra email đăng kí có hay chưa
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    //thêm tài khoản
    public boolean insertUser(String id_user, String f_name, String l_name, String birth, String email, String password, String access, boolean gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(birth);
            String formattedBirth = dbFormat.format(date);
            values.put("birth_year", formattedBirth);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        values.put("id_user", id_user);
        values.put("f_name", f_name);
        values.put("l_name", l_name);
        values.put("email", email);
        values.put("pass_word", password);
        values.put("Access", access);
        values.put("Gender", gender); // Boolean: true = Nam, false = Nữ

        long result = db.insert("Account", null, values);
        return result != -1;
    }

    //cập nhật pass
    public boolean updatePasswordByEmail(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pass_word", newPassword); // sửa đúng tên cột trong bảng Account

        int result = db.update("Account", values, "email=?", new String[]{email}); // sửa đúng tên bảng
        return result > 0;
    }

    public void updateEmailById(String userID, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        db.update("Account", values, "id_user=?", new String[]{userID});
        db.close();
    }

    // Kiem tra quyen truy cap
    public boolean checkedIsAdmin(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        String id_user=prefs.getString("id_user","");
        String access="";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT ACCESS FROM ACCOUNT WHERE ID_USER=?",new String[]{id_user});
        while (cursor.moveToNext()){
            access=cursor.getString(0);
        }
        return access.equals("admin")?true:false;
    }
}

