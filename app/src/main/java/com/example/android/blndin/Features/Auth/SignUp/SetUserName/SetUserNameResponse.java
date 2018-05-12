package com.example.android.blndin.Features.Auth.SignUp.SetUserName;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class SetUserNameResponse {
    String status;
    Payload payload;
    String message;

    public SetUserNameResponse(String status, Payload payload, String message) {
        this.status = status;
        this.payload = payload;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private class Payload {
        String username;

        public Payload(String username) {

            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
