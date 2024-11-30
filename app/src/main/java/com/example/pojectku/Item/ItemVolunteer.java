package com.example.pojectku.Item;

import android.content.ClipData;

import java.io.Serializable;

public class ItemVolunteer implements Serializable {

    private String id;
    private String gambar;
    private String judul;
    private String kategori;
    private int harga;
    private String keterangan;
    private String tanggal;

    public ItemVolunteer(String id, String gambar, String judul, String kategori, int harga, String keterangan, String tanggal) {
        this.id = id;
        this.gambar = gambar;
        this.judul = judul;
        this.kategori = kategori;
        this.harga = harga;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}


