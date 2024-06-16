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
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
         if(itemID == R.id.settings){
            Intent settings = new Intent(getApplicationContext(),Settings.class);
            startActivity(settings);
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
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (haveNetworkConnection()) {
             setContentView(R.layout.activity_home);
             Log.d("netStatus",String.valueOf(haveNetworkConnection()));
            Toast.makeText(getApplicationContext(),"The net status is:"+haveNetworkConnection(),Toast.LENGTH_SHORT).show();
            searchBox = findViewById(R.id.searchBox);
            welcomeText = findViewById(R.id.welcomeText);
            searchBtn = findViewById(R.id.searchBtn);
            searchBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
//        SEARCH SECTION
            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (searchBox.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter some food to search....", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());
                        String query = searchBox.getText().toString().trim();
                        SharedPreferences preferences = getSharedPreferences("history", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
//                  Toast.makeText(getApplicationContext(),"first:"+preferences.getString("first",""),Toast.LENGTH_SHORT).show();
                        if (preferences.getString("first", "") == "") {
                            editor.putString("first", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("second", "") == "") {
                            editor.putString("second", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("third", "") == "") {
                            editor.putString("third", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("fourth", "") == "") {
                            editor.putString("fourth", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("fifth", "") == "") {
                            editor.putString("fifth", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("sixth", "") == "") {
                            editor.putString("sixth", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("seventh", "") == "") {
                            editor.putString("seventh", query);
                            editor.apply();
                            //Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("eight", "") == "") {
                            editor.putString("eight", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("ninth", "") == "") {
                            editor.putString("ninth", query);
                            editor.apply();
                            //   Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("tenth", "") == "") {
                            editor.putString("tenth", query);
                            editor.apply();
                            //   Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("eleven", "") == "") {
                            editor.putString("eleven", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("twelve", "") == "") {
                            editor.putString("twelve", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("thirteen", "") == "") {
                            editor.putString("thirteen", query);
                            editor.apply();
                            //   Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("fourteen", "") == "") {
                            editor.putString("fourteen", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("fifteen", "") == "") {
                            editor.putString("fifteen", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("sixteen", "") == "") {
                            editor.putString("sixteen", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("seventeen", "") == "") {
                            editor.putString("seventeen", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("eighteen", "") == "") {
                            editor.putString("eighteen", query);
                            editor.apply();
                            //Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("nineteen", "") == "") {
                            editor.putString("nineteen", query);
                            editor.apply();
                            //  Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        } else if (preferences.getString("twenty", "") == "") {
                            editor.putString("twenty", query);
                            editor.apply();
                            // Toast.makeText(getApplicationContext(),"Saved:",Toast.LENGTH_SHORT).show();
                        }
                        //sharedPreferencesHelper.saveSearchQuery(query);
                        Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                        searchActivity.putExtra("search_term", query);
                        startActivity(searchActivity);
                    }
                }});

//        END SEARCH SECTION
            SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
            String user_name = sharedPreferences.getString("user_name", null);
            welcomeText.setText("Welcome " + user_name);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
//        toolbar.setTitleTextColor(Color.red());
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_red_dark));
            ActionBar action = getSupportActionBar();
            //   Button logoutBtn = findViewById(R.id.logoutBtn);
            ImageButton ib1 = findViewById(R.id.ib1);
            ImageButton spicy = findViewById(R.id.spicy);
            ImageButton chicken = findViewById(R.id.chicken);
            HorizontalScrollView hsc = findViewById(R.id.hsv);
            ImageButton ni = findViewById(R.id.northindian);
            ImageButton si = findViewById(R.id.southindian);
            ImageButton french = findViewById(R.id.french);
            ImageButton german = findViewById(R.id.german);
            ImageButton italic = findViewById(R.id.italic);
            ImageButton japanese = findViewById(R.id.japanese);
            german.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "german");
                    startActivity(searchActivity);
                }
            });
            italic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "italic");
                    startActivity(searchActivity);
                }
            });
            japanese.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "japanese");
                    startActivity(searchActivity);
                }
            });
            ni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "north indian");
                    startActivity(searchActivity);
                }
            });
            si.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "south indian");
                    startActivity(searchActivity);
                }
            });
            french.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term", "french");
                    startActivity(searchActivity);
                }
            });
            Picasso.get().load("https://media.cnn.com/api/v1/images/stellar/prod/190715182611-19-germany-dishes-gallery-restricted.jpg?q=w_2700,h_1800,x_0,y_0,c_fill").transform(new CircleTransformationImageButton()).into(german);
            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlfd_A24xAau5fvbiyXts9XURIIf_ObxAOxQ&s").transform(new CircleTransformationImageButton()).into(italic);
            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGNDI_jP4yD1b0qyzRJ3ccHjN4U8wpQGYpZw&s").transform(new CircleTransformationImageButton()).into(japanese);
            Picasso.get().load("https://www.willflyforfood.net/wp-content/uploads/2021/12/french-pastries-eclair.jpg.webp").transform(new CircleTransformationImageButton()).into(french);
            Picasso.get().load("https://vaya.in/recipes/wp-content/uploads/2019/04/Traditional-South-Indian-Dishes.jpg").transform(new CircleTransformationImageButton()).into(si);
            Picasso.get().load("https://static.toiimg.com/photo/96590900/96590900.jpg").transform(new CircleTransformationImageButton()).into(ni);
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
                    .into(spicy, new Callback() {
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
                    Intent sweets = new Intent(getApplicationContext(), Sweets.class);
                    startActivity(sweets);
                }
            });
            spicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent spicy = new Intent(getApplicationContext(), Spicy.class);
                    startActivity(spicy);
                }
            });
            chicken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent chicken = new Intent(getApplicationContext(), ChickenView.class);
                    startActivity(chicken);
                }
            });
        }
        else{
            Log.d("netStatus",String.valueOf(haveNetworkConnection()));
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
            builder1.setTitle("Network Unavailable");
            builder1.setMessage("Please try connecting again.....");
            builder1.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder1.create();
            alertDialog.show();
        }
    }
    }
