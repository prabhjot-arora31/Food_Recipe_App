package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_recipe.model.User;
import com.example.food_recipe.model.UserContract;
import com.example.food_recipe.model.UserDbHelper;
public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);
        Button register = findViewById(R.id.registerBtn);
        EditText email = findViewById(R.id.ed1);
        EditText password = findViewById(R.id.ed2);
        register.setOnClickListener(v->{
            Intent registerActivity = new Intent(LoginScreen.this,MainActivity.class);
            startActivity(registerActivity);
        });

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                if(email1.isEmpty() || password1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter all the fields....", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = getUserFromDatabase(email1);
                if(user != null && user.getPassword().equals(password1)){
                    SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_name",user.getName());
                    editor.putString("email",email1);
                    editor.putString("phone",user.getPhone());
                    editor.putLong("expiry",System.currentTimeMillis()+5*24*60*60*1000);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(LoginScreen.this, Home.class);
                    startActivity(home);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private User getUserFromDatabase(String email) {
        // Perform a database query to retrieve the user's information based on the provided email
        User user = null;
        UserDbHelper userDbHelper = new UserDbHelper(getApplicationContext());
        // Open a readable database
        SQLiteDatabase db =  userDbHelper.getReadableDatabase();

        // Define a projection that specifies the columns from the database
        String[] projection = {
                UserContract.UserEntry.user_email,
                UserContract.UserEntry.user_password,
                UserContract.UserEntry.user_name,
                UserContract.UserEntry.user_phone
        };

        // Filter results WHERE "email" = email
        String selection = UserContract.UserEntry.user_email + " = ?";
        String[] selectionArgs = { email };

        // Perform the query
        Cursor cursor = db.query(
                UserContract.UserEntry.table_name,   // The table to query
                projection,                          // The columns to return
                selection,                           // The columns for the WHERE clause
                selectionArgs,                       // The values for the WHERE clause
                null,                                // Don't group the rows
                null,                                // Don't filter by row groups
                null                                 // The sort order
        );

        // Extract user information from the cursor
        if (cursor != null && cursor.moveToFirst()) {
            String useremail = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.user_email));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.user_password));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.user_name));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.user_phone));
            user = new User(useremail, password);
            user.setName(name);
            user.setPhone(phone);
            cursor.close();
        }

        return user;
    }
}
