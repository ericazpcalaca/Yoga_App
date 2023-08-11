package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class Workout extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 1000;

    private TextView txtTimerDisplay;
    private TextView txtTotalPoses;
    private TextView txtCurrentPose;
    private ImageButton btnStartPause;
    private ImageButton btnInfo;
    private ImageView poseImage;
    private Button btnResetTime;
    private int selectedTime;
    private ArrayList<Integer> workOutIDs;
    private int currentImageIndex = 0;
    private CountDownTimer mCountDownTimer;
    private long image_change;

    private Boolean mTimerRunning = false;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();
        int typeofWorkout = intent.getIntExtra("workoutID", -1);
        selectedTime = intent.getIntExtra("selectedTime", -1);

        workOutIDs = YogaPosesManager.getInstance().getPoseList(typeofWorkout);
        txtTotalPoses = findViewById(R.id.totalPose);
        txtCurrentPose = findViewById(R.id.currentPose);
        poseImage = findViewById(R.id.yogaPoseExample);
        txtTotalPoses.setText(String.valueOf(workOutIDs.size()));
        mTimeLeftInMillis = selectedTime * START_TIME_IN_MILLIS * workOutIDs.size(); //set the timer
        image_change = selectedTime * 1000; // transform the seconds in milliseconds
        changeImage();

        //Count down to the workout
        txtTimerDisplay = findViewById(R.id.text_view_countdown);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnResetTime = findViewById(R.id.btnReset);

        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        btnResetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

        //Open the dialog for the information about the current pose
        btnInfo = findViewById(R.id.openInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

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

        dialog.show();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        btnResetTime.setVisibility(View.INVISIBLE);
        btnStartPause.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnStartPause.setImageResource(R.drawable.ic_baseline_play_circle_24);
        btnResetTime.setVisibility(View.VISIBLE);
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
                    changeImage();
                    lastImageChangeTime = currentTime;
                }
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btnStartPause.setImageResource(R.drawable.ic_baseline_play_circle_24);
                btnStartPause.setVisibility(View.INVISIBLE);
                btnResetTime.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        btnStartPause.setImageResource(R.drawable.ic_baseline_pause_circle_24);
        btnResetTime.setVisibility(View.INVISIBLE);
    }

    private void changeImage() {
        YogaPose yogaPose = YogaPosesManager.getInstance().getYogaPoseByIndex(workOutIDs.get(currentImageIndex));
        Glide.with(getApplicationContext()).load(yogaPose.getImage()).into(poseImage);
        currentImageIndex++;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) ((mTimeLeftInMillis / 1000) % 60);

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        txtTimerDisplay.setText(timeLeftFormatted);
    }
}