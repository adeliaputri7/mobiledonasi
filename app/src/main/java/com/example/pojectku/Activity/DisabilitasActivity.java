package com.example.pojectku.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.Adapter.AdapterBencanaDLS;
import com.example.pojectku.R;

import org.json.JSONArray;
import org.json.JSONException;

public class DisabilitasActivity extends AppCompatActivity {

    private RecyclerView recycleDonasi;
    private AdapterBencanaDLS donasiAdapter;
    private LinearLayout kembali;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bencana); // Pastikan layout sesuai dengan activity ini

        recycleDonasi = findViewById(R.id.re_bencana); // Inisialisasi RecyclerView
        judul = findViewById(R.id.headerTitle);

        judul.setText("Disabilitas");
        judul.setTextSize(32);
        judul.setTextColor(getResources().getColor(android.R.color.black));

        // Set LayoutManager untuk RecyclerView
        recycleDonasi.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi adapter dengan data kosong
        donasiAdapter = new AdapterBencanaDLS(this, new JSONArray()); // Placeholder kosong
        recycleDonasi.setAdapter(donasiAdapter);

        kembali = findViewById(R.id.backButton);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // Memanggil API untuk mendapatkan data donasi
        getDonasiBencana();
    }

    private void getDonasiBencana() {
        String url = "http://10.0.2.2/my_api_android/api-disabilitas.php"; // Ganti dengan URL API Anda

        // Membuat RequestQueue untuk Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        // Membuat JsonObjectRequest untuk mendapatkan data dari API
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // Periksa status respons
                        if (response.getString("status").equals("success")) {
                            // Ambil data JSON
                            JSONArray data = response.getJSONArray("data");

                            // Set adapter dengan data yang diterima
                            donasiAdapter.updateData(data); // Method untuk update data dalam adapter
                        } else {
                            // Menampilkan pesan jika status response gagal
                            Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        // Menampilkan error jika terjadi masalah parsing JSON
                        Toast.makeText(this, "JSON Parsing Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Menampilkan error jika terjadi masalah dengan jaringan
                    Toast.makeText(this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        // Menambahkan request ke queue
        queue.add(jsonObjectRequest);
    }
}
