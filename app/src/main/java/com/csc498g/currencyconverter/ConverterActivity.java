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

    public int convert(float amount, String currency1, String currency2) {

        // Convert from Currency 1 to Currency 2

        int cur1_to_dollars = toDollar(amount, currency1);
        int dollars_to_cur2 = fromDollar(cur1_to_dollars, currency2);
        return dollars_to_cur2;

    }

    private int fromDollar(float amount, String currency2) {

        // Convert from Dollars to a certain currency
        return 0;
    }

    private int toDollar(float amount, String currency1) {

        // Convert from a certain currency to Dollars
        return 0;
        
    }
}