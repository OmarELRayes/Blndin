package com.example.android.blndin.Models;

/**
 * Created by Luffy on 4/19/2018.
 */

public class SquadChatModel {
    String text;
    Boolean owner;
    String name;
    public SquadChatModel(String text, Boolean owner) {
        this.text = text;
        this.owner = owner;
    }

    public SquadChatModel(String text, Boolean owner, String name) {
        this.text = text;
        this.owner = owner;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
