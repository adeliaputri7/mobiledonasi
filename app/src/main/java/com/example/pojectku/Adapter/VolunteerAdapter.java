package com.example.pojectku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pojectku.R;
import com.example.pojectku.TampilDonasi;
import com.example.pojectku.TampilVolunteer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private Context context;
    private JSONArray volunteerList;



    // Constructor
    public VolunteerAdapter(Context context, JSONArray volunteerList) {
        this.context = context;
        this.volunteerList = volunteerList;
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_donation.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volunteer, parent, false);
        return new VolunteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        try {
            // Get donation object
            JSONObject volunteer = volunteerList.getJSONObject(position);


            holder.Judul.setText(volunteer.getString("judul"));
            holder.Kategori.setText(volunteer.getString("kategori"));
            holder.Lokasi.setText(volunteer.getString("lokasi"));
            holder.Tanggal.setText(volunteer.getString("waktu"));


            String imageUrl = volunteer.getString("gambar"); // Pastikan API mengembalikan URL gambar
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_imgkosong) // Placeholder jika gambar belum dimuat
                    .error(R.drawable.ic_imgerror) // Gambar jika ada error
                    .into(holder.Gambar);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, TampilVolunteer.class);
                try {
                    // Kirim data detail donasi menggunakan Intent

                    intent.putExtra("judul", volunteer.getString("judul"));
                    intent.putExtra("kategori", volunteer.getString("kategori"));
                    intent.putExtra("target", "Rp." + volunteer.getString("target"));
                    intent.putExtra("keterangan", "Keterangan: " + volunteer.getString("keterangan"));
                    intent.putExtra("gambar", volunteer.getString("gambar"));
                    intent.putExtra("waktu", "Pelaksanan: " + volunteer.getString("waktu"));
                    context.startActivity(intent); // Mulai activity tujuan
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return volunteerList.length();
    }

    // ViewHolder class
    public static class VolunteerViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, Kategori, Harga, Lokasi, Tanggal, Keterangan;
        ImageView Gambar;

        public VolunteerViewHolder(@NonNull View itemView) {
            super(itemView);

            Gambar = itemView.findViewById(R.id.img_3);
            Judul = itemView.findViewById(R.id.txt_title);
            Kategori = itemView.findViewById(R.id.txt_kategori);
            Tanggal = itemView.findViewById(R.id.txt_tanggal);
            Lokasi = itemView.findViewById(R.id.txt_lokasi);
        }
    }
}






