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

public class TampilDonasi extends AppCompatActivity {

   private LinearLayout back;
   private Button donate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_donasi);


        TextView title = findViewById(R.id.txt_title);
        TextView kategori = findViewById(R.id.txt_kategori);
        TextView target = findViewById(R.id.txt_target);
        TextView terkumpul = findViewById(R.id.txt_terkumpul);
        TextView keterangan = findViewById(R.id.txt_keterangan);
        ImageView image = findViewById(R.id.img_donasi);
        TextView status = findViewById(R.id.txt_status);
        TextView lokasi = findViewById(R.id.txt_lokasi);
        TextView tenggat = findViewById(R.id.txt_tenggat);

        back = findViewById(R.id.backButton);
        donate = findViewById(R.id.btn_donate);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        Intent intent = getIntent();
        String judul = intent.getStringExtra("judul");
        String kategoriText = intent.getStringExtra("kategori");
        String targetText = intent.getStringExtra("target");
        String terkumpulText = intent.getStringExtra("terkumpul");
        String keteranganText = intent.getStringExtra("keterangan");
        String gambarUrl = intent.getStringExtra("gambar");
        String statusText = intent.getStringExtra("status");
        String lokasiText = intent.getStringExtra("lokasi");
        String tenggatDate = intent.getStringExtra("tanggal_tenggat");

        // Set data ke view
        title.setText(judul);
        kategori.setText(kategoriText);
        target.setText(targetText);
        terkumpul.setText(terkumpulText);
        keterangan.setText(keteranganText);
        status.setText(statusText);
        lokasi.setText(lokasiText);
        tenggat.setText(tenggatDate);

        // Muat gambar menggunakan Glide
        Glide.with(this)
                .load(gambarUrl)
                .placeholder(R.drawable.ic_imgkosong)
                .error(R.drawable.ic_imgerror)
                .into(image);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BayarActivity.class));
            }
        });
    }
}

