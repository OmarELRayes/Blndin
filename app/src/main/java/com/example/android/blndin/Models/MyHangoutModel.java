package com.example.android.blndin.Models;

/**
 * Created by LeGenÐ on 4/21/2018.
 */

public class MyHangoutModel {
    private int id;
    private String name;
    private String imageUrl;

    public MyHangoutModel(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
