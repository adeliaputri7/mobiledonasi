package com.example.pojectku.Item;

import java.io.Serializable;

public class ItemDonasi implements Serializable {

    private String picture;
    private String categori;
    private String title;
    private int terkumpul;

    public ItemDonasi(String picture, String categori, String title, int terkumpul) {
        this.picture = picture;
        this.categori = categori;
        this.title = title;
        this.terkumpul = terkumpul;
    }

    public String getPicture() {

        return picture;
    }

    public void setPicture(String picture) {

        this.picture = picture;
    }

    public String getCategori() {

        return categori;
    }

    public void setCategori(String categori) {

        this.categori = categori;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public int getTerkumpul() {

        return terkumpul;
    }

    public void setTerkumpul(int terkumpul) {

        this.terkumpul = terkumpul;
    }
}

