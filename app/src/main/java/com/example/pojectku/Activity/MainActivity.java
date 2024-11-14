package com.example.pojectku.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pojectku.Fragment.AccountFragment;
import com.example.pojectku.Fragment.HomeFragment;
import com.example.pojectku.Fragment.NotivicationFragment;
import com.example.pojectku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        if (item.getItemId() == R.id.fr_home) {
            fragment = new HomeFragment();
        } else if (item.getItemId() == R.id.fr_notif) {
            fragment = new NotivicationFragment();
        } else if (item.getItemId() == R.id.fr_akun) {
            fragment = new AccountFragment();
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_lay, fragment) // Menambahkan commit di sini
                    .commit();
            return true;
        }
        return false;
    }
}

