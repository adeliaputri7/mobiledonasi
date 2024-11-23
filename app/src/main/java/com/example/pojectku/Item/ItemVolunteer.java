package com.example.pojectku.Item;

import android.content.ClipData;

import java.io.Serializable;

public class ItemVolunteer implements Serializable {

    private String picture;
    private String category;
    private String title;
    private int price;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {

        this.picture = picture;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String categori) {

        this.category = categori;
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
        this.category = category;
        this.title = title;
        this.price = price;
    }
}


