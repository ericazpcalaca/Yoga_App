package com.example.yoga_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Explore extends Fragment {

    private ImageButton btnFocusOne;
    private ImageButton btnFocusTwo;
    private ImageButton btnFocusThree;
    private ImageButton btnFocusFour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        //Retrieve the data
        DataRetriever dataRetriever = new DataRetriever(getActivity().getBaseContext());

        btnFocusOne = view.findViewById(R.id.btnStartOne);
        btnFocusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(1);
            }
        });

        btnFocusTwo = view.findViewById(R.id.btnStartTwo);
        btnFocusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(2);
            }
        });

        btnFocusThree = view.findViewById(R.id.btnStartThree);
        btnFocusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(3);
            }
        });

        btnFocusFour = view.findViewById(R.id.btnStartFour);
        btnFocusFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(4);
            }
        });

        return view;
    }

    private void openFocus(int focusType) {
        Intent intent = new Intent(getActivity(), SelectedWorkout.class);
        intent.putExtra("type",focusType);
        startActivity(intent);
    }
}