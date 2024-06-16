package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView profile = findViewById(R.id.user);
        TextView profileName = findViewById(R.id.profileName);
        TextView profilePhone = findViewById(R.id.profilePhone);
        profilePhone.setPaintFlags(profilePhone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    profileName.setPaintFlags(profileName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        profilePhone.setText(sharedPreferences.getString("phone",""));
        String name = sharedPreferences.getString("user_name","");
        profileName.setText(name);
        TextView profileEmail = findViewById(R.id.profileEmail);
        profileEmail.setPaintFlags(profileEmail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        profileEmail.setText(sharedPreferences.getString("email",""));
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9abi43mES5pm_vCUKaeoTsjpgOsoI_WlZiw&s").into(profile);
    }
}