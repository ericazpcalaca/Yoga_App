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
    private Button btnSugestFeature;
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
                // Handle button click event
                openPoseLibrary();
            }
        });

        //Send an email suggesting features for the app
        btnSugestFeature = view.findViewById(R.id.btnSugest);
        btnSugestFeature.setOnClickListener(new View.OnClickListener() {
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