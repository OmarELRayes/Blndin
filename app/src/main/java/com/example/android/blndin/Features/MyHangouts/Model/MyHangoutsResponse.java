package com.example.android.blndin.Features.MyHangouts.Model;

import com.example.android.blndin.Models.MyHangoutModel;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/14/2018.
 */

public class MyHangoutsResponse {
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
        ArrayList<MyHangoutModel> hangouts;

        public ArrayList<MyHangoutModel> getHangouts() {
            return hangouts;
        }

        public void setHangouts(ArrayList<MyHangoutModel> hangouts) {
            this.hangouts = hangouts;
        }
    }
}
