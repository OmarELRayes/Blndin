package com.example.android.blndin.Features.HangoutScenario.Hangout.Model;

import com.example.android.blndin.Models.ActivityModel;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/12/2018.
 */

public class ActivitiesResponse {
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
        ArrayList<ActivityModel> activities;

        public ArrayList<ActivityModel> getActivities() {
            return activities;
        }

        public void setActivities(ArrayList<ActivityModel> activities) {
            this.activities = activities;
        }
    }
}
