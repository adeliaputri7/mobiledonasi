package com.example.pojectku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pojectku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;

public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.DonationViewHolder> {

    private Context context;
    private JSONArray donationList;


    // Constructor
    public DonasiAdapter(Context context, JSONArray donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_donation.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donasi, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        try {
            // Get donation object
            JSONObject donation = donationList.getJSONObject(position);

            
            holder.Judul.setText(donation.getString("judul"));
            holder.Kategori.setText(donation.getString("kategori"));
            holder.Target.setText("Target: " + donation.getString("target"));
            holder.Terkumpul.setText("Terkumpul: " + donation.getString("terkumpul"));
            holder.Status.setText("Status: " + donation.getString("status"));

            String imageUrl = donation.getString("image_url"); // Pastikan API mengembalikan URL gambar
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_imgkosong) // Placeholder jika gambar belum dimuat
                    .error(R.drawable.ic_imgerror) // Gambar jika ada error
                    .into(holder.Gambar);
            

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return donationList.length();
    }

    // ViewHolder class
    public static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, Kategori, Target, Terkumpul, Status;
        ImageView Gambar;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);

            Gambar = itemView.findViewById(R.id.img_2);
            Judul = itemView.findViewById(R.id.txt_title);
            Kategori = itemView.findViewById(R.id.txt_kategori);
            Target = itemView.findViewById(R.id.txt_target);
            Terkumpul = itemView.findViewById(R.id.txt_terkumpul);
            Status = itemView.findViewById(R.id.txt_status);
        }
    }
}



