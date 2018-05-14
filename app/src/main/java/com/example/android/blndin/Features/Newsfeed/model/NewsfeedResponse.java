package com.example.android.blndin.Features.Newsfeed.model;

import com.example.android.blndin.Models.NewsfeedModel;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/14/2018.
 */

public class NewsfeedResponse {
    String status;
    Payload payload;
    Paginator paginator;

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

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public class Payload{
        ArrayList<NewsfeedModel> data;

        public ArrayList<NewsfeedModel> getData() {
            return data;
        }

        public void setData(ArrayList<NewsfeedModel> data) {
            this.data = data;
        }
    }
    public class Paginator{
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
}
