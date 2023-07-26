package com.example.yoga_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class Home extends Fragment {
    private ImageButton btnPoseLibrary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnPoseLibrary = view.findViewById(R.id.btnPoseLibrary);
        btnPoseLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                openPoseLibrary();
            }
        });

        return view;
    }

    private void openPoseLibrary() {
        Intent intent = new Intent(getActivity(), PoseLibrary.class);
        startActivity(intent);
    }
}