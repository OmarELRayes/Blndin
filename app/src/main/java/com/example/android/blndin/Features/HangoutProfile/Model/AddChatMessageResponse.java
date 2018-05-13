package com.example.android.blndin.Features.HangoutProfile.Model;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class AddChatMessageResponse {

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

    public class Payload {
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
