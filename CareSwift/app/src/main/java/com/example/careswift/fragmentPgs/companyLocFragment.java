package com.example.careswift.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.careswift.R;

public class companyLocFragment extends Fragment {

    public companyLocFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_loc, container, false);

        // Set company location text
        String locationText = getString(R.string.company_location_text);
        TextView locationTextView = view.findViewById(R.id.locationTextView);
        locationTextView.setText(locationText);

        return view;
    }
}
