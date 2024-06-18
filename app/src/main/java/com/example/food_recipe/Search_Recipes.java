package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifTextView;

public class Search_Recipes extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private GifTextView loader ;
    TextView searchLabel;
    JSONObject jsonObject;
    JSONArray res = new JSONArray();

    private ArrayList<JSONObject> recipeList = new ArrayList<>();
    ArrayList<JSONObject> originalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipes);
        String[] selectedFilter = {""};
        Spinner spinner = findViewById(R.id.dropdownMenu);
        String[] dropdown = new String[]{"All","Veg","Non-Veg"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , dropdown);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFilter[0] = dropdown[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loader = (GifTextView) findViewById(R.id.loader);
//        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif").into(loader);
        Intent abc =getIntent();
        String searchTerm = abc.getStringExtra("search_term");
        searchLabel = findViewById(R.id.searchLabel);
        searchLabel.setTypeface(ResourcesCompat.getFont(getApplicationContext(),R.font.opensansmedium));
        searchLabel.setText(searchTerm);
        adapter = new RecipeAdapter(Search_Recipes.this,recipeList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loader.setVisibility(View.VISIBLE);

        Button filterBtn = findViewById(R.id.filterBtn);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = spinner.getSelectedItem().toString();
                if(val.equals("Veg")) {
                    JSONArray veg = new JSONArray();
                    for(int i = 0; i < res.length(); i++) {
                        try {
                            JSONObject ingredientObject2 = res.getJSONObject(i);
                            JSONObject recipe = ingredientObject2.getJSONObject("recipe");
                            JSONArray healthLabels = recipe.getJSONArray("healthLabels");
                            boolean isVegetarian = false;
                            for (int j = 0; j < healthLabels.length(); j++) {
                                String label = healthLabels.getString(j);
                                if(label.equals("Vegetarian") || label.equals("Vegan")) {
                                    isVegetarian = true;
                                    break;
                                }
                            }
                            if (isVegetarian) {
                                veg.put(recipe);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayList<JSONObject> vegList = new ArrayList<>();
                    for(int i = 0; i < veg.length(); i++) {
                        try {
                            vegList.add(veg.getJSONObject(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.updateList(vegList);
                    adapter.notifyDataSetChanged();
                } else if(val.equals("Non-Veg")) {
                    JSONArray nonVeg = new JSONArray();
                    for(int i = 0; i < res.length(); i++) {
                        try {
                            JSONObject ingredientObject = res.getJSONObject(i);
                            JSONObject recipe = ingredientObject.getJSONObject("recipe");
                            JSONArray healthLabels = recipe.getJSONArray("healthLabels");
                            boolean isNonVeg = true;
                            for (int j = 0; j < healthLabels.length(); j++) {
                                String label = healthLabels.getString(j);
                                if(label.equals("Vegetarian") || label.equals("Vegan")) {
                                    isNonVeg = false;
                                    break;
                                }
                            }
                            if (isNonVeg) {
                                nonVeg.put(ingredientObject);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayList<JSONObject> nonvegList = new ArrayList<>();
                    for(int i = 0; i < nonVeg.length(); i++) {
                        try {
                            nonvegList.add(nonVeg.getJSONObject(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.updateList(nonvegList);
                    adapter.notifyDataSetChanged();
                } else if(val.equals("All")) {
                    adapter.updateList(recipeList);
                    adapter.notifyDataSetChanged();
                }
            }
        });





        filterBtn.setBackgroundColor(Color.RED);
        // API call
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://vercel-food-api-rho.vercel.app/query/"+searchTerm,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++){
                            try {
                                res.put(response.getJSONObject(i));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        loader.setVisibility(View.GONE);
                        try {
                            if(response.length() > 1) {
                                for (int i = 0; i < response.length(); i++) {
                                     jsonObject = response.getJSONObject(i);
                                    recipeList.add(jsonObject);
                                  //  originalList.add(jsonObject);
                                    originalList = new ArrayList<>(recipeList);
                                }
                                adapter.notifyDataSetChanged();
                                // Notify adapter of data change
                            }
                            else{
                                startActivity(new Intent(getApplicationContext(),ErrorPage.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e){
                            Toast.makeText(getApplicationContext(),"Something wrong",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ErrorPage.class));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loader.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ErrorPage.class));
                    }
                }
        )

                ;
        requestQueue.add(jsonArrayRequest);
    }
}