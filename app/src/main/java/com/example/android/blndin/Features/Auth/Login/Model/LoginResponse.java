package com.example.android.blndin.Features.Auth.Login.Model;

import com.example.android.blndin.Models.UserModel;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class LoginResponse {

    String status;
    Payload payload;
    String message;

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

    public String getToken() {
        return getPayload().getToken();
    }

    public class Payload {
        String message;
        String token;
        UserModel users;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserModel getUsers() {
            return users;
        }

        public void setUsers(UserModel users) {
            this.users = users;
        }
    }
}
