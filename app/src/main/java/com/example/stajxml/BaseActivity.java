package com.example.stajxml;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stajxml.app.LocaleHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}
