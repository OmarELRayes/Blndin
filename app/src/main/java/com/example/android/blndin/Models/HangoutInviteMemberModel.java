package com.example.android.blndin.Models;

/**
 * Created by LeGenÐ on 5/8/2018.
 */

public class HangoutInviteMemberModel {
    String id;
    String name;
    String img;

    public HangoutInviteMemberModel(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
