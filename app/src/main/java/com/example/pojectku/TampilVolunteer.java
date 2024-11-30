package com.example.pojectku;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pojectku.Activity.BayarActivity;
import com.example.pojectku.Activity.MainActivity;

public class TampilVolunteer extends AppCompatActivity {

    private LinearLayout back;
    private Button ikuti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_volunteer);


        TextView title = findViewById(R.id.txt_title);
        TextView kategori = findViewById(R.id.txt_kategori);
        TextView lokasi = findViewById(R.id.txt_lokasi);
        TextView tanggal = findViewById(R.id.txt_tanggal);
        TextView keterangan = findViewById(R.id.txt_keterangan);
        ImageView image = findViewById(R.id.img_volunteer);
        TextView harga = findViewById(R.id.txt_harga);

        back = findViewById(R.id.backButton);
        ikuti = findViewById(R.id.btn_ikuti);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        Intent intent = getIntent();
        String judul = intent.getStringExtra("judul");
        String kategoriText = intent.getStringExtra("kategori");
        String lokasiText = intent.getStringExtra("lokasi");
        String tanggalText = intent.getStringExtra("waktu");
        String keteranganText = intent.getStringExtra("keterangan");
        String gambarUrl = intent.getStringExtra("gambar");
        String hargaText = intent.getStringExtra("target");

        // Set data ke view
        title.setText(judul);
        kategori.setText(kategoriText);
        lokasi.setText(lokasiText);
        tanggal.setText(tanggalText);
        keterangan.setText(keteranganText);
        harga.setText(hargaText);

        // Muat gambar menggunakan Glide
        Glide.with(this)
                .load(gambarUrl)
                .placeholder(R.drawable.ic_imgkosong)
                .error(R.drawable.ic_imgerror)
                .into(image);

        ikuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BayarActivity.class));
            }
        });
    }
}



