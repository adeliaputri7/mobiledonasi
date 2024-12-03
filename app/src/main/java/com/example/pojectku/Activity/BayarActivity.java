package com.example.pojectku.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BayarActivity extends AppCompatActivity {

    private Spinner spinnerBank;
    private EditText etDonationAmount; // Tambahkan EditText untuk nominal pembayaran
    private int idUser;
    private int idDonasi; // ID Donasi yang diterima dari Intent
    private ArrayList<String> bankNames = new ArrayList<>(); // Menyimpan daftar bank

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        spinnerBank = findViewById(R.id.spinner_payment_method);
        etDonationAmount = findViewById(R.id.et_donation_amount); // Inisialisasi EditText

        // Ambil ID User dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        idUser = sharedPreferences.getInt("id_user", -1); // Ambil ID User yang sudah disimpan

        // Ambil ID Donasi dan data lainnya dari Intent
        idDonasi = getIntent().getIntExtra("id_donasi", -1);  // Ambil ID Donasi dari Intent

        // Mengambil daftar bank dari API
        fetchBanks();

        // Lanjutkan pembayaran button
        findViewById(R.id.btn_continue).setOnClickListener(view -> {
            // Ambil data dari spinner dan nominal pembayaran
            String selectedBank = spinnerBank.getSelectedItem().toString();
            String donationAmount = etDonationAmount.getText().toString().trim();

            // Validasi input
            if (donationAmount.isEmpty()) {
                Toast.makeText(BayarActivity.this, "Nominal pembayaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            submitPayment(selectedBank, donationAmount); // Kirim pembayaran dengan nominal
        });
    }

    // Fungsi untuk mengambil daftar bank dari API
    private void fetchBanks() {
        String url = "http://10.0.2.2/my_api_android/api-bank.php"; // Ganti dengan URL API Anda

        // Membuat request GET untuk mengambil data bank
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // Mengecek apakah respons memiliki status sukses
                        JSONObject jsonResponse = response.getJSONObject(0); // Mengambil objek pertama dalam array
                        String status = jsonResponse.getString("status");
                        if (status.equals("success")) {
                            // Ambil array data bank
                            JSONArray banks = jsonResponse.getJSONArray("data");

                            // Menyusun nama bank untuk dimasukkan ke dalam spinner
                            bankNames.clear();
                            for (int i = 0; i < banks.length(); i++) {
                                JSONObject bank = banks.getJSONObject(i);
                                String bankName = bank.getString("payment"); // Nama bank
                                bankNames.add(bankName); // Menambahkan nama bank ke list
                            }

                            // Menampilkan data ke dalam spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(BayarActivity.this,
                                    android.R.layout.simple_spinner_item, bankNames);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerBank.setAdapter(adapter);

                        } else {
                            Toast.makeText(BayarActivity.this, "Tidak ada data bank", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(BayarActivity.this, "Kesalahan parsing data", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Menangani error jika request gagal
                    Toast.makeText(BayarActivity.this, "Gagal mengambil data bank: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        // Menambahkan request ke queue Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    // Fungsi untuk mengirim pembayaran menggunakan Volley
    private void submitPayment(String selectedBank, String donationAmount) {
        if (idUser == -1 || idDonasi == -1) {
            Toast.makeText(this, "ID User atau ID Donasi tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        // URL API pembayaran
        String url = "http://10.0.2.2/my_api_android/api-pembayaran.php"; // Ganti dengan URL API Anda

        // Membuat request body JSON
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("id_user", idUser);
            requestBody.put("id_donasi", idDonasi);
            requestBody.put("bank", selectedBank);
            requestBody.put("nominal", donationAmount); // Menambahkan nominal pembayaran ke request
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Membuat permintaan JSON dengan Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                response -> {
                    try {
                        String status = response.getString("status");
                        String message = response.getString("message");

                        if (status.equals("success")) {
                            Toast.makeText(BayarActivity.this, "Pembayaran Berhasil!", Toast.LENGTH_SHORT).show();
                            // Redirect atau aksi lain setelah pembayaran berhasil
                        } else {
                            Toast.makeText(BayarActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(BayarActivity.this, "Kesalahan parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(BayarActivity.this, "Kesalahan koneksi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        // Menambahkan permintaan ke antrian Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
