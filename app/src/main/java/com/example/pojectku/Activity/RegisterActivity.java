package com.example.pojectku.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtname, edtemail, edtpass;
    private Button btndaftar;
    private TextView txtmasuk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtname = findViewById(R.id.edt_name);
        edtemail = findViewById(R.id.edt_email);
        edtpass = findViewById(R.id.edt_pass);
        btndaftar = findViewById(R.id.btn_daftar);
        txtmasuk = findViewById(R.id.txt_masuk);

        txtmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edtname.getText().toString();
                String email = edtemail.getText().toString();
                String password = edtpass.getText().toString();


                if (!email.isEmpty() && !password.isEmpty()) {
                    // Membuat request JSON untuk login
                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("nama", nama);
                        postData.put("email", email);
                        postData.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    String url = "http://10.0.2.2/my_api_android/api-register.php";  // Ganti dengan URL API Anda

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String status = response.getString("status");
                                String message = response.getString("message");

                                if ("success".equals(status)) {
                                    Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                                    // Pindah ke halaman utama setelah Daftar berhasil
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Daftar Gagal: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Tambahkan request ke request queue
                    requestQueue.add(jsonObjectRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Nama, Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

