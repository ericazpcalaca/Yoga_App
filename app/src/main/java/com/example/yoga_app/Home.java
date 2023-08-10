package com.example.yoga_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class Home extends Fragment {
    private ImageButton btnPoseLibrary;
    private ImageButton btnFocusOne;
    private ImageButton btnFocusTwo;
    private ImageButton btnFocusThree;
    private ImageButton btnFocusFour;
    private ImageButton btnFocusAll;
    private Button btnSuggestFeature;
    private TextView greetingText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Greeting text that changes depending on the time of the day
        greetingText = view.findViewById(R.id.txtTitle);
        String greeting = getGreetingMessage();
        greetingText.setText(greeting);

        //Open the Pose Library
        btnPoseLibrary = view.findViewById(R.id.btnPoseLibrary);
        btnPoseLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPoseLibrary();
            }
        });

        btnFocusOne = view.findViewById(R.id.focusOne);
        btnFocusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(1);
            }
        });

        btnFocusTwo = view.findViewById(R.id.focusTwo);
        btnFocusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(2);
            }
        });

        btnFocusThree = view.findViewById(R.id.focusThree);
        btnFocusThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(3);
            }
        });

        btnFocusFour = view.findViewById(R.id.focusFour);
        btnFocusFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocus(4);
            }
        });

        //Send an email suggesting features for the app
        btnSuggestFeature = view.findViewById(R.id.btnSugest);
        btnSuggestFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This email is here for test proposes
                String subject = "Suggest a feature";
                String email = "mailto: ericacalacacn@gmail.com" +
                        "?&subject=" + Uri.encode(subject);

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(email));

                try{
                    startActivity(Intent.createChooser(intent,"Send Email..."));
                }catch (Exception e){
                    Toast.makeText(getContext(),"Exception :" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private void openPoseLibrary() {
        Intent intent = new Intent(getActivity(), PoseLibrary.class);
        startActivity(intent);
    }

    private void openFocus(int focusType) {
        Intent intent = new Intent(getActivity(), SelectedWorkout.class);
        intent.putExtra("type",focusType);
        startActivity(intent);
    }

    private String getGreetingMessage() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 0 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }

}