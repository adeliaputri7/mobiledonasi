package com.example.pojectku.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.pojectku.Adapter.DonasiAdapter;
import com.example.pojectku.Adapter.ImageSlideAdapter;
import com.example.pojectku.Item.ImageSlide;
import com.example.pojectku.Item.ItemDonasi;
import com.example.pojectku.R;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private LinearLayout lainnya;
    private LinearLayout iconTambahan;
    private RecyclerView recycleDonasi;
    private ViewPager2 viewPager2;

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

        lainnya = rootView.findViewById(R.id.lainnya);
        iconTambahan = rootView.findViewById(R.id.icon_tambahan);
        recycleDonasi = rootView.findViewById(R.id.re_donasi);
        viewPager2 = rootView.findViewById(R.id.view_page);

        // Inisialisasi RecyclerView dan ViewPager2
        initRecycleView();
        initImageSlider();

        // Menambahkan toggle untuk ikon tambahan
        lainnya.setOnClickListener(v -> {
            if (iconTambahan.getVisibility() == View.GONE) {
                iconTambahan.setVisibility(View.VISIBLE);
            } else {
                iconTambahan.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    private void initRecycleView() {
        // Membuat data item donasi
        ArrayList<ItemDonasi> itemList = new ArrayList<>();
        itemList.add(new ItemDonasi("image1", "Bencana", "Solidaritas untuk Palestina.", 1000));
        itemList.add(new ItemDonasi("image2", "Edukasi", "Berbagi 500 buku untuk saudara pelosok kita.", 1000));
        itemList.add(new ItemDonasi("image3", "Kesehatan", "Ekplorasi keanekaragaman hayati kebun raya mangrove", 1000));

        // Mengatur LayoutManager horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleDonasi.setLayoutManager(layoutManager);

        // Mengatur adapter untuk RecyclerView
        DonasiAdapter adapterDonasi = new DonasiAdapter(itemList);
        recycleDonasi.setAdapter(adapterDonasi);
    }

    private void initImageSlider() {
        // Data gambar slider
        List<ImageSlide> sliderItems = new ArrayList<>();
        sliderItems.add(new ImageSlide(R.drawable.image1));
        sliderItems.add(new ImageSlide(R.drawable.image2));
        sliderItems.add(new ImageSlide(R.drawable.image3));

        // Mengatur adapter untuk ViewPager2
        ImageSlideAdapter imageSlideAdapter = new ImageSlideAdapter(sliderItems, viewPager2, 3000, v -> {
            viewPager2.setCurrentItem(1);
        });

        viewPager2.setAdapter(imageSlideAdapter);

        // Mengatur jarak antar halaman
        viewPager2.setPageTransformer((page, position) -> {
            float offset = 20 * Math.abs(position);
            page.setTranslationX(-offset);
        });
    }
}
