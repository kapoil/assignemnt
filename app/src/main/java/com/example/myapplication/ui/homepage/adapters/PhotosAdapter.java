package com.example.myapplication.ui.homepage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ItemViewHolder> {


    //    private final ArrayList<Profile> profile;
    Context context;
    ArrayList<HashMap> photos;
    //endregion

    //region Constructor methods
    public PhotosAdapter(ArrayList<HashMap> photos, Context context) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotosAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotosAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photos,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.ItemViewHolder holder, int position) {
        HashMap hashMap = photos.get(position);
        String name = (String) hashMap.get("first_name");
        if (name != null && name != "") {
            holder.name.setVisibility(View.VISIBLE);
            holder.name.setText(name);
        } else {
            holder.name.setVisibility(View.GONE);
            holder.name.setText("");

        }

        String url = (String) hashMap.get("photo");
        if (url == null || url == "") {
            url = (String) hashMap.get("avatar");

        }

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.photo);


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        //        TextView genderAndAge;
//        TextView recentlyActive;
//        TextView lastSeen;
        ImageView photo;


        public ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
//            genderAndAge = itemView.findViewById(R.id.gender_age);
//            recentlyActive = itemView.findViewById(R.id.recentlyActive);
//            lastSeen = itemView.findViewById(R.id.lastSeen);
            photo = itemView.findViewById(R.id.image);
        }

    }
}