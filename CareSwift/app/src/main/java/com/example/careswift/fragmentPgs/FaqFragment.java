package com.example.careswift.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.careswift.R;

public class FaqFragment extends Fragment {

    public FaqFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        // Set FAQ text
        String faqText = getString(R.string.faq_text);
        TextView faqTextView = view.findViewById(R.id.faqTextView);
        faqTextView.setText(faqText);

        return view;
    }
}
