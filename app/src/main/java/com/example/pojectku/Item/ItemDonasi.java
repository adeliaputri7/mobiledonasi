package com.example.pojectku.Item;

import java.io.Serializable;

public class ItemDonasi implements Serializable {

    private String picture;
    private String category;
    private String title;
    private int terkumpul;
    private boolean donasi;

    // Konstruktor untuk ItemDonasi
    public ItemDonasi(String picture, String category, String title, int terkumpul) {
        this.picture = picture;  // Gambar donasi
        this.category = category;  // Kategori donasi
        this.title = title;  // Judul donasi
        this.terkumpul = terkumpul;
        this.donasi = donasi;// Jumlah terkumpul
    }

    public boolean isDonasi() {
        return donasi;
    }

    public void setDonasi(boolean donasi) {
        this.donasi = donasi;
    }

    public ItemDonasi(boolean donasi) {
        this.donasi = donasi;
    }

    // Getter dan Setter untuk picture
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    // Getter dan Setter untuk category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter dan Setter untuk title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter dan Setter untuk terkumpul
    public int getTerkumpul() {
        return terkumpul;
    }

    public void setTerkumpul(int terkumpul) {
        this.terkumpul = terkumpul;
    }

}
