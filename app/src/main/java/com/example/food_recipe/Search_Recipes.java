package com.example.food_recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

import java.util.ArrayList;

import pl.droidsonroids.gif.GifTextView;

public class Search_Recipes extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private GifTextView loader ;
    TextView searchLabel;
    private ArrayList<JSONObject> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipes);
        loader = (GifTextView) findViewById(R.id.loader);
//        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif").into(loader);
        Intent abc =getIntent();
        String searchTerm = abc.getStringExtra("search_term");
        searchLabel = findViewById(R.id.searchLabel);

        searchLabel.setText(searchTerm);
        adapter = new RecipeAdapter(Search_Recipes.this,recipeList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loader.setVisibility(View.VISIBLE);
        // API call
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://vercel-food-api-rho.vercel.app/query/"+searchTerm,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loader.setVisibility(View.GONE);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                recipeList.add(jsonObject);
                            }
                            adapter.notifyDataSetChanged(); // Notify adapter of data change
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loader.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                }
        )

                ;
        requestQueue.add(jsonArrayRequest);
    }
}