package com.example.pojectku.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.pojectku.Item.ImageSlide;
import com.example.pojectku.R;

import java.util.ArrayList;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.ViewHolder> {
    ArrayList<ImageSlide> item;

    public ImageSlideAdapter(ArrayList<ImageSlide> item) {

        this.item = item;
    }

    @NonNull
    @Override
    public ImageSlideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSlideAdapter.ViewHolder holder, int position) {

        int drawableResId = holder.itemView.getResources().getIdentifier(item.get(position).getPicture(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(), new GranularRoundedCorners(40,40,40,40))
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img_1);

        }
    }
}



