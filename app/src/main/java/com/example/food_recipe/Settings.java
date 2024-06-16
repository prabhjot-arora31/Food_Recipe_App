package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView profileImg = findViewById(R.id.profileImg);
        ImageView aboutImg = findViewById(R.id.aboutImg);
        ImageView logoutImg = findViewById(R.id.logoutImg);
        ImageView historyImg = findViewById(R.id.historyImg);
        Picasso.get().load("https://as2.ftcdn.net/v2/jpg/05/20/81/43/1000_F_520814381_q7V5TJGenYeakH8TsJJtM8SnwEb4QIoL.jpg").into(historyImg);
        LinearLayout history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SearchHistory.class));
            }
        });
        LinearLayout logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("expiry");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                finish();
            }
        });
        Picasso.get().load("https://www.kindpng.com/picc/m/19-194798_transparent-logout-png-sign-out-icon-transparent-png.png").into(logoutImg);
        Picasso.get().load("https://static.thenounproject.com/png/4181324-200.png").into(aboutImg);
        LinearLayout about = findViewById(R.id.about);
        LinearLayout privacyPolicy = findViewById(R.id.privacy);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PrivacyPolicy.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(getApplicationContext(), aboutUsPage.class);
                startActivity(profile);
            }
        });
        ImageView privacyImg = findViewById(R.id.privacyImg);
        Picasso.get().load("https://static.vecteezy.com/system/resources/previews/019/551/974/non_2x/privacy-policy-valid-document-concept-icon-in-line-style-design-isolated-on-white-background-editable-stroke-vector.jpg").into(privacyImg);
        Picasso.get().load("https://static.vecteezy.com/system/resources/previews/002/318/271/non_2x/user-profile-icon-free-vector.jpg").into(profileImg);
        LinearLayout profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(getApplicationContext(), Profile.class);
                startActivity(profile);
            }
        });
    }
}