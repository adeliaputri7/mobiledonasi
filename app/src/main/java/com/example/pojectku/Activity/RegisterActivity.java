package com.example.pojectku.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.R;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtnama, edtemail, edtpass, edttlp;
    private Button btndaftar;
    private TextView txtmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtnama = findViewById(R.id.ed_nama);
        edtemail = findViewById(R.id.ed_email);
        edtpass = findViewById(R.id.ed_pass);
        edttlp = findViewById(R.id.ed_notlp);
        btndaftar = findViewById(R.id.btn_daftar);
        txtmasuk = findViewById(R.id.txt_masuk);


        btndaftar.setOnClickListener(v -> registerUser());

        txtmasuk.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private void registerUser() {
        String username = edtnama.getText().toString().trim();
        String email = edtemail.getText().toString().trim();
        String password = edtpass.getText().toString().trim();
        String no_telp = edttlp.getText().toString().trim();

        if (username.isEmpty()) {
            edtnama.setError("Nama tidak boleh kosong");
            edtnama.requestFocus();
            return;
        }
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
        if (no_telp.isEmpty()) {
            edttlp.setError("No telpon tidak boleh kosong");
            edttlp.requestFocus();
            return;
        }

        // URL API
        String url = "http://192.168.50.29/my_api_android/api-register.php";

        // Membuat body JSON
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username", username);
            requestBody.put("email", email);
            requestBody.put("password", password);
            requestBody.put("no_telp", no_telp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kirim permintaan ke server
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                response -> {
                    try {
                        // Parsing respons dari server
                        String status = response.getString("status");
                        String message = response.getString("message");

                        if (status.equals("success")) {
                            Toast.makeText(RegisterActivity.this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();

                            // Redirect ke MainActivity jika register berhasil
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Kesalahan parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Kesalahan koneksi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("LoginError", "Error: " + error.getMessage());
                });

        // Tambahkan request ke antrian Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}


