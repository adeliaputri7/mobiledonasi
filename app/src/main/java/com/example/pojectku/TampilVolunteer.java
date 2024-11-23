package com.example.pojectku;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pojectku.Activity.MainActivity;

public class TampilVolunteer extends AppCompatActivity {

    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_volunteer);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // Ambil data dari Intent
        String title = getIntent().getStringExtra("title");
        String category = getIntent().getStringExtra("category");
        String imageName = getIntent().getStringExtra("image"); // Ambil nama gambar

        // Temukan komponen UI
        TextView titleTextView = findViewById(R.id.txt_title);
        TextView categoryTextView = findViewById(R.id.txt_kategori);
        ImageView imageView = findViewById(R.id.img_volunteer);

        // Set data ke UI
        titleTextView.setText(title);
        categoryTextView.setText(category);

        // Gunakan Glide untuk memuat gambar
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        Glide.with(this)
                .load(imageResId)  // Menampilkan gambar berdasarkan resource ID
                .into(imageView);
    }
}

