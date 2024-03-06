package com.example.food_recipe.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String db_name = "users.db";
    private static final int db_version  = 1;
    public UserDbHelper(Context ctx){
        super(ctx,db_name,null,db_version);
    }
    @Override
   public void onCreate(@NotNull SQLiteDatabase db){
final String create_table = "create table "+UserContract.UserEntry.table_name+"("+ UserContract.UserEntry._ID+" integer primary key autoincrement,"+
        UserContract.UserEntry.user_name+" text not null,"+UserContract.UserEntry.user_email+" text not null,"+UserContract.UserEntry.user_phone+" text not null,"+UserContract.UserEntry.user_password+" text not null"+");";
db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.table_name);
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
