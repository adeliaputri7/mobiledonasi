package com.example.pojectku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.DonationViewHolder> {

    private Context context;
    private JSONArray donasiList;

    // Constructor
    public DonasiAdapter(Context context, JSONArray donasiList) {
        this.context = context;
        this.donasiList = donasiList;
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
            JSONObject donasi = donasiList.getJSONObject(position);

            // Isi data ke dalam TextView dan ImageView
            holder.Judul.setText(donasi.getString("judul"));
            holder.Kategori.setText(donasi.getString("kategori"));
            holder.Target.setText("Target: " + donasi.getString("target"));
            holder.Terkumpul.setText("Terkumpul: " + donasi.getString("terkumpul"));
            holder.Status.setText("Status: " + donasi.getString("status"));

            String imageUrl = donasi.getString("gambar");
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_imgkosong)
                    .error(R.drawable.ic_imgerror)
                    .into(holder.Gambar);

            // Klik item untuk menyimpan id_donasi dan buka TampilDonasi
            holder.itemView.setOnClickListener(v -> {
                try {
                    // Ambil id_donasi
                    int idDonasi = donasi.getInt("id_donasi");

                    Log.d("DonasiAdapter", "ID Donasi: " + idDonasi);

                    // Simpan ke SharedPreferences
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("id_donasi", idDonasi); // Simpan hanya id_donasi
                    editor.apply();

                    // Kirim data lainnya melalui Intent
                    Intent intent = new Intent(context, TampilDonasi.class);
                    intent.putExtra("judul", donasi.getString("judul"));
                    intent.putExtra("kategori", donasi.getString("kategori"));
                    intent.putExtra("target", "Target: " + donasi.getString("target"));
                    intent.putExtra("terkumpul", "Terkumpul: " + donasi.getString("terkumpul"));
                    intent.putExtra("keterangan", "Keterangan: " + donasi.getString("keterangan"));
                    intent.putExtra("gambar", donasi.getString("gambar"));
                    intent.putExtra("lokasi", "Lokasi: " + donasi.getString("lokasi"));
                    intent.putExtra("tanggal_tenggat", "Berakhir: " + donasi.getString("tanggal_tenggat"));
                    intent.putExtra("status", "Status: " + donasi.getString("status"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DonasiAdapter", "Error parsing JSON: " + e.getMessage());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return donasiList.length();
    }

    // ViewHolder class
    public static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, Kategori, Target, Terkumpul, Status, Keterangan;
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
