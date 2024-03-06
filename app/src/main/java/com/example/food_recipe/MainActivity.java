package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.food_recipe.model.UserContract;
import com.example.food_recipe.model.UserDbHelper;

public class MainActivity extends AppCompatActivity {
   private UserDbHelper userdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isLoggedIn()){
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }else {
        SQLiteDatabase db  = getApplicationContext().openOrCreateDatabase("users.db", Context.MODE_PRIVATE,null);
        userdbHelper = new UserDbHelper(getBaseContext());
        Button register = findViewById(R.id.registerBtn);
        Button login = findViewById(R.id.loginBtn);
        EditText name = findViewById(R.id.ed1);
        EditText email = findViewById(R.id.ed2);
        EditText phone = findViewById(R.id.ed3);
        EditText password = findViewById(R.id.ed4);
        ProgressBar loader = findViewById(R.id.loader);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loader.setVisibility(View.VISIBLE);
                String name1 = name.getText().toString().trim();
                String email1 = email.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                if(name1.isEmpty() || email1.isEmpty() || phone1.isEmpty() || password1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter all the fields...",Toast.LENGTH_LONG).show();
                    loader.setVisibility(View.INVISIBLE);
                    return;
                }
                SQLiteDatabase db = userdbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(UserContract.UserEntry.user_name,name1);
                values.put(UserContract.UserEntry.user_email,email1);
                values.put(UserContract.UserEntry.user_phone,phone1);
                values.put(UserContract.UserEntry.user_password,password1);
                long newRowId = db.insert(UserContract.UserEntry.table_name,null,values);
                if(newRowId == -1){
                    Toast.makeText(getBaseContext(),"User Registration failed!!",Toast.LENGTH_SHORT).show();
                }else{
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                             Intent login = new Intent(getApplicationContext(),LoginScreen.class);
                             startActivity(login);
                        }
                    };
                    handler.postDelayed(runnable,2500);
                    Toast.makeText(getApplicationContext(),"User Registration Success",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    password.setText("");
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(login);
                finish();
            }
        });
    }}
    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
         Long expiry = sharedPreferences.getLong("expiry",0);
         if(System.currentTimeMillis() < expiry){
             return  true;
         }else{
             return  false;
         }
    }
}