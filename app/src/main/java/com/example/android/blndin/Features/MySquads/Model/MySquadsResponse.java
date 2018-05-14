package com.example.android.blndin.Features.MySquads.Model;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class MySquadsResponse {
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
        ArrayList<MySquadsModel> squads;

        public ArrayList<MySquadsModel> getSquads() {
            return squads;
        }

        public void setSquads(ArrayList<MySquadsModel> squads) {
            this.squads = squads;
        }
    }
}
