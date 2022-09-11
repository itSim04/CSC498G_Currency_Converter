package com.csc498g.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

public class ConverterActivity extends AppCompatActivity {

    static final HashMap<String, Double> currencies = new HashMap<>() {{

        put("USD", 1.0);
        put("LBP", 40000.0);

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }

    public double convert(float amount, String currency1, String currency2) {

        // Convert from Currency 1 to Currency 2

        double cur1_to_dollars = toDollar(amount, currency1);
        double dollars_to_cur2 = fromDollar(cur1_to_dollars, currency2);
        return dollars_to_cur2;

    }

    private double toDollar(double amount, String currency1) {

        // Convert from a certain currency to Dollars
        return amount / currencies.get(currency1);

    }

    private double fromDollar(double amount, String currency2) {

        // Convert from Dollars to a certain currency

        return 0;
    }

}