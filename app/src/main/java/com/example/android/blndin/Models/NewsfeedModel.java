package com.example.android.blndin.Models;

/**
 * Created by Luffy on 4/16/2018.
 */

public class NewsfeedModel {
    String name;
    Boolean liked;
    public NewsfeedModel(String name,Boolean liked) {
        this.name = name;
        this.liked=liked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
