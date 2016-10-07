package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DataBaseAdapter extends SQLiteOpenHelper {


    private static final String TAG=DataBaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME="registered_users.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_USER="user";
    private static final String KEY_ID="id";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_LASTNAME="lname";
    private static final String KEY_FIRSTNAME="fname";
    private static final String KEY_USERNAME="uname";


    public DataBaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE="CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT," +KEY_LASTNAME+ " TEXT," +KEY_FIRSTNAME + " TEXT," +KEY_USERNAME + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String registeremail(String email, String password,String uname,String fname,String lname) {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + email + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("email", cursor.getString(1));
        }
        cursor.close();
        db.close();

        if (email.equals(user.get("email"))) {
            return "Email already in used.";
        } else {
            SQLiteDatabase wdb = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, email);
            values.put(KEY_PASSWORD, password);
            values.put(KEY_USERNAME, uname);
            values.put(KEY_LASTNAME, lname);
            values.put(KEY_FIRSTNAME, fname);

            long id = wdb.insert(TABLE_USER, null, values);
            db.close();

            return "User successfully added";
        }
    }
    public String registerUsername(String email, String password,String uname,String fname,String lname) {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME + "=\"" + uname + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("uname", cursor.getString(5));
        }
        cursor.close();
        db.close();

        if (uname.equals(user.get("uname"))) {
            return "Username already in used.";
        } else {
            SQLiteDatabase wdb = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, email);
            values.put(KEY_PASSWORD, password);
            values.put(KEY_USERNAME, uname);
            values.put(KEY_LASTNAME, lname);
            values.put(KEY_FIRSTNAME, fname);

            long id = wdb.insert(TABLE_USER, null, values);
            db.close();

            return "User successfully added";
        }
    }


    public String registerUser(String fname, String lname, String uname, String email, String password) {
        SQLiteDatabase wdb = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, uname);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_FIRSTNAME, fname);
        values.put(KEY_LASTNAME, lname);

        long id = wdb.insert(TABLE_USER, null, values);
        wdb.close();

        Log.d(TAG, "Successfully added user: " + id);
        Log.d(TAG, "Successfully added user: " + email);
        Log.d(TAG, "Successfully added user: " + password);
        return "User successfully added";
    }



    public boolean validateuname(String uname){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME + "=\"" + uname + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }
    public boolean validateemail(String email){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + email + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }
    public boolean validateEmail(String email, String pwd){
        HashMap<String, String> user=new HashMap<String, String>();
        String selectQuery="SELECT * FROM "+TABLE_USER+" WHERE "+KEY_EMAIL+"=\""+email+"\"";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){

            user.put("uname", cursor.getString(5));
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
        }
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        if (email.equals(user.get("email"))&& pwd.equals(user.get("password"))){
            return true;
        }
        else {

            Log.d(TAG,"does not exist");
            return false;
        }
    }
    public boolean validateUsername(String email, String pwd){
        HashMap<String, String> user=new HashMap<String, String>();
        String selectQuery="SELECT * FROM "+TABLE_USER+" WHERE "+KEY_USERNAME+"=\""+email+"\"";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){

            user.put("uname", cursor.getString(5));
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
        }
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        if (email.equals(user.get("uname"))&& pwd.equals(user.get("password"))){
            return true;
        }
        else {

            Log.d(TAG,"does not exist");
            return false;
        }
    }

}
