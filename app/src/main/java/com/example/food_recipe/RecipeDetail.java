package com.example.food_recipe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

    LinearLayout ml;

    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        sv   = findViewById(R.id.sv);
        ml = new LinearLayout(this);
        findViewById(R.id.back).setOnClickListener(e->{
            finish();
        });
        findViewById(R.id.shareBtn).setBackgroundColor(Color.RED);
        findViewById(R.id.shareBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey. I got this recipe in Feast Flavours app. Do check out: "+getIntent().getStringExtra("url"));
                shareIntent.setType("text/plain");
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
// Verify that the intent will resolve to at least one activity
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
        ImageView shareImg = findViewById(R.id.shareImg);
        shareImg.setImageResource(R.drawable.share);
        Button back = findViewById(R.id.back);
        back.setBackgroundColor(Color.RED);
        LinearLayout shareBtn = findViewById(R.id.share);
        shareBtn.setBackgroundColor(Color.RED);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey. I got this recipe in Feast Flavours app. Do check out: "+getIntent().getStringExtra("url"));
                shareIntent.setType("text/plain");
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
// Verify that the intent will resolve to at least one activity
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
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
        String yield = detail.getStringExtra("yield");

        // Setting the recipe name and image
        TextView head = findViewById(R.id.head);
        ImageView image = findViewById(R.id.imageView);
        head.setText(name);
        Picasso.get().load(imageUrl).into(image);

//        servings
        TextView serving = findViewById(R.id.serving);
        serving.setText("Serving = "+yield);

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
        ml.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < ingredientsArray.length(); i++) {
            try {
                JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                LinearLayout ll2 = new LinearLayout(this);
                ImageView im = new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(250,250);
                ll2.setGravity(Gravity.CENTER_HORIZONTAL);
                lp.setMargins(10 , 0,0,0);
                im.setLayoutParams(lp);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    im.setForegroundGravity(Gravity.CENTER);
                }
                params.setMargins(0,10,0,10);
                ll2.setLayoutParams( params);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                ll2.setVerticalGravity(Gravity.CENTER_VERTICAL);
                LinearLayout forText = new LinearLayout(this);
                forText.setOrientation(LinearLayout.HORIZONTAL);
                TextView t = new TextView(this);
                TextView numbering = new TextView(this);
                numbering.setText(String.valueOf(i+1)+":   ");
                numbering.setTextSize(17);
                numbering.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium), Typeface.BOLD_ITALIC);
               forText.addView(numbering);
                forText.addView(t);
                t.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium));
                t.setLayoutParams(new LinearLayout.LayoutParams(
                        530, ViewGroup.LayoutParams.WRAP_CONTENT));
                t.setTextSize(17.5f);
                Picasso.get().load(ingredientObject.getString("image")).into(im);
                String text =  ingredientObject.getString("text") + " [" +
                        ingredientObject.getString("quantity") + " " +
                        ingredientObject.getString("measure") + "]";
                t.setText(text);
                ll2.addView(forText);
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
        LinearLayout first = findViewById(R.id.first);
        LinearLayout second = findViewById(R.id.second);
        first.setGravity(Gravity.CENTER);
        LinearLayout third = findViewById(R.id.third);
        LinearLayout fourth = findViewById(R.id.fourth);
        LinearLayout i1 = new LinearLayout(this);
        LinearLayout i2 = new LinearLayout(this);
        LinearLayout i3 = new LinearLayout(this);
        LinearLayout i4 = new LinearLayout(this);
        LinearLayout i5 = new LinearLayout(this);
        LinearLayout i6 = new LinearLayout(this);
        i1.setBackgroundColor(Color.YELLOW);
        i2.setBackgroundColor(Color.YELLOW);
        i3.setBackgroundColor(Color.YELLOW);
        i4.setBackgroundColor(Color.YELLOW);
        i5.setBackgroundColor(Color.YELLOW);
        i6.setBackgroundColor(Color.YELLOW);
        LinearLayout[] iArr = new LinearLayout[]{i1,i2,i3,i4 , i5 , i6};
        for(LinearLayout diff : iArr){
            diff.setPadding(15,15,15,15);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0,0,40,0);
        i1.setLayoutParams(params);
        i2.setLayoutParams(params);
        i3.setLayoutParams(params);
        i4.setLayoutParams(params);
        i5.setLayoutParams(params);
        i1.setOrientation(LinearLayout.VERTICAL);
        i2.setOrientation(LinearLayout.VERTICAL);
        i3.setOrientation(LinearLayout.VERTICAL);
        i4.setOrientation(LinearLayout.VERTICAL);
        i5.setOrientation(LinearLayout.VERTICAL);
        i6.setOrientation(LinearLayout.VERTICAL);
        first.addView(i1);
        first.addView(i2);
        first.addView(i3);
        second.addView(i4);
        second.addView(i5);
        second.addView(i6);
        while (nutrientKeys.hasNext()) {
            String key = nutrientKeys.next();
            try {
                JSONObject nutrient = nutrientsJson.getJSONObject(key);
                String label = nutrient.getString("label");
                String quantity = new DecimalFormat("0.00").format(nutrient.getDouble("quantity"));
                String unit = nutrient.getString("unit");
                TextView l = new TextView(this);
                TextView q = new TextView(this);
                l.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium) , Typeface.BOLD);
                l.setTextSize(16);
                l.setText(label);
                q.setText(String.valueOf(quantity) + " "+unit);
                q.setTypeface(ResourcesCompat.getFont( getApplicationContext(), R.font.opensansmedium));
                q.setTextSize(14);
                if(label.equals("Energy")){
                    i1.addView(l);
                    i1.addView(q);
                }else if(label.equals("Fat")){
                    i2.addView(l);
                    i2.addView(q);
                }else if(label.equals("Fiber")){
                    i3.addView(l);
                    i3.addView(q);
                }else if(label.equals("Sugars")){
                    i4.addView(l);
                    i4.addView(q);
                }else if(label.equals("Protein")){
                    i5.addView(l);
                    i5.addView(q);
                }else if(label.equals("Vitamin A")){
                    i6.addView(l);
                    i6.addView(q);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sv2.addView(ml2);
    }
}
