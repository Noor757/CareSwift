package com.example.careswift.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.careswift.R;

public class termsFragment extends Fragment {

    public termsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terms, container, false);

        // Set terms and conditions text
        String termsConditionsText = getString(R.string.terms_conditions_text);
        TextView termsConditionsTextView = view.findViewById(R.id.termsConditionsTextView);
        termsConditionsTextView.setText(termsConditionsText);

        return view;
    }
}
