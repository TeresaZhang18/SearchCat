package com.example.searchcat.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatBreed {
    @SerializedName("id")
    public String imageid;
    @SerializedName("url")
    public String imageurl;
    public ArrayList<Cat> breeds;

    public String getImageurl() {
        return imageurl;
    }

    public String getImageid() {
        return imageid;
    }
}
