package com.example.projectku;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojectku.MainActivity;
import com.example.pojectku.RegisterActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;



public class LoginActivity extends AppCompatActivity {

    private EditText edtemail, edtpass;
    private Button btnlogin;
    private TextView txtdaftar, txtlppass;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sampleCollection")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("Firestore", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.w("Firestore", "Error.", task.getException());
                    }
                });

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d("AddTaskFragment", "User is authenticated with UID" + user.getUid());
        } else {
            Log.d("AddTaskFragment", "User not authenticated");
        }

        mAuth = FirebaseAuth.getInstance();
        edtemail= findViewById(R.id.edt_email);
        edtpass = findViewById(R.id.edt_pass);
        btnlogin = findViewById(R.id.btn_masuk);
        txtdaftar = findViewById(R.id.txt_daftar);
        txtlppass = findViewById(R.id.lupa_pass);



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString().trim();
                String password = edtpass.getText().toString().trim();

                if (email.isEmpty()) {
                    edtemail.setError("Email harus ada");
                    edtemail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    edtpass.setError("Password harus ada");
                    edtpass.requestFocus();
                    return;
                }
                LoginUser(email, password);
            }
        });

        txtdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(log);
                finish();
            }
        });

        txtlppass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString().trim();
                if (email.isEmpty()) {
                    edtemail.setError("Email harus ada");
                    edtemail.requestFocus();
                } else {
                    resetPassword(email);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.log), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void LoginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(LoginActivity.this, "Login sukses", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login gagal" + Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Email untuk reset password sudah dikirim", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Gagal mengirim reset email" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}