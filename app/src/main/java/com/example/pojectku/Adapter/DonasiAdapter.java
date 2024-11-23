package com.example.pojectku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.pojectku.Activity.LoginActivity;
import com.example.pojectku.Item.ItemDonasi;
import com.example.pojectku.R;
import com.example.pojectku.TampilDonasi;

import java.util.ArrayList;

public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.ViewHolder> {

    private ArrayList<ItemDonasi> itemList;
    private Context context; // Menambahkan context

    // Constructor yang menerima context
    public DonasiAdapter(Context context, ArrayList<ItemDonasi> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public DonasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donasi, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DonasiAdapter.ViewHolder holder, int position) {
        ItemDonasi item = itemList.get(position);

        // Menyusun gambar menggunakan Glide
        int drawableResId = holder.itemView.getResources().getIdentifier(item.getPicture(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(), new GranularRoundedCorners(40, 40, 40, 40))
                .into(holder.img);

        // Set data title dan category ke TextView
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());

        // Set OnClickListener untuk tiap item
        holder.itemView.setOnClickListener(v -> {
            // Membuat Intent untuk menampilkan detail donasi
            Intent intent = new Intent(context, TampilDonasi.class);
            intent.putExtra("title", item.getTitle()); // Menambahkan data title ke intent
            intent.putExtra("category", item.getCategory()); // Menambahkan kategori
            intent.putExtra("image", item.getPicture()); // Menambahkan ID gambar ke intent
            context.startActivity(intent); // Memulai Activity
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView category, title;
        Button donasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_2);
            category = itemView.findViewById(R.id.txt_kategori);
            title = itemView.findViewById(R.id.txt_title);
            donasi = itemView.findViewById(R.id.btn_donate);
        }
    }
}
