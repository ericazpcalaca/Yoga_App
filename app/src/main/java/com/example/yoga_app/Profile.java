package com.example.yoga_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {

    private FirebaseAuth auth;
    private Button btnLogout;
    private Button btnAccountSettings;
    private Button btnReminder;
    private TextView userEmail;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        btnLogout = view.findViewById(R.id.btnLogout);
        userEmail = view.findViewById(R.id.userEmail);
        btnAccountSettings = view.findViewById(R.id.btnAccount);
        btnReminder = view.findViewById(R.id.btnReminder);
        user = auth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(getContext(), Login.class));
            getActivity().finish();
        } else {
            userEmail.setText(user.getEmail());
        }

        // Set up the account settings
        btnAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AccountSettings.class));
            }
        });

        // Set up the notification
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Notification.class));
            }
        });

        // For logout the app
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), Login.class));
                getActivity().finish();
            }
        });



        return view;
    }
}