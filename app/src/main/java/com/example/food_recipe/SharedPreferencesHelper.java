package com.example.food_recipe;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesHelper {

    private static final String SEARCH_HISTORY_PREF = "search_history_pref";
    private static final String SEARCH_HISTORY_KEY = "search_history";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(SEARCH_HISTORY_PREF, Context.MODE_PRIVATE);
    }

    // Save a search query to SharedPreferences
    public void saveSearchQuery(String query) {
        Set<String> history = getSearchHistory();
        if (history == null) {
            history = new HashSet<>();
        }
        history.add(query);

        sharedPreferences.edit().putStringSet(SEARCH_HISTORY_KEY, history).apply();
    }

    // Get the list of search history from SharedPreferences
    public Set<String> getSearchHistory() {
      return sharedPreferences.getStringSet(SEARCH_HISTORY_KEY, null);

    }

    // Clear search history in SharedPreferences
    public void clearSearchHistory() {
        sharedPreferences.edit().remove(SEARCH_HISTORY_KEY).apply();
    }
}
