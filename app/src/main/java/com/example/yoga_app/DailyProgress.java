package com.example.yoga_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DailyProgress extends Fragment {

    private CalendarView calendarView;
    private TextView totalBestStreak;
    private TextView totalCurrentStreak;
    private TextView totalMinutes;
    private TextView totalWorkout;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser userId;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_progress, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        totalBestStreak = view.findViewById(R.id.totalBestStreak);
        totalCurrentStreak = view.findViewById(R.id.totalCurrentStreak);
        totalMinutes = view.findViewById(R.id.totalMinutes);
        totalWorkout = view.findViewById(R.id.totalWorkout);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser();
        userRef = database.getReference("tracker").child(userId.getUid());

        getDataFromFirebase();
        return view;
    }

    private void getDataFromFirebase(){
        // Attach a listener to the "users" node
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProgressUser userProgress = snapshot.getValue(ProgressUser.class);
                if (userProgress != null) {
                    totalBestStreak.setText(String.valueOf(userProgress.getBestStreak()));
                    totalCurrentStreak.setText(String.valueOf(userProgress.getCurrentStreak()));
                    totalMinutes.setText(String.valueOf(userProgress.getTotalMinutes()));
                    totalWorkout.setText(String.valueOf(userProgress.getTotalWorkout()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Error "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}