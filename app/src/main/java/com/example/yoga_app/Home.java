package com.example.yoga_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Home extends Fragment {
    private Button buttonOpenActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        buttonOpenActivity = view.findViewById(R.id.buttonOpenActivity);
//        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button click event
//                openActivity();
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }
}