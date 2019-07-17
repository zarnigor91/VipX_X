package com.example.stajxml.start;

import android.os.Bundle;

import com.example.stajxml.BaseActivity;
import com.example.stajxml.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.continer, new LoginFragment()).commit();
        }


    }
}
