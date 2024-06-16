package com.example.food_recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SearchHistory extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_history);
        ListView searches = findViewById(R.id.searches);
        ImageView deleteIcon = findViewById(R.id.deleteIcon);
        LinearLayout deleteLay = findViewById(R.id.deleteLay);
        SharedPreferences preferences = getSharedPreferences("history",Context.MODE_PRIVATE);
        if(preferences.getString("first","") == ""){
           deleteLay.setVisibility(View.GONE);
        }
        deleteLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().clear().apply();
                recreate();
            }
        });
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5PED8EDuL1-CrC0hTbDEoLzeNpDCr7RzCbQ&s").into(deleteIcon);
        try {
           // SharedPreferences preferences = getSharedPreferences("history", Context.MODE_PRIVATE);
            String first = preferences.getString("first","") != "" ? preferences.getString("first","") : "-";
            String second = preferences.getString("second","") != "" ? preferences.getString("second","") : "-";
            String third = preferences.getString("third","") != "" ? preferences.getString("third","") : "-";
            String fourth = preferences.getString("fourth","") != "" ? preferences.getString("fourth","") : "-";
            String fifth = preferences.getString("fifth","") != "" ? preferences.getString("fifth","") : "-";
            String sixth = preferences.getString("sixth","") != "" ? preferences.getString("sixth","") : "-";
            String seventh = preferences.getString("seventh","") != "" ? preferences.getString("seventh","") : "-";
            String eight = preferences.getString("eight","") != "" ? preferences.getString("eight","") : "-";
            String ninth = preferences.getString("ninth","") != "" ? preferences.getString("ninth","") : "-";
            String tenth = preferences.getString("tenth","") != "" ? preferences.getString("tenth","") : "-";
            String eleven = preferences.getString("eleven","") != "" ? preferences.getString("eleven","") : "-";
            String twelve = preferences.getString("twelve","") != "" ? preferences.getString("twelve","") : "-";
            String thirteen = preferences.getString("thirteen","") != "" ? preferences.getString("thirteen","") : "-";
            String fourteen = preferences.getString("fourteen","") != "" ? preferences.getString("fourteen","") : "-";
            String fifteen = preferences.getString("fifteen","") != "" ? preferences.getString("fifteen","") : "-";
            String sixteen = preferences.getString("sixteen","") != "" ? preferences.getString("sixteen","") : "-";
            String seventeen = preferences.getString("seventeen","") != "" ? preferences.getString("seventeen","") : "-";
            String eighteen = preferences.getString("eighteen","") != "" ? preferences.getString("eighteen","") : "-";
            String nineteen = preferences.getString("nineteen","") != "" ? preferences.getString("nineteen","") : "-";
            String twenty = preferences.getString("twenty","") != "" ? preferences.getString("twenty","") : "-";
            String[] historyArray = new String[]{first , second , third , fourth , fifth , sixth , seventh , eight , ninth , tenth , eleven , twelve , thirteen , fourteen , fifteen , sixteen , seventeen , eighteen , nineteen , twenty};
            for (String i:
                 historyArray) {
                Log.d("array:",i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_items,historyArray);
            searches.setAdapter(adapter);
            searches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent searchActivity = new Intent(getApplicationContext(), Search_Recipes.class);
                    searchActivity.putExtra("search_term",historyArray[i]);
                    if(!historyArray[i].equals("-"))
                    {
                    startActivity(searchActivity);
                }
                }
            });
//            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());
//            Set<String> searchHistory = sharedPreferencesHelper.getSearchHistory();
//            //Collections.reverse(searchHistory);
//            List<String> mainList = new ArrayList<String>();
//            mainList.addAll(searchHistory);
//            Toast.makeText(getApplicationContext(), mainList.toString(), Toast.LENGTH_SHORT).show();
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, mainList);
//            searches.setAdapter(adapter);
        }
    catch(Exception e){
            Toast.makeText(getApplicationContext(),"Some error occurred",Toast.LENGTH_SHORT).show();
    }
    }
}
