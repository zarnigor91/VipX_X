package com.example.stajxml;

import android.content.SharedPreferences;

public class Prefs {

    static Prefs prefs = null;
    private static String LENGUAGE_KEY = "lenguage";
    private SharedPreferences sharedPreferences;

    public Prefs(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static Prefs instance(SharedPreferences sharedPreferences) {
        if (prefs == null) prefs = new Prefs(sharedPreferences);
        return prefs;

    }

    public String getLogin() {
        return sharedPreferences.getString("login", "setLogin");

    }

    public void setLogin(String login) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.apply();

    }

    public String getPass() {
        return sharedPreferences.getString("pass", "setLogin");

    }

    public void setPass(String pass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pass", pass);
        editor.apply();
    }

    public void setReg(boolean regg) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("regestred", regg);
        editor.apply();
    }


}
