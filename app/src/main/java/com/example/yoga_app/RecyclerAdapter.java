package com.example.yoga_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;

    public RecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        YogaPose yogaPose = YogaPosesManager.getInstance().getYogaPoseByIndex(position);
        holder.setImageView(yogaPose.getImage());
        holder.setPoseTitle(yogaPose.getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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
        return YogaPosesManager.getInstance().getNumberOfPoses();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView poseImage;
        TextView poseTitle;
        CardView cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void setImageView(String url){
            poseImage = view.findViewById(R.id.poseImage);
            Glide.with(context).load(url).into(poseImage);
        }

        public void setPoseTitle(String title){
            poseTitle = view.findViewById(R.id.poseTitle);
            poseTitle.setText(title);
        }
    }
}
