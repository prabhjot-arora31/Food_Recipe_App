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

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Activity context;
    private ArrayList<JSONObject> recipes;
    public RecipeAdapter(Activity ctx, ArrayList<JSONObject> recipes){
this.context = ctx;
this.recipes = recipes;
    }
    private int pos;
    public void updateList(ArrayList<JSONObject> newList) {
        this.recipes = newList;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        try {
            if(!recipes.get(pos).getJSONObject("recipe").getString("label").equals("")) {
                 view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
            }
            else{
                view = LayoutInflater.from(context).inflate(R.layout.empty_view,parent , false);
            }
        } catch (JSONException e) {
            view = LayoutInflater.from(context).inflate(R.layout.empty_view,parent,false);
          //  throw new RuntimeException(e);
        }
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        try {
            this.pos = pos;
            try {
                boolean condition = recipes.get(pos).getJSONObject("recipe").getString("label").equals("");
                if (!condition) {
                    JSONObject recipe = recipes.get(position);
                    String name = recipe.getJSONObject("recipe").getString("label");
                    String imageUrl = recipe.getJSONObject("recipe").getString("image");
                    JSONArray mealType = recipe.getJSONObject("recipe").getJSONArray("mealType");
                    String url = recipe.getJSONObject("recipe").getString("url");
                    JSONArray ingredients = recipe.getJSONObject("recipe").getJSONArray("ingredients");
                    JSONObject nutrients = recipe.getJSONObject("recipe").getJSONObject("totalNutrients");
                    String servings = recipe.getJSONObject("recipe").getString("yield");
                    if (name.length() >= 14) {
                        holder.recipeName.setText(name.substring(0, 14) + "...");
                    } else {
                        holder.recipeName.setText(name);
                    }
                    holder.mealType.setText(mealType.getString(0));
                    // holder.view_btn.setBackgroundColor(Color.RED);
                    holder.view_btn.setBackgroundColor(Color.parseColor("#FF0000"));
                    holder.view_btn.setTextColor(Color.WHITE);

                    holder.view_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (context != null) {
                                Intent recipe_detail = new Intent(context, RecipeDetail.class);
                                recipe_detail.putExtra("url", url);
                                recipe_detail.putExtra("name", name);
                                recipe_detail.putExtra("imageUrl", imageUrl);
                                recipe_detail.putExtra("ingredients", ingredients.toString());
                                recipe_detail.putExtra("nutrients", nutrients.toString());
                                recipe_detail.putExtra("yield",servings.toString());
                                try {
                                    recipe_detail.putExtra("mealType", mealType.getString(0).toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                context.startActivity(recipe_detail);
                            } else {
                                Log.d("ctx", "Error");
                            }
                        }
                    });
                    Picasso.get().load(imageUrl).
                            transform(new CircleTransformationImageButton()).
                            into(holder.recipeImage);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
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
