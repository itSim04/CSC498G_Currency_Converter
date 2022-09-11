package com.csc498g.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;

public class ConverterActivity extends AppCompatActivity {

    static final HashMap<String, Float> currencies = new HashMap<>() {{

        put("USD", 1.0F);
        put("LBP", 40000.0F);

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }
}