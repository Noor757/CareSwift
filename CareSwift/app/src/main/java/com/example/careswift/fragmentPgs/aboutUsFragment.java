package com.example.careswift.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.careswift.R;

public class aboutUsFragment extends Fragment {

    public aboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        // Set about us text
        String aboutUsText = getString(R.string.about_us_text);
        TextView aboutUsTextView = view.findViewById(R.id.aboutUs);
        aboutUsTextView.setText(aboutUsText);

        return view;
    }
}
