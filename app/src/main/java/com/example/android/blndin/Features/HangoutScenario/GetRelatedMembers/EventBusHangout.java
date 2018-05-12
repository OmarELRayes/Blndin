package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers;

import com.example.android.blndin.Models.ActivityModel;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/12/2018.
 */

public class EventBusHangout {
    ArrayList<ActivityModel> activityModels;

    public EventBusHangout(ArrayList<ActivityModel> activityModels) {
        this.activityModels = activityModels;
    }

    public ArrayList<ActivityModel> getActivityModels() {
        return activityModels;
    }

    public void setActivityModels(ArrayList<ActivityModel> activityModels) {
        this.activityModels = activityModels;
    }
}
