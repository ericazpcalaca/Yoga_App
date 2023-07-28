package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    private ImageView imagePose;
    private TextView poseName;
    private TextView poseDescription;
    private TextView poseBenefits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        imagePose = findViewById(R.id.test);
        poseName = findViewById(R.id.test);
//        poseDescription = findViewById(R.id.ratingMovie);
//        poseBenefits = findViewById(R.id.descriptionMovie);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("name");
        String mRating = bundle.getString("description");
        String mDescription = bundle.getString("benefits");
        String mPoster = bundle.getString("url");

//        Picasso.get().load(mPoster).into(imagePose);
        poseName.setText(mTitle);
//        poseDescription.setText(mRating);
//        poseBenefits.setText(mDescription);
    }
}