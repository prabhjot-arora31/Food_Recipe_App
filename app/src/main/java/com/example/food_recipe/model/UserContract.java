package com.example.food_recipe.model;

import android.provider.BaseColumns;

public class UserContract {
    public static class UserEntry implements BaseColumns{
        public static String table_name = "users";
        public static String user_name = "name";
        public static String user_password = "password";
        public static String user_email = "email";
        public static String user_phone = "phone";



    }
}
