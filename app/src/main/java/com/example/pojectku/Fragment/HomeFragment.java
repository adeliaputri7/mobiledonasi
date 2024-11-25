package com.example.pojectku.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pojectku.Adapter.DonasiAdapter;
import com.example.pojectku.Adapter.ImageSlideAdapter;
import com.example.pojectku.Adapter.VolunteerAdapter;
import com.example.pojectku.Db_Contract;
import com.example.pojectku.Item.ImageSlide;
import com.example.pojectku.Item.ItemDonasi;
import com.example.pojectku.Item.ItemVolunteer;
import com.example.pojectku.R;
import com.example.pojectku.TampilDonasi;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private LinearLayout lainnya;
    private LinearLayout iconTambahan, donasi, volunteer;
    private RecyclerView recycleDonasi, recycleVolunteer, recycleImage;
    private DonasiAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        lainnya = rootView.findViewById(R.id.btn_lainnya);
        iconTambahan = rootView.findViewById(R.id.icon_tambahan);
        recycleDonasi = rootView.findViewById(R.id.re_donasi);
        recycleVolunteer = rootView.findViewById(R.id.re_volunteer);
        recycleImage = rootView.findViewById(R.id.re_img);
        donasi = rootView.findViewById(R.id.item_donasi);
        volunteer = rootView.findViewById(R.id.item_voll);


        recycleDonasi = rootView.findViewById(R.id.re_donasi);
        recycleDonasi.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new DonasiAdapter(requireContext(), new JSONArray()); // Placeholder kosong
        recycleDonasi.setAdapter(adapter);


            // Fetch data dari API
            fetchDonations();











        if (lainnya != null) {
            lainnya.setOnClickListener(v -> {
                // Aksi saat klik
                // Contoh: menampilkan ikon tambahan
                if (iconTambahan.getVisibility() == View.GONE) {
                    iconTambahan.setVisibility(View.VISIBLE);
                } else {
                    iconTambahan.setVisibility(View.GONE);
                }
            });
        } else {
            Log.e("HomeFragment", "View 'lainnya' tidak ditemukan!");
        }

        return rootView;
    }

    private void fetchDonations() {
        String url = Db_Contract.urlDonasi; // URL API

        // Buat RequestQueue
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        // Buat JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // Periksa status respons
                        if (response.getString("status").equals("success")) {
                            // Ambil data JSON
                            JSONArray data = response.getJSONArray("data");

                            // Set adapter dengan data yang didapat
                            adapter = new DonasiAdapter(getContext(), data);
                            recycleDonasi.setAdapter(adapter);

                        } else {
                            Toast.makeText(getContext(), "Error: " + response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "JSON Parsing Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getContext(), "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        // Tambahkan request ke RequestQueue
        queue.add(jsonObjectRequest);
    }


}
