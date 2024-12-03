package com.example.pojectku.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtemail, edtpass;
    private Button btnmasuk;
    private TextView txtdaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi komponen UI
        edtemail = findViewById(R.id.edt_email);
        edtpass = findViewById(R.id.edt_pass);
        btnmasuk = findViewById(R.id.btn_masuk);
        txtdaftar = findViewById(R.id.txt_daftar);

        // Aksi tombol Login
        btnmasuk.setOnClickListener(v -> loginUser());

        // Aksi tombol Daftar
        txtdaftar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = edtemail.getText().toString().trim();
        String password = edtpass.getText().toString().trim();

        // Validasi input kosong
        if (email.isEmpty()) {
            edtemail.setError("Email tidak boleh kosong");
            edtemail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtpass.setError("Password tidak boleh kosong");
            edtpass.requestFocus();
            return;
        }

        // URL API
        String url = "http://10.0.2.2/my_api_android/api-login.php";

        // Membuat body JSON untuk permintaan POST
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Membuat request POST
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                response -> {
                    try {
                        // Parsing respons dari server
                        String status = response.getString("status");
                        String message = response.getString("message");

                        if (status.equals("success")) {
                            // Ambil id_user dan id_donasi dari respons API
                            int idUser = response.getJSONObject("user").getInt("id_user");
                            String idDonasi = response.getJSONObject("user").optString("id_donasi", null);

                            // Simpan id_user dan id_donasi di SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("id_user", idUser); // Simpan ID User
                            if (idDonasi != null) {
                                editor.putString("id_donasi", idDonasi); // Simpan ID Donasi (jika ada)
                            }
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();

                            // Redirect ke MainActivity jika login berhasil
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Menghentikan activity login agar pengguna tidak bisa kembali
                        } else {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Kesalahan parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Kesalahan koneksi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("LoginError", "Error: " + error.getMessage());
                });

        // Tambahkan request ke antrian Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

}
