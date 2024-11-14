package com.example.pojectku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtname, edtemail, edtpass;
    private Button btndaftar;
    private TextView txtmasuk;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        edtname = findViewById(R.id.edt_name);
        edtemail = findViewById(R.id.edt_email);
        edtpass = findViewById(R.id.edt_pass);
        btndaftar = findViewById(R.id.btn_daftar);
        txtmasuk = findViewById(R.id.txt_masuk);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                String nama = edtname.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String password = edtpass.getText().toString().trim();

                if (nama.isEmpty()){
                    edtname.setError("Nama tidak boleh kosong");
                    edtname.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    edtemail.setError("Email tidak boleh kosong");
                    edtemail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    edtpass.setError("Password tidak boleh kosong");
                    edtpass.requestFocus();
                    return;
                }
                registerUser(email, password);

            }
        });
        txtmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(log);
                finish();
            }
        });
    }
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    user.sendEmailVerification().addOnCompleteListener(emailTask -> {
                        if (emailTask.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Register berhasil! Tolong check email anda untuk verifikasi", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Gagal terkirim ke email", Toast.LENGTH_SHORT).show();
                            ;
                        }
                    });
                }
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Registrasi gagal: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
