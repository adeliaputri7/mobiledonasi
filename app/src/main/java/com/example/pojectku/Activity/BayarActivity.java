package com.example.pojectku.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pojectku.R;

public class BayarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        // Inisialisasi elemen UI
        EditText etNominal = findViewById(R.id.et_donation_amount);
        Spinner spinnerPaymentMethod = findViewById(R.id.spinner_payment_method);
        Button btnPay = findViewById(R.id.btn_continue);

        // Tambahkan aksi pada tombol bayar
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nominal = etNominal.getText().toString();
                String selectedMethod = spinnerPaymentMethod.getSelectedItem().toString();

                if (nominal.isEmpty()) {
                    Toast.makeText(BayarActivity.this, "Nominal tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    // Contoh logika ketika data sudah diisi
                    Toast.makeText(
                            BayarActivity.this,
                            "Anda akan membayar Rp " + nominal + " menggunakan " + selectedMethod,
                            Toast.LENGTH_LONG
                    ).show();

                    // Di sini, Anda dapat meneruskan data ke API atau ke halaman berikutnya
                }
            }
        });
    }
}
