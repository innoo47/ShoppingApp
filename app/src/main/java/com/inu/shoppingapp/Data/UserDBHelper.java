package com.inu.shoppingapp.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {

    private static UserDBHelper dbHelper = null;

    public static final String DATABASE_NAME = "userdb";
    public static final String TABLE_NAME = "user";
    public static final int DB_VERSION = 1;

    public static final String COL_0 = "serialNumber";
    public static final String COL_1 = "name";
    public static final String COL_2 = "email";
    public static final String COL_3 = "id";
    public static final String COL_4 = "password";
    public static final String COL_5 = "gender";
    public static final String COL_6 = "years";

    public static UserDBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new UserDBHelper(context.getApplicationContext());
        }

        return dbHelper;
    }

    private UserDBHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    /*
    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ("
                + COL_0 + " integer primary key autoincrement, "
                + COL_1 + " text not null,"
                + COL_2 + " text not null check (email like '%@%'),"
                + COL_3 + " text not null unique,"
                + COL_4 + " text not null,"
                + COL_5 + " text not null,"
                + COL_6 + " text not null "
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public long insertUser(UserBean user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COL_1, user.getName());
        value.put(COL_2, user.getEmail());
        value.put(COL_3, user.getId());
        value.put(COL_4, user.getPassword());
        value.put(COL_5, user.getGender());
        value.put(COL_6, user.getYears());

        return db.insert(TABLE_NAME, null, value);
    }

//    public ArrayList<UserBean> getAllUser(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
//        ArrayList<UserBean> result = new ArrayList<>();
//
//        while(cursor.moveToNext()){
//            UserBean user = new UserBean();
//            user.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
//            user.setName(cursor.getString(cursor.getColumnIndex(COL_1)));
//            user.setEmail(cursor.getString(cursor.getColumnIndex(COL_2)));
//            user.setId(cursor.getString(cursor.getColumnIndex(COL_3)));
//            user.setPassword(cursor.getString(cursor.getColumnIndex(COL_4)));
//            user.setGender(cursor.getString(cursor.getColumnIndex(COL_5)));
//            user.setYears(cursor.getString(cursor.getColumnIndex(COL_6)));
//            result.add(user);
//        }
//
//        return result;
//    }
//
//    public ArrayList<UserBean> getUserById(String id){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
//        ArrayList<UserBean> result = new ArrayList<>();
//
//        while(cursor.moveToNext()){
//            UserBean user = new UserBean();
//
//            user.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
//            user.setName(cursor.getString(cursor.getColumnIndex(COL_1)));
//            user.setEmail(cursor.getString(cursor.getColumnIndex(COL_2)));
//            user.setId(cursor.getString(cursor.getColumnIndex(COL_3)));
//            user.setPassword(cursor.getString(cursor.getColumnIndex(COL_4)));
//            user.setGender(cursor.getString(cursor.getColumnIndex(COL_5)));
//            user.setYears(cursor.getString(cursor.getColumnIndex(COL_6)));
//
//            result.add(user);
//        }
//
//        return result;
//    }

    @SuppressLint("Range")
    public String getUserId(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_3));
        }

        return result;
    }

    @SuppressLint("Range")
    public String getUserPw(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_4));
        }

        return result;
    }

    @SuppressLint("Range")
    public String getUsername(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_1));
        }

        return result;
    }

    @SuppressLint("Range")
    public String getUserEmail(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_2));
        }

        return result;
    }

    @SuppressLint("Range")
    public String getUserGender(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_5));
        }

        return result;
    }

    @SuppressLint("Range")
    public String getUserBirthday(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_6));
        }

        return result;
    }

    // 전체 삭제 -> 현재 유저만 삭제
    public long deleteUser(UserBean bean){
        SQLiteDatabase db = getWritableDatabase();
        String serial = String.valueOf(bean.getSerialNumber());

        return db.delete(TABLE_NAME, COL_0 + "=?", new String[] {serial});
    }
}