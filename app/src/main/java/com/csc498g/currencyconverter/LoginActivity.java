package com.csc498g.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View v) {

        EditText username_edit_text = findViewById(R.id.usernameEdit);
        EditText password_edit_text = findViewById(R.id.passwordEdit);

        String username_value = username_edit_text.getText().toString();
        String password_value = password_edit_text.getText().toString();

        if(username_value.equals("user.leb") && password_value.equals(("123456"))) {

            Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
            Intent logger = new Intent(getApplicationContext(), ConverterActivity.class);
            startActivity(logger);

        } else {

            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();

        }


    }
}