package com.example.mahmoudamin.gradpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class App extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        android.app.ActionBar bar =getActionBar();
    }
}
