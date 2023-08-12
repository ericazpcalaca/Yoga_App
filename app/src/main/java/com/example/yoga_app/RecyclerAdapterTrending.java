package com.example.yoga_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class RecyclerAdapterTrending extends RecyclerView.Adapter<RecyclerAdapterTrending.ViewHolder> {

    private ArrayList<Integer> categoryListID;
    private Context context;
    private final int FIXED_TIME = 45;

    public RecyclerAdapterTrending(Context context, ArrayList<Integer> categoryListID) {
        this.context = context;
        this.categoryListID = categoryListID;
    }

    @NonNull
    @Override
    public RecyclerAdapterTrending.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trend_list_layout, parent, false);
        return new RecyclerAdapterTrending.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTrending.ViewHolder holder, int position) {
        YogaCategories yogaCategory = YogaPosesManager.getInstance().getYogaCategoryByIndex(categoryListID.get(position));
        holder.setPoseTitle(yogaCategory.getNameCategory());
        holder.setLevelTrend("I will fix lol");
        holder.setTimeTrend(convertToString(yogaCategory.getPosesID().size() * FIXED_TIME));

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectedWorkout.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",yogaCategory.getIdCategory());
                bundle.putInt("selectedTime",FIXED_TIME);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryListID.size();
    }

    private String convertToString (int time) {
        int minutes = time / 60;
        int seconds = time % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        return timeLeftFormatted + " minutes";
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTrend;
        TextView timeTrend;
        TextView levelTrend;
        androidx.constraintlayout.widget.ConstraintLayout  constraintLayout;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            constraintLayout = view.findViewById(R.id.listItemTrend);
        }

        public void setPoseTitle(String title){
            titleTrend = view.findViewById(R.id.trend_title);
            titleTrend.setText(title);
        }

        public void setTimeTrend(String trend){
            timeTrend = view.findViewById(R.id.trend_time);
            timeTrend.setText(trend);
        }

        public void setLevelTrend(String level){
            levelTrend = view.findViewById(R.id.trend_level);
            levelTrend.setText(level);
        }
    }
}