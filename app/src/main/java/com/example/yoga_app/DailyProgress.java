package com.example.yoga_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;


public class DailyProgress extends Fragment {

    private CalendarView calendarView;
    private TextView totalBestStreak;
    private TextView totalCurrentStreak;
    private TextView totalMinutes;
    private TextView totalWorkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_progress, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        totalBestStreak = view.findViewById(R.id.totalBestStreak);
        totalCurrentStreak = view.findViewById(R.id.totalCurrentStreak);
        totalMinutes = view.findViewById(R.id.totalMinutes);
        totalWorkout = view.findViewById(R.id.totalWorkout);

        return view;
    }
}