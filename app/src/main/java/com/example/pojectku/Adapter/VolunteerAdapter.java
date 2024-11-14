package com.example.pojectku.Adapter;

import java.io.Serializable;

public class VolunteerAdapter implements Serializable {

    private String categori;
    private String title;
    private int price;

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

    public VolunteerAdapter(String categori, String title, int price) {
        this.categori = categori;
        this.title = title;
        this.price = price;
    }
}

