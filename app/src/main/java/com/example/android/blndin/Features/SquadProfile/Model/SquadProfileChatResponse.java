package com.example.android.blndin.Features.SquadProfile.Model;

import com.example.android.blndin.Models.ChatMessageModel;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class SquadProfileChatResponse {
    String status;
    Paginator paginator;
    Payload payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public class Paginator {
        String pages;
        String total;
        String current_page;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

    }

    public class Payload {
        ArrayList<ChatMessageModel> data;

        public ArrayList<ChatMessageModel> getData() {
            return data;
        }

        public void setData(ArrayList<ChatMessageModel> data) {
            this.data = data;
        }
    }
}
