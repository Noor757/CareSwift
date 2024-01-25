package com.example.careswift.activityPgs;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.careswift.R;
import com.example.careswift.databinding.ActivityOptionBinding; // Import view binding class

public class OptionActivity extends AppCompatActivity {

    private ActivityOptionBinding binding; // Declare view binding variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionBinding.inflate(getLayoutInflater()); // Inflate using view binding
        setContentView(binding.getRoot()); // Use the root view from the binding

        // Set click listeners for each option
        Button contactUsButton = binding.contactUsButton;
        Button companyLocationButton = binding.companyLocationButton;
        Button faqsButton = binding.faqsButton;
        Button termsConditionsButton = binding.termsConditionsButton;

        contactUsButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        companyLocationButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        faqsButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        termsConditionsButton.setBackgroundColor(getResources().getColor(R.color.yellow));

        contactUsButton.setOnClickListener(v -> {
            replaceFragment(new com.example.careswift.fragments.aboutUsFragment());
        });

        companyLocationButton.setOnClickListener(v -> {
            replaceFragment(new com.example.careswift.fragments.companyLocFragment());
        });

        faqsButton.setOnClickListener(v -> {
            replaceFragment(new com.example.careswift.fragments.FaqFragment());
        });

        termsConditionsButton.setOnClickListener(v -> {
            replaceFragment(new com.example.careswift.fragments.termsFragment());
        });
    }

    @Override
    public void onBackPressed() {
        // Start HomepageActivity when the back button is pressed
        super.onBackPressed();
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish(); // Optional: finish the current activity if needed
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.fragmentContainer.getId(), fragment); // Use binding to get the ID
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
