package com.example.android.blndin.Features.HangoutProfile.Model;

import com.example.android.blndin.Models.HangoutModel;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class HangoutProfileDetailsResponse {

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

    public HangoutModel getHangout() {
        return payload.getHangout();
    }

    private class Payload {
        HangoutModel hangout;

        public HangoutModel getHangout() {
            return hangout;
        }

        public void setHangout(HangoutModel hangout) {
            this.hangout = hangout;
        }
    }

}
