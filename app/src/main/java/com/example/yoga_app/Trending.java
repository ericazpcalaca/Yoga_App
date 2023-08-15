package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Trending extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private androidx.appcompat.widget.Toolbar toolbar;
    ArrayList<Integer> categoryListID;
    private final String TYPE_ALL = "all";
    private final String TYPE_STRENGTH = "strength";
    private final String TYPE_INTERMEDIATE = "intermediate";
    private final String TYPE_EXPERT = "expert";
    private final String TYPE_BEGINNER = "beginner";
    private final String TYPE_ARMS = "arm"; //ok
    private final String TYPE_LEGS = "leg";
    private final String TYPE_SHOULDER = "shoulder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        toolbar = findViewById(R.id.toolbarTrending);
        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        categoryListID = new ArrayList<>();
        //Create an list with the ID of the categories for display

        for (int i = 1; i < YogaPosesManager.getInstance().getNumberOfCategories(); i++) {
            if (type.equals(TYPE_STRENGTH) && YogaPosesManager.getInstance().getYogaCategoryByIndex(i).getDescriptionCategory().contains(TYPE_STRENGTH)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_ARMS) && (YogaPosesManager.getInstance().getYogaCategoryByIndex(i).getDescriptionCategory()).contains(TYPE_ARMS)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_LEGS) && YogaPosesManager.getInstance().getYogaCategoryByIndex(i).getDescriptionCategory().contains(TYPE_LEGS)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_SHOULDER) && YogaPosesManager.getInstance().getYogaCategoryByIndex(i).getDescriptionCategory().contains(TYPE_SHOULDER)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_INTERMEDIATE) && YogaPosesManager.getInstance().getLevelOfCategory(i).equals(TYPE_INTERMEDIATE)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_EXPERT) && YogaPosesManager.getInstance().getLevelOfCategory(i).equals(TYPE_EXPERT)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_BEGINNER) && YogaPosesManager.getInstance().getLevelOfCategory(i).equals(TYPE_BEGINNER)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            } else if (type.equals(TYPE_ALL)) {
                if (!categoryListID.contains(i)) {
                    categoryListID.add(i);
                }
            }
        }

        recyclerView = findViewById(R.id.recyclerViewTrend);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DataRetriever dataRetriever = new DataRetriever(this);
        adapter = new RecyclerAdapterTrending(Trending.this, categoryListID);
        recyclerView.setAdapter(adapter);

    }
}