package com.example.yoga_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Explore extends Fragment {

    private Button btnAll;
    private Button btnBeginner;
    private Button btnExpert;
    private Button btnStrength;
    private Button btnIntermediate;
    private ImageButton btnFocusOne;
    private ImageButton btnFocusTwo;
    private ImageButton btnFocusThree;
    private ImageButton btnFocusFour;
    private final String TYPE_ALL = "all";
    private final String TYPE_STRENGTH = "strength";
    private final String TYPE_INTERMEDIATE = "intermediate";
    private final String TYPE_EXPERT = "expert";
    private final String TYPE_BEGINNER = "beginner";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        //Retrieve the data
        DataRetriever dataRetriever = new DataRetriever(getActivity().getBaseContext());

        btnAll = view.findViewById(R.id.exploreAll);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(TYPE_ALL);
            }
        });

        btnBeginner = view.findViewById(R.id.btnBeginner);
        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(TYPE_BEGINNER);
            }
        });

        btnExpert = view.findViewById(R.id.btnExpert);
        btnExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(TYPE_EXPERT);
            }
        });

        btnStrength = view.findViewById(R.id.btnStrength);
        btnStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(TYPE_STRENGTH);
            }
        });

        btnIntermediate = view.findViewById(R.id.btnIntermediate);
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(TYPE_INTERMEDIATE);
            }
        });

        //Created for you
        
        //Getting Started
        //Seated Yoga
        btnFocusOne = view.findViewById(R.id.btnStartOne);
        btnFocusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(2);
            }
        });

        //Strengthening Yoga
        btnFocusTwo = view.findViewById(R.id.btnStartTwo);
        btnFocusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(3);
            }
        });

        //ChestOpeningYoga
        btnFocusThree = view.findViewById(R.id.btnStartThree);
        btnFocusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(4);
            }
        });

        //Backbend
        btnFocusFour = view.findViewById(R.id.btnStartFour);
        btnFocusFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(5);
            }
        });

        return view;
    }

    private void openFocus(int focusType) {
        Intent intent = new Intent(getActivity(), SelectedWorkout.class);
        intent.putExtra("type",focusType);
        startActivity(intent);
    }

    private void openTag(String tag) {
        Intent intent = new Intent(getActivity(), Trending.class);
        intent.putExtra("type",tag);
        startActivity(intent);
    }
}