package com.example.android.blndin.Features.HangoutScenario.Hangout.Model;

/**
 * Created by Luffy on 5/12/2018.
 */

public class CheckHangoutResponse {
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

    public class Payload{
        String message;
        String status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
