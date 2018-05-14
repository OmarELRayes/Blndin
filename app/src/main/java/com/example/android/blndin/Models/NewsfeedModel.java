package com.example.android.blndin.Models;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/16/2018.
 */

public class NewsfeedModel {
     String id;
    String hangout_id;
    String hangout_address;
    String name;
    String content;
    String image;
    String comments;
    String likes;
    String isLiked;
    String user_image;
    ArrayList<UserModel> members;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHangout_id() {
        return hangout_id;
    }

    public void setHangout_id(String hangout_id) {
        this.hangout_id = hangout_id;
    }

    public String getHangout_address() {
        return hangout_address;
    }

    public void setHangout_address(String hangout_address) {
        this.hangout_address = hangout_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(String isLiked) {
        this.isLiked = isLiked;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public ArrayList<UserModel> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<UserModel> members) {
        this.members = members;
    }
}
