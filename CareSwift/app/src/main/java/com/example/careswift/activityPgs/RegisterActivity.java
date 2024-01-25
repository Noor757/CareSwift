package com.example.careswift.activityPgs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.careswift.R;
import com.example.careswift.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel viewModel;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        setContentView(binding.getRoot());

        binding.buttonSignUp.setBackgroundColor(getResources().getColor(R.color.yellow));

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // Set the ViewModel in the layout
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inside your activity
                viewModel.onSignUpClick(
                        binding.editTextName.getText().toString(),
                        binding.editTextEmailAddress.getText().toString(),
                        binding.editTextPhone.getText().toString(),
                        binding.editTextPassword.getText().toString(),
                        binding.editTextCPassword.getText().toString()
                );

            }
        });

        // Observe registration status
        viewModel.getRegistrationStatus().observe(this, registrationStatus -> {
            if (registrationStatus) {
                // Registration successful, redirect to CustomerActivity
                Intent intent = new Intent(RegisterActivity.this, CustomerActivity.class);
                intent.putExtras(getUserDataBundle());
                startActivity(intent);
                finish();
            }
        });

        // Observe toast messages
        viewModel.getToastMessage().observe(this, message -> {
            // Display toast messages
            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private Bundle getUserDataBundle() {
        // Create a Bundle with user data
        Bundle userData = new Bundle();
        userData.putString("name", binding.editTextName.getText().toString());
        userData.putString("email", binding.editTextEmailAddress.getText().toString());
        userData.putString("phone", binding.editTextPhone.getText().toString());
        userData.putString("password", binding.editTextPassword.getText().toString());
        return userData;
    }
}
