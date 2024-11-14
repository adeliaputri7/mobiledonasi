package com.example.pojectku.Item;

import android.content.ClipData;

import java.io.Serializable;

public class ItemVolunteer implements Serializable {

    private String picture;
    private String categori;
    private String title;
    private int price;

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

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public ItemVolunteer(String picture, String categori, String title, int price) {
        this.picture = picture;
        this.categori = categori;
        this.title = title;
        this.price = price;
    }
}


