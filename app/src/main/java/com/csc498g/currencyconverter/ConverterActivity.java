package com.csc498g.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;

public class ConverterActivity extends AppCompatActivity {

    EditText value_one_edit_text;
    EditText value_two_edit_text;
    Spinner currency_one_spinner;
    Spinner currency_two_spinner;
    boolean in_progress = false;

    final DecimalFormat conversion_format = new DecimalFormat("#.##########");
    static final HashMap<String, Double> currencies = new HashMap<>() {{

        put("USD", 1.0);
        put("LBP", 40000.0);

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        value_one_edit_text = findViewById(R.id.valueOneEdit);
        value_two_edit_text = findViewById(R.id.valueTwoEdit);
        currency_one_spinner = findViewById(R.id.currencyOneSpin);
        currency_two_spinner = findViewById(R.id.currencyTwoSpin);


        value_one_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                handleConversion(true);
            }
        });
        value_two_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                handleConversion(false);

            }
        });
        currency_two_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                handleConversion(true);
                updatePreview();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        currency_one_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                handleConversion(false);
                updatePreview();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

    public void handleConversion(boolean forward) {

        if(!in_progress) {

            in_progress = true;
            String currency_1 = currency_one_spinner.getSelectedItem().toString();
            String currency_2 = currency_two_spinner.getSelectedItem().toString();
            String value = forward ? value_one_edit_text.getText().toString() : value_two_edit_text.getText().toString();

            double converted_result = 0;
            try {
                converted_result = convert(!value.equals("") ? Double.parseDouble(value) : 0, forward ? currency_1 : currency_2, forward ? currency_2 : currency_1);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            }
            if(forward) {
                value_two_edit_text.setText(conversion_format.format(converted_result));
            } else {
                value_one_edit_text.setText(conversion_format.format(converted_result));
            }
            in_progress = false;
        }

    }

    public double convert(double amount, String currency1, String currency2) {

        // Convert from Currency 1 to Currency 2
        double cur1_to_dollars = toDollar(amount, currency1);
        return fromDollar(cur1_to_dollars, currency2);

    }

    private double toDollar(double amount, String currency1) {

        // Convert from a certain currency to Dollars
        return amount / currencies.getOrDefault(currency1, 1.0);

    }

    private double fromDollar(double amount, String currency2) {

        // Convert from Dollars to a certain currency
        return amount * currencies.getOrDefault(currency2, 0.0);
    }

    public void updatePreview() {

        TextView currency_preview = findViewById(R.id.conversionPreviewText);

        String currency1 = currency_one_spinner.getSelectedItem().toString();
        String currency2 = currency_two_spinner.getSelectedItem().toString();
        double conversion_result = convert(1.0, currency1, currency2);


        currency_preview.setText(String.format("1 %s equals %s %s", currency1, conversion_format.format(conversion_result), currency2));

    }

}