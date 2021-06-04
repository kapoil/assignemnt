package com.example.myapplication.ui.homepage.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ui.homepage.activity.DetailActivity;
import com.example.myapplication.models.ModelProfile;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ItemViewHolder> {


    //region view variable
    Context context;
    ArrayList<ModelProfile> profiles;
    //endregion

    //region Constructor methods
    public ProfileAdapter(ArrayList<ModelProfile> profiles, Context context) {
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ProfileAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ItemViewHolder holder, int position) {
        ModelProfile modelProfile = profiles.get(position);
        holder.name.setText((String) modelProfile.getGeneral_information().get("first_name"));
        String gender = (String) modelProfile.getGeneral_information().get("gender");
        Integer age = (int) modelProfile.getGeneral_information().get("age");

        holder.genderAndAge.setText(gender + ", " + age);
        holder.lastSeen.setText(modelProfile.getLast_seen_window());
        holder.recentlyActive.setText(modelProfile.getLast_seen());

        HashMap hashMap = (HashMap) modelProfile.getPhotos().get(0);
        String url = (String) hashMap.get("photo");

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.photo);

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putSerializable("Profile",modelProfile);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView genderAndAge;
        TextView recentlyActive;
        TextView lastSeen;
        ImageView  photo;


        public ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            genderAndAge = itemView.findViewById(R.id.gender_age);
            recentlyActive = itemView.findViewById(R.id.recentlyActive);
            lastSeen = itemView.findViewById(R.id.lastSeen);
            photo = itemView.findViewById(R.id.photo1);
        }

    }
}
