package com.example.yoga_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<YogaPose> poseList;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<YogaPose> poseList){
        this.context = context;
        this.poseList = poseList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        YogaPose item = poseList.get(position);
        holder.setImageView(item.getImage());
        holder.setPoseTitle(item.getName());
    }

    @Override
    public int getItemCount() {
        return poseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView poseImage;
        TextView poseTitle;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
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
