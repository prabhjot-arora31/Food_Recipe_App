package com.example.food_recipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Activity context;
    private ArrayList<JSONObject> recipes;
    public RecipeAdapter(Activity ctx, ArrayList<JSONObject> recipes){
this.context = ctx;
this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        try {
            JSONObject recipe = recipes.get(position);
            String name = recipe.getJSONObject("recipe").getString("label");
            String imageUrl = recipe.getJSONObject("recipe").getString("image");
            JSONArray mealType = recipe.getJSONObject("recipe").getJSONArray("mealType");
            String url = recipe.getJSONObject("recipe").getString("url");
            holder.recipeName.setText(name);
            holder.mealType.setText(mealType.getString(0));

            holder.view_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context != null) {
                        Intent recipe_detail = new Intent(context, RecipeDetail.class);
                        recipe_detail.putExtra("url",url);
                        context.startActivity(recipe_detail);
                    }else{
                        Log.d("ctx","Error");
                    }
                }
            });
            Picasso.get().load(imageUrl).
            transform(new CircleTransformationImageButton()).
                    into(holder.recipeImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeName;
        TextView mealType;
        Button view_btn;
        String url;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
            mealType = itemView.findViewById(R.id.mealType);
            view_btn = itemView.findViewById(R.id.view_btn);
        }
    }
}
