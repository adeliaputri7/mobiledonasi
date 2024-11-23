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
import com.example.pojectku.Adapter.DonasiAdapter;
import com.example.pojectku.Adapter.ImageSlideAdapter;
import com.example.pojectku.Adapter.VolunteerAdapter;
import com.example.pojectku.Item.ImageSlide;
import com.example.pojectku.Item.ItemDonasi;
import com.example.pojectku.Item.ItemVolunteer;
import com.example.pojectku.R;
import com.example.pojectku.TampilDonasi;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private LinearLayout lainnya;
    private LinearLayout iconTambahan, donasi, volunteer;
    private RecyclerView recycleDonasi, recycleVolunteer, recycleImage;



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

        initRecycleView();









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

    private void initRecycleView() {
        // Membuat data item donasi
        {
            ArrayList<ItemDonasi> itemList = new ArrayList<>();
            itemList.add(new ItemDonasi("image1", "Bencana", "Solidaritas untuk Palestina.", 1000));
            itemList.add(new ItemDonasi("image2", "Edukasi", "Berbagi 500 buku untuk saudara pelosok kita.", 1000));
            itemList.add(new ItemDonasi("image3", "Kesehatan", "Ekplorasi keanekaragaman hayati kebun raya mangrove", 1000));

            // Mengatur LayoutManager horizontal
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recycleDonasi.setLayoutManager(layoutManager);

            // Mengatur adapter untuk RecyclerView
            DonasiAdapter adapterDonasi = new DonasiAdapter(getContext(), itemList);
            recycleDonasi.setAdapter(adapterDonasi);
        }
        {
            ArrayList<ItemVolunteer> itemList = new ArrayList<>();
            itemList.add(new ItemVolunteer("img_volunteer1", "Lingkungan", "Bagi_Bagi Makanan", 1000));
            itemList.add(new ItemVolunteer("img_volunteer2", "Lingkungan", "Menanam Tumbuhan", 1000));
            itemList.add(new ItemVolunteer("img_volunteer3", "Lingkungan", "Gotong Royong Membersihkan Sampah", 1000));

            // Mengatur LayoutManager horizontal
            LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recycleVolunteer.setLayoutManager(layout);

            // Mengatur adapter untuk RecyclerView
            VolunteerAdapter volunteerAdapter = new VolunteerAdapter(getContext(), itemList);
            recycleVolunteer.setAdapter(volunteerAdapter);
        }

        ArrayList<ImageSlide> items = new ArrayList<>();
        items.add(new ImageSlide("image1"));
        items.add(new ImageSlide("image2"));
        items.add(new ImageSlide("img_volunteer1"));
        items.add(new ImageSlide("img_volunteer2"));

        // Mengatur LayoutManager horizontal
        LinearLayoutManager layouts = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleImage.setLayoutManager(layouts);

        // Mengatur adapter untuk RecyclerView
        ImageSlideAdapter imageSlideAdapter = new ImageSlideAdapter(items);
        recycleImage.setAdapter(imageSlideAdapter);


    }


}
