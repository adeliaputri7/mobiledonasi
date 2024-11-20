package com.example.pojectku.Item;

import java.io.Serializable;

public class ImageSlide implements Serializable {

    private String picture;

    public ImageSlide(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
