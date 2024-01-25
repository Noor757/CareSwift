package com.example.careswift.activityPgs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.careswift.R;

public class CustomerActivity extends AppCompatActivity {
    private Bundle userDataBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // Find views
        EditText phoneNumberEditText = findViewById(R.id.editTextPhone);
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.button_signUp);
        loginButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        registerButton.setBackgroundColor(getResources().getColor(R.color.yellow));


        userDataBundle = getIntent().getExtras();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String phoneNumber = phoneNumberEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform validation checks (e.g., check if fields are not empty)
                if (phoneNumber.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CustomerActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Check if the credentials exist in the stored bundles
                    if (isUserCredentialsValid(phoneNumber, password)) {
                        // If credentials are correct, redirect to HomepageActivity
                        Intent intent = new Intent(CustomerActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If credentials are incorrect, display a message
                        Toast.makeText(CustomerActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to RegisterActivity
                Intent intent = new Intent(CustomerActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private boolean isUserCredentialsValid(String phoneNumber, String password) {
        // Retrieve the user's data bundle
        Bundle userBundle = getIntent().getExtras();

        return userBundle != null
                && userBundle.getString("phone", "").equals(phoneNumber)
                && userBundle.getString("password", "").equals(password)
                && isPhoneNumberValid(phoneNumber);
    }
    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.length() == 11;
    }
}
