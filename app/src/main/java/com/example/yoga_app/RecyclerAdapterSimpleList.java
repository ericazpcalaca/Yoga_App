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

public class RecyclerAdapterSimpleList extends RecyclerView.Adapter<RecyclerAdapterSimpleList.ViewHolder> {

    private ArrayList<Integer> poseListID;
    private Context context;

    public RecyclerAdapterSimpleList(Context context, ArrayList<Integer> poseListID){
        this.context = context;
        this.poseListID = poseListID;
    }

    @NonNull
    @Override
    public RecyclerAdapterSimpleList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_list_layout,parent,false);
        return new RecyclerAdapterSimpleList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterSimpleList.ViewHolder holder, int position) {
        YogaPose yogaPose = YogaPosesManager.getInstance().getYogaPoseByIndex(poseListID.get(position));
        holder.setPoseTitle(yogaPose.getName());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",yogaPose.getPoseId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poseListID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView poseTitle;
        View view;
        androidx.constraintlayout.widget.ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            constraintLayout = view.findViewById(R.id.listItem);
        }

        public void setPoseTitle(String title){
            poseTitle = view.findViewById(R.id.poseWorkoutTitle);
            poseTitle.setText(title);
        }
    }
}