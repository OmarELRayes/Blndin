package com.example.android.blndin.Features.HangoutScenario.Hangout.Model;



import com.example.android.blndin.Models.UserModel;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/12/2018.
 */

public class RelatedMembersResponse {

    String status;
    Payload payload;
    String message;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

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
    public class Payload{
        ArrayList<UserModel> users;
        public ArrayList<UserModel> getUsers() {
            return users;
        }

        public void setUsers(ArrayList<UserModel> members) {
            this.users = members;
        }
    }
}
