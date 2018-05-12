package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model;

/**
 * Created by Luffy on 5/12/2018.
 */

public class CreateHangoutResponse {
    String status;
    String message;
    Payload payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public class Payload{
        String message;
        String hangout_id;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getHangout_id() {
            return hangout_id;
        }

        public void setHangout_id(String hangout_id) {
            this.hangout_id = hangout_id;
        }
    }
}
