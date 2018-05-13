package com.example.android.blndin.Models;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class ChatMessageModel {
    String id;
    String message;
    String username;
    String image;
    String owned;
    String created_at;

    public ChatMessageModel(String message) {
        this.message = message;
        this.owned = "1";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
