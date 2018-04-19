package com.example.android.blndin.Models;

/**
 * Created by Luffy on 4/19/2018.
 */

public class SquadChatModel {
    String text;
    Boolean owner;

    public SquadChatModel(String text, Boolean owner) {
        this.text = text;
        this.owner = owner;
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
}
