package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {

    private ImageView imagePose;
    private TextView poseName;
    private TextView poseDescription;
    private TextView poseBenefits;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbarDetail);
        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imagePose = findViewById(R.id.imagePose);
        poseName = findViewById(R.id.poseTittle);
        poseDescription = findViewById(R.id.txtDescription);
        poseBenefits = findViewById(R.id.txtBenefits);

        Bundle bundle = getIntent().getExtras();

        String yName = bundle.getString("name");
        String yDescription = bundle.getString("description");
        String yBenefits = bundle.getString("benefits");
        String yUrl = bundle.getString("url");

        Glide.with(getApplicationContext()).load(yUrl).into(imagePose);
        poseName.setText(yName);
        poseDescription.setText(yDescription);
        poseBenefits.setText(yBenefits);
    }
}