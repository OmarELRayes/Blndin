package com.example.android.blndin.Models;

/**
 * Created by Luffy on 5/14/2018.
 */

public class InviteSquadModel {
    String id;
    String admin_uuid;
    String admin_name;
    String admin_image;
    String squad_title;
    String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin_uuid() {
        return admin_uuid;
    }

    public void setAdmin_uuid(String admin_uuid) {
        this.admin_uuid = admin_uuid;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_image() {
        return admin_image;
    }

    public void setAdmin_image(String admin_image) {
        this.admin_image = admin_image;
    }

    public String getSquad_title() {
        return squad_title;
    }

    public void setSquad_title(String squad_title) {
        this.squad_title = squad_title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
