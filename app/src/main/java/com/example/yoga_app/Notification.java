package com.example.yoga_app;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class Notification extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private Button btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.toolbarReminder);

        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle("Reminder");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnNotification = findViewById(R.id.btnSetNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();
                
                Intent notificationIntent = new Intent(getApplicationContext(), NotificationBroadcastReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


                // Set the time for the daily notification
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 9);
                calendar.set(Calendar.MINUTE, 30);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY,  // Repeat every day
                        alarmIntent);
                Toast.makeText(Notification.this, "Reminder Activate",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void askPermission() {
        //Ask for the permission if needed
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(Notification.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(Notification.this, new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }
    }

}