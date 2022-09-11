package com.csc498g.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class ConverterActivity extends AppCompatActivity {

    boolean in_progress = false;
    static final HashMap<String, Double> currencies = new HashMap<>() {{

        put("USD", 1.0);
        put("LBP", 40000.0);

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        EditText value_one_edit_text = findViewById(R.id.valueOneEdit);
        EditText value_two_edit_text = findViewById(R.id.valueTwoEdit);
        Spinner currency_one_spinner = findViewById(R.id.currencyOneSpin);
        Spinner currency_two_spinner = findViewById(R.id.currencyTwoSpin);

        value_one_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!in_progress) {

                    in_progress = true;
                    String currency_1 = currency_one_spinner.getSelectedItem().toString();
                    String currency_2 = currency_two_spinner.getSelectedItem().toString();
                    String value = value_one_edit_text.getText().toString();

                    double converted_result = convert(!value.equals("") ? Double.parseDouble(value) : 0, currency_1, currency_2);
                    value_two_edit_text.setText(converted_result + "");
                    in_progress = false;
                }
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

                if(!in_progress) {

                    in_progress = true;
                    String currency_1 = currency_two_spinner.getSelectedItem().toString();
                    String currency_2 = currency_one_spinner.getSelectedItem().toString();
                    String value = value_two_edit_text.getText().toString();

                    double converted_result = convert(!value.equals("") ? Double.parseDouble(value) : 0, currency_1, currency_2);
                    value_one_edit_text.setText(converted_result + "");
                    in_progress = false;
                }
            }
        });

    }

    public double convert(double amount, String currency1, String currency2) {

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
        return amount * currencies.get(currency2);
    }

}