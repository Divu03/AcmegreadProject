package com.example.shopping;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserManager.db";
    public static final String TABLE_USER ="user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+"("
            +COLUMN_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_USER_NAME+" TEXT,"
            +COLUMN_USER_EMAIL+" TEXT,"+COLUMN_USER_PASSWORD+" TEXT)";
    private String DROP_USER_TABLE ="DROP TABLE IF EXISTS "+TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLUMN_USER_NAME,user.getName());
        val.put(COLUMN_USER_EMAIL,user.getEmail());
        val.put(COLUMN_USER_PASSWORD,user.getPassword());
        db.insert(TABLE_USER,null,val);
        db.close();
    }
    public boolean checkUser(String email){
        String[] columns = {
          COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    @SuppressLint("Range")
    public List<User> getAllUser(){
        String[] columns = {
                COLUMN_USER_ID,COLUMN_USER_NAME,COLUMN_USER_EMAIL,COLUMN_USER_PASSWORD
        };
        String sortOrders = COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,null,null,null,sortOrders);
        if(cursor.moveToFirst()){
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLUMN_USER_NAME,user.getName());
        val.put(COLUMN_USER_EMAIL,user.getEmail());
        val.put(COLUMN_USER_PASSWORD,user.getPassword());
        db.update(TABLE_USER,val,COLUMN_USER_ID + " = ?",new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,COLUMN_USER_ID + " = ?",new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUserLogin(String email,String pass) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ? AND "+ COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email,pass};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }
    @SuppressLint("Range")
    public User getUser(String email){

        User user = new User();
        String[] columns={
            COLUMN_USER_EMAIL,COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,null,null);
        if(cursor.moveToNext()){
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setId(0);
                user.setPassword("0");
                return user;
        }else{
            User notfound = new User();
            notfound.setEmail("Not");
            notfound.setName("Not");
            return notfound;
        }
    }
}
