package com.example.food_recipe.model;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }
    public String getPassword() {
        return password;
    }
}
