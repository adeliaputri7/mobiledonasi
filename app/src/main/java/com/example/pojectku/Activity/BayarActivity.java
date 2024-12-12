package com.example.pojectku.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BayarActivity extends AppCompatActivity {

    private Spinner spinnerBank;
    private EditText etDonationAmount;
    private TextView tanggal;
    private int idUser;
    private int idDonasi; // ID Donasi yang diterima dari Intent
    private ArrayList<String> bankNames = new ArrayList<>();
    private ArrayList<String> bankid = new ArrayList<>();
    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        tanggal = findViewById(R.id.txt_tanggal);
        spinnerBank = findViewById(R.id.spinner_payment_method);
        etDonationAmount = findViewById(R.id.et_donation_amount); // Inisialisasi EditText

        // Mengambil ID User dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        idUser = sharedPreferences.getInt("id_user", -1); // Jika tidak ditemukan, default -1

        if (idUser == -1) {
            Toast.makeText(this, "ID User tidak ditemukan!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengambil ID Donasi dari SharedPreferences
        SharedPreferences sharedPreferencess = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        idDonasi = sharedPreferencess.getInt("id_donasi", -1);

        if (idDonasi == -1) {
            Toast.makeText(this, "ID Donasi tidak ditemukan!", Toast.LENGTH_SHORT).show();
            finish(); // Tutup activity jika id_donasi tidak ditemukan
            return;
        }

        // Mengambil daftar bank dari API
        fetchBanks();
        setCurrentDate();

        // Lanjutkan pembayaran button
        findViewById(R.id.btn_continue).setOnClickListener(view -> {
            // Ambil data dari spinner dan nominal pembayaran
            String selectedBank = spinnerBank.getSelectedItem().toString();
            String donationAmountStr = etDonationAmount.getText().toString().trim();

            if (donationAmountStr.isEmpty()) {
                Toast.makeText(BayarActivity.this, "Nominal pembayaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mengonversi nominal pembayaran menjadi int
            int donationAmount = 10000;
            try {
                donationAmount = Integer.parseInt(donationAmountStr);
            } catch (NumberFormatException e) {
                Toast.makeText(BayarActivity.this, "Nominal pembayaran tidak valid", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kirim pembayaran dengan nominal
            submitPayment(selectedBank, donationAmount);
        });
    }

    private void setCurrentDate() {
        // Mendapatkan tanggal hari ini
        Calendar calendar = Calendar.getInstance();
        currentDate = calendar.getTime(); // Menyimpan objek Date

        // Menampilkan tanggal pada TextView dalam format "YYYY-MM-DD"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(currentDate);
        tanggal.setText(formattedDate);
        tanggal.setEnabled(false); // Jika tidak ingin agar pengguna mengedit tanggal
    }

    // Fungsi untuk mengambil daftar bank dari API
    private void fetchBanks() {
        String url = "http://10.0.2.2/my_api_android/api-bank.php"; // Ganti dengan URL API Anda

        // Membuat request GET untuk mengambil data bank
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // Mengecek apakah respons memiliki status sukses
                        String status = response.getString("status");
                        if (status.equals("success")) {
                            // Ambil array data bank
                            JSONArray banks = response.getJSONArray("data");

                            // Menyusun nama bank untuk dimasukkan ke dalam spinner
                            bankNames.clear();
                            bankid.clear();
                            for (int i = 0; i < banks.length(); i++) {
                                JSONObject bank = banks.getJSONObject(i);
                                String namaBank = bank.getString("payment");
                                int idBank = bank.getInt("id"); // ID bank
                                bankNames.add(namaBank);       // Nama bank untuk ditampilkan di spinner
                                bankid.add(String.valueOf(idBank));  // ID bank untuk dipilih pada submitPayment
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
        requestQueue.add(jsonObjectRequest);
    }

    // Fungsi untuk mengirim pembayaran menggunakan Volley
    private void submitPayment(String selectedBank, int donationAmount) {
        if (idUser == -1 || idDonasi == -1) {
            Toast.makeText(this, "ID User atau ID Donasi tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengambil ID bank yang dipilih dari spinner
        int selectedBankId = Integer.parseInt(bankid.get(spinnerBank.getSelectedItemPosition()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(currentDate);

        // URL API pembayaran
        String url = "http://10.0.2.2/my_api_android/api-pembayaran.php"; // Ganti dengan URL API Anda

        // Membuat request body JSON
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("id_user", idUser);
            requestBody.put("id_donasi", idDonasi);
            requestBody.put("id_bank", selectedBankId);  // Mengirim ID bank (yang dipilih)
            requestBody.put("tanggal_donasi", formattedDate);  // Mengirim tanggal dalam format timestamp atau milidetik
            requestBody.put("nominal_donasi", donationAmount); // Menambahkan nominal pembayaran ke request
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
