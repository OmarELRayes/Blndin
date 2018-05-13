package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Adapters.ChatAdapter;
import com.example.android.blndin.Features.HangoutProfile.Model.AddChatMessageResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileChatResponse;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutProfileChatPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutChatView;
import com.example.android.blndin.Models.ChatMessageModel;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blndin.Util.Constants.SUCCESS_RESPONSE;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class HangoutProfileChatPresenterImp implements HangoutProfileChatPresenter {

    Context context;
    HangoutChatView hangoutChatView;
    ChatAdapter adapter;
    ArrayList<ChatMessageModel> messageModels;
    HangoutProfileChatResponse.Paginator paginator;
    int focusItem;

    public HangoutProfileChatPresenterImp(Context context, HangoutChatView hangoutChatView, ChatAdapter adapter, ArrayList<ChatMessageModel> messageModels) {
        this.context = context;
        this.hangoutChatView = hangoutChatView;
        this.adapter = adapter;
        this.messageModels = messageModels;
    }

    @Override
    public void getPostsByPage(String token, String hangout_id) {
        if (paginator == null) {
            loadMessages(token, hangout_id, "1");
        } else {
            if (checkPages(paginator))
                loadMessages(token, hangout_id, String.valueOf(Integer.valueOf(paginator.getCurrent_page()) + 1));
        }
    }

    void loadMessages(String token, String hangout_id, final String page_id) {
        //Log.d("page_id",page_id);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HangoutProfileChatResponse> call = apiInterface.getHangoutChat(token, hangout_id, page_id);
        call.enqueue(new Callback<HangoutProfileChatResponse>() {
            @Override
            public void onResponse(Call<HangoutProfileChatResponse> call, Response<HangoutProfileChatResponse> response) {
                if (response.body() != null) {
                    paginator = response.body().getPaginator();
                    if (response.body().getStatus().equals(SUCCESS_RESPONSE)) {
                        hangoutChatView.success("Done " + response.body().getStatus());
                        messageModels.addAll(0, response.body().getPayload().getData());
                        adapter.notifyDataSetChanged();
                        if (page_id.equals("1"))
                            hangoutChatView.setFocus();
                        hangoutChatView.stopRefreshing();
                    } else
                        hangoutChatView.failure("Something error " + response.body().getStatus());
                } else hangoutChatView.failure("Server Error");
            }

            @Override
            public void onFailure(Call<HangoutProfileChatResponse> call, Throwable t) {
                hangoutChatView.failure("Server Error");
            }
        });
    }

    @Override
    public boolean checkPages(HangoutProfileChatResponse.Paginator paginator) {
        if (!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages()))) {
            Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context, "no more posts", Toast.LENGTH_SHORT).show();
        hangoutChatView.stopRefreshing();
        return false;
    }

    @Override
    public void addChatMessage(String token, String hangout_id, String message) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddChatMessageResponse> call = apiInterface.addMessage(token, hangout_id, message);
        call.enqueue(new Callback<AddChatMessageResponse>() {
            @Override
            public void onResponse(Call<AddChatMessageResponse> call, Response<AddChatMessageResponse> response) {
                if (response.body().getStatus().equals(SUCCESS_RESPONSE)) {
                    Log.d("chat", "onResponse: " + response.body().getPayload().getMessage());
                } else
                    Log.d("chat", "onResponse: " + response.body().getStatus());
            }

            @Override
            public void onFailure(Call<AddChatMessageResponse> call, Throwable t) {
                Log.d("chat", "onResponse: " + t.getMessage());
            }
        });
    }
}
