package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Workout extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 1000;
    private androidx.appcompat.widget.Toolbar toolbar;
    private TextView txtTimerDisplay;
    private TextView txtPoseName;
    private TextView txtTotalPoses;
    private TextView txtCurrentPose;
    private ImageButton btnStartPause;
    private ImageButton btnInfo;
    private ImageView poseImage;
    private int selectedTime;
    private int currentImageIndex = 0;
    private ArrayList<Integer> workOutIDs;
    private CountDownTimer mCountDownTimer;
    private long image_change;
    private Boolean mTimerRunning = false;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser userId;
    private FirebaseAuth auth;
    private int updateWorkout;
    private int updateMinutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //Set variables
        toolbar = findViewById(R.id.toolbarWorkdout);
        txtTotalPoses = findViewById(R.id.totalPose);
        txtPoseName = findViewById(R.id.nameCurrentPose);
        txtCurrentPose = findViewById(R.id.currentPose);
        poseImage = findViewById(R.id.yogaPoseExample);
        txtTimerDisplay = findViewById(R.id.text_view_countdown);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnInfo = findViewById(R.id.openInfo);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser();
        userRef = database.getReference("tracker").child(userId.getUid());


        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Get data about the workout selected and the time from the other activity
        Intent intent = getIntent();
        int typeofWorkout = intent.getIntExtra("workoutID", -1);
        selectedTime = intent.getIntExtra("selectedTime", -1);

        /*
            Get the list of workouts id's and set the timer and the
            total of poses
        */
        workOutIDs = YogaPosesManager.getInstance().getPoseList(typeofWorkout);
        txtTotalPoses.setText(String.valueOf(workOutIDs.size()));
        mTimeLeftInMillis = selectedTime * START_TIME_IN_MILLIS * workOutIDs.size();
        image_change = selectedTime * 1000; // transform the seconds in milliseconds

        //Count down to the workout
        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        updateCountDownText();

        //Open the dialog for the information about the current pose
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    /*
        Dialog to show the user more information's about the current pose
    */
    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        ImageButton btnClose = dialog.findViewById(R.id.closeDialog);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnCloseDialog = dialog.findViewById(R.id.btnClose);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        YogaPose yogaPose = YogaPosesManager.getInstance().getYogaPoseByIndex(workOutIDs.get(currentImageIndex - 1));
        TextView title = dialog.findViewById(R.id.titleOfPose);
        TextView description = dialog.findViewById(R.id.descriptionOfPose);
        title.setText(yogaPose.getName());
        description.setText(yogaPose.getDescription());
        dialog.show();
    }

    /*
       Dialog to show at the end of the workout that the user finished the workout
   */
    private void callDialog() {



        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_finished_workout, null);

        // Create and show the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final Dialog dialog = builder.create();
        dialog.show();

        // Close button click listener
        View closeButton = dialogView.findViewById(R.id.dialog_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProgress();
                dialog.dismiss();
            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            private long lastImageChangeTime = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

                long currentTime = System.currentTimeMillis();
                if (currentTime - lastImageChangeTime >= image_change) {
                    updateDataInScreen();
                    lastImageChangeTime = currentTime;
                }
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btnStartPause.setImageResource(R.drawable.ic_baseline_play_circle_24);
                btnStartPause.setVisibility(View.INVISIBLE);
                callDialog();
            }
        }.start();

        mTimerRunning = true;
        btnStartPause.setImageResource(R.drawable.ic_baseline_pause_circle_24);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnStartPause.setImageResource(R.drawable.ic_baseline_play_circle_24);
    }

    /*
       Method to upload the images and names of the poses while the time goes by
   */
    private void updateDataInScreen() {
        YogaPose yogaPose = YogaPosesManager.getInstance().getYogaPoseByIndex(workOutIDs.get(currentImageIndex));
        Glide.with(getApplicationContext()).load(yogaPose.getImage()).into(poseImage);
        txtCurrentPose.setText(String.valueOf(currentImageIndex + 1));
        txtPoseName.setText(yogaPose.getName());
        currentImageIndex++;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) ((mTimeLeftInMillis / 1000) % 60);

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        txtTimerDisplay.setText(timeLeftFormatted);
    }

    /*
       Method to upload the information about the user in regard of his daily progress
   */
    private void updateUserProgress() {
        // Attach a listener to the "users" node
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProgressUser userProgress = snapshot.getValue(ProgressUser.class);
                if (userProgress != null) {
                    updateWorkout = userProgress.getTotalWorkout();
                    updateMinutes = userProgress.getTotalMinutes();
                    updateMinutes += (selectedTime * workOutIDs.size() / 60);
                    updateWorkout++;
                    userRef.child("totalWorkout").setValue(updateWorkout);
                    userRef.child("totalMinutes").setValue(updateMinutes);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}