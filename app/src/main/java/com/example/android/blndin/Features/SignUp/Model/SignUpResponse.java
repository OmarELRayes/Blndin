package com.example.android.blndin.Features.SignUp.Model;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class SignUpResponse {

    String status;
    Payload payload;

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

    private class Payload {
        String message;
        String token;

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
    }
}
