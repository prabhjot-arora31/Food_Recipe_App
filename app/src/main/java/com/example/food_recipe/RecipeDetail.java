package com.example.food_recipe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Iterator;

public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        findViewById(R.id.back).setOnClickListener(e->{
            finish();
        });
        Button back = findViewById(R.id.back);
        back.setBackgroundColor(Color.RED);
        findViewById(R.id.web).setBackgroundColor(Color.RED);
        findViewById(R.id.web).setOnClickListener(e->{
           // Toast.makeText(getApplicationContext(),"Yo",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, WebsiteView.class);
            i.putExtra("url",getIntent().getStringExtra("url"));
            startActivity(i);
        });
        // Getting data from the intent
        Intent detail = getIntent();
        String imageUrl = detail.getStringExtra("imageUrl");
        String name = detail.getStringExtra("name");
        String ingredientString = detail.getStringExtra("ingredients");
        String nutrientString = detail.getStringExtra("nutrients");

        // Setting the recipe name and image
        TextView head = findViewById(R.id.head);
        ImageView image = findViewById(R.id.imageView);
        head.setText(name);
        Picasso.get().load(imageUrl).into(image);

        // Parsing nutrients JSON
        JSONObject nutrientsJson = null;
        try {
            nutrientsJson = new JSONObject(nutrientString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Parsing ingredients JSON
        JSONArray ingredientsArray = null;
        try {
            ingredientsArray = new JSONArray(ingredientString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Adding ingredients to the ScrollView
        ScrollView sv = findViewById(R.id.sv);
        LinearLayout ml = new LinearLayout(this);
        ml.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < ingredientsArray.length(); i++) {
            try {
                JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                LinearLayout ll2 = new LinearLayout(this);
                ImageView im = new ImageView(this);
                im.setLayoutParams(new LinearLayout.LayoutParams(250,250));
               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                       ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    im.setForegroundGravity(Gravity.CENTER);
                }
                params.setMargins(0,0,0,10);
               ll2.setLayoutParams( params);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                ll2.setVerticalGravity(Gravity.CENTER_VERTICAL);
                TextView t = new TextView(this);
                t.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium));
                t.setLayoutParams(new LinearLayout.LayoutParams(
                        565, ViewGroup.LayoutParams.WRAP_CONTENT));
                t.setTextSize(17.5f);
                Picasso.get().load(ingredientObject.getString("image")).into(im);
                String text = i + 1 + ": " + ingredientObject.getString("text") + " [" +
                        ingredientObject.getString("quantity") + " " +
                        ingredientObject.getString("measure") + "]";
                t.setText(text);
                ll2.addView(t);
                ll2.addView(im);
                ml.addView(ll2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sv.addView(ml);

        // Adding nutrients to the ScrollView
        ScrollView sv2 = findViewById(R.id.sv2);
        LinearLayout ml2 = new LinearLayout(this);
        ml2.setOrientation(LinearLayout.VERTICAL);
        Iterator<String> nutrientKeys = nutrientsJson.keys();
        int i=1;
        while (nutrientKeys.hasNext()) {

            String key = nutrientKeys.next();
            try {
                JSONObject nutrient = nutrientsJson.getJSONObject(key);
                String label = nutrient.getString("label");
                double quantity = nutrient.getDouble("quantity");
                String unit = nutrient.getString("unit");
                if(label.equals("Energy") || label.equals("Fat") || label.equals("Carbohydrates (net)") || label.equals("Fiber") || label.equals("Sugars") || label.equals("Protein") || label.equals("Vitamin A") || label.equals("Water")) {
                    TextView t2 = new TextView(this);
                    t2.setTextSize(17.5f);
                    t2.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium));
                    t2.setText(i + ". " + label + " = " + new DecimalFormat("0.00").format(quantity) + " " + unit);
                    ml2.addView(t2);
                    i++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sv2.addView(ml2);
    }
}
