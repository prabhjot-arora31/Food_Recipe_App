package com.example.food_recipe;

import androidx.annotation.NonNull;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import  androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    private  AlertDialog.Builder builder;

    private EditText searchBox;
    private TextView welcomeText;
    private Button searchBtn;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menus,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();
        if(itemID == R.id.helpOption){
            Intent help = new Intent(getApplicationContext(), aboutUsPage.class);
            startActivity(help);
            return true;
        } else if(itemID == R.id.logoutBtn){
            SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("email");
            editor.remove("expiry");
            editor.apply();
            startActivity(new Intent(Home.this,LoginScreen.class));
            finish();
            return  true;
        } else if(itemID == R.id.settings){
            Intent settings = new Intent(getApplicationContext(),Settings.class);
            startActivity(settings);
        }
        else if(itemID == R.id.profile){
Intent profile = new Intent(this, Profile.class);
startActivity(profile);
        }
        return  true;
    }
    @Override
    public void onBackPressed(){
        if(!isFinishing()) {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert!!!").
            setMessage("Are you sure you want to exit the app?").
                    setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }else{
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchBox = findViewById(R.id.searchBox);
        welcomeText = findViewById(R.id.welcomeText);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
//        SEARCH SECTION
          searchBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                  searchActivity.putExtra("search_term",searchBox.getText().toString());
                  startActivity(searchActivity);
              }
          });
//        END SEARCH SECTION
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name",null);
        welcomeText.setText("Welcome "+user_name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitleTextColor(Color.red());
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_red_dark));
    ActionBar action = getSupportActionBar();
        Button logoutBtn = findViewById(R.id.logoutBtn);
        ImageButton ib1 = findViewById(R.id.ib1);
        ImageButton spicy = findViewById(R.id.spicy);
        ImageButton chicken = findViewById(R.id.chicken);
        HorizontalScrollView hsc = findViewById(R.id.hsv);
        hsc.setHorizontalScrollBarEnabled(false);
        String image1 = "https://images.unsplash.com/photo-1528207776546-365bb710ee93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        String image2 = "https://bigoven-res.cloudinary.com/image/upload/t_recipe-1280/hot-spicy-menudo.jpg";
        String image3 = "https://images.unsplash.com/photo-1606728035253-49e8a23146de?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        Picasso.get().load(image1)
                .transform(new CircleTransformationImageButton())
                .into(ib1, new Callback() {
            @Override
            public void onSuccess() {
                System.out.println("Done loading ");
//                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                System.out.println("not Done loading ");
//                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });
        Picasso.get().load(image2)
                .transform(new CircleTransformationImageButton())
                .into(spicy,new Callback() {
            @Override
            public void onSuccess() {
                System.out.println("Done loading ");
//                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                System.out.println("not Done loading ");
//                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });
        Picasso.get().load(image3)
                .transform(new CircleTransformationImageButton())
                .into(chicken, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Done loading ");
//                        Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("not Done loading ");
//                        Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                    }
                });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sweets = new Intent(getApplicationContext(),Sweets.class);
                startActivity(sweets);
            }
        });
        spicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spicy = new Intent(getApplicationContext(),Spicy.class);
                startActivity(spicy);
            }
        });
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chicken = new Intent(getApplicationContext(),ChickenView.class);
                startActivity(chicken);
            }
        });
    }
}