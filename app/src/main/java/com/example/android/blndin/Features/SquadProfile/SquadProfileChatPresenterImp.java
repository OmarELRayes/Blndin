package com.example.android.blndin.Features.SquadProfile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Adapters.ChatAdapter;
import com.example.android.blndin.Features.SquadProfile.Model.AddChatMessageResponse;
import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileChatResponse;
import com.example.android.blndin.Features.SquadProfile.Presenter.SquadProfileChatPresenter;
import com.example.android.blndin.Features.SquadProfile.View.SquadChatView;
import com.example.android.blndin.Models.ChatMessageModel;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blndin.Util.Constants.SUCCESS_RESPONSE;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class SquadProfileChatPresenterImp implements SquadProfileChatPresenter {

    Context context;
    SquadChatView squadChatView;
    ChatAdapter adapter;
    ArrayList<ChatMessageModel> messageModels;
    SquadProfileChatResponse.Paginator paginator;
    ArrayList<ChatMessageModel> temp;

    public SquadProfileChatPresenterImp(Context context, SquadChatView squadChatView, ChatAdapter adapter, ArrayList<ChatMessageModel> messageModels) {
        this.context = context;
        this.squadChatView = squadChatView;
        this.adapter = adapter;
        this.messageModels = messageModels;
    }

    @Override
    public void getMessagesByPage(String token, String squad_id) {
        if (paginator == null) {
            loadMessages(token, squad_id, "1");
        } else {
            if (checkPages(paginator))
                loadMessages(token, squad_id, String.valueOf(Integer.valueOf(paginator.getCurrent_page()) + 1));
        }
    }

    void loadMessages(String token, String hangout_id, final String page_id) {
        //Log.d("page_id",page_id);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SquadProfileChatResponse> call = apiInterface.getSquadChat(token, hangout_id, page_id);
        call.enqueue(new Callback<SquadProfileChatResponse>() {
            @Override
            public void onResponse(Call<SquadProfileChatResponse> call, Response<SquadProfileChatResponse> response) {
                if (response.body() != null) {
                    paginator = response.body().getPaginator();
                    if (response.body().getStatus().equals(SUCCESS_RESPONSE)) {
                        squadChatView.success("Done " + response.body().getStatus());
                        temp = response.body().getPayload().getData();
                        Collections.reverse(temp);
                        messageModels.addAll(0, temp);
                        adapter.notifyDataSetChanged();
                        if (page_id.equals("1"))
                            squadChatView.setFocus();
                        squadChatView.stopRefreshing();
                    } else
                        squadChatView.failure("Something error " + response.body().getStatus());
                } else squadChatView.failure("Server Error");
            }

            @Override
            public void onFailure(Call<SquadProfileChatResponse> call, Throwable t) {
                squadChatView.failure("Server Error");
            }
        });
    }

    @Override
    public boolean checkPages(SquadProfileChatResponse.Paginator paginator) {
        if (!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages()))) {
            Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context, "no more posts", Toast.LENGTH_SHORT).show();
        squadChatView.stopRefreshing();
        return false;
    }

    @Override
    public void addChatMessage(String token, String squad_id, String message) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddChatMessageResponse> call = apiInterface.addSquadChatMessage(token, squad_id, message);
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
