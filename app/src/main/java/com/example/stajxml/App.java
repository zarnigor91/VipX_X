package com.example.stajxml;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class App extends Application {
    private Context context;
    public static SharedPreferences preferences;
    static App instance;

    public App() {
        context = this;
    }

    public void init(){
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.onAttach(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LocaleHelper.onAttach(base);
    }

    public void updateRes() {
        try {
            Resources res = getInstance().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = new Locale(LocaleHelper.getLanguage(getInstance()));
            res.updateConfiguration(conf, dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
