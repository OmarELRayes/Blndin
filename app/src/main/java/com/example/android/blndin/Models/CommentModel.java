package com.example.android.blndin.Models;

/**
 * Created by Luffy on 5/13/2018.
 */

public class CommentModel {
    String id;
    String name;
    String created_at;
    String image;
    String comment;

    public CommentModel(String name, String created_at, String image, String commnet) {
        this.name = name;
        this.created_at = created_at;
        this.image = image;
        this.comment = commnet;
    }

    public CommentModel() {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommnet() {
        return comment;
    }

    public void setCommnet(String commnet) {
        this.comment = commnet;
    }
}
