package com.example.pojectku.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pojectku.Item.ImageSlide;
import com.example.pojectku.R;

import java.util.List;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.SliderViewHolder> {

    private List<ImageSlide> sliderItems;
    private ViewPager2 viewPager2;
    private long interval;
    private View.OnClickListener onClickListener;

    public ImageSlideAdapter(List<ImageSlide> sliderItems, ViewPager2 viewPager2, long interval, View.OnClickListener onClickListener) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.interval = interval;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
        view.setOnClickListener(onClickListener);  // Mengatur klik listener
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sl_image);
        }

        void setImage(ImageSlide slide) {
            imageView.setImageResource(slide.getImageResId());
        }
    }
}



