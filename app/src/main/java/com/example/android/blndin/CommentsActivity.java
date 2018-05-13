package com.example.android.blndin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.blndin.Features.HangoutProfile.HangoutCommentsAdapter;
import com.example.android.blndin.Features.HangoutProfile.HangoutPostCommentsPresenterImp;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutPostCommentsPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutPostCommentsView;
import com.example.android.blndin.Models.CommentModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsActivity extends AppCompatActivity implements HangoutPostCommentsView{
    @BindView(R.id.recycler_comments)RecyclerView recyclerView;
    @BindView(R.id.send_comment)ImageView send_comment;
    @BindView(R.id.send_comment_et)EditText send_comment_et;
    @BindView(R.id.swipe_refresh_layout)SwipeRefreshLayout swipe_refresh_layout;

    RecyclerView.Adapter adapter;
    ArrayList<CommentModel> models;
    HangoutPostCommentsPresenterImp presenter;
    RecyclerView.LayoutManager layoutManager;
    String post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        post_id=getIntent().getStringExtra("post_id");
        Log.d("Post_id",post_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        models=new ArrayList<>();
        adapter=new HangoutCommentsAdapter(getApplicationContext(),models);
        recyclerView.setAdapter(adapter);
        presenter=new HangoutPostCommentsPresenterImp(getApplicationContext(),adapter,models,this);
        presenter.getCommentsByPage("$2y$10$aOxpZjszXYGAD/pYvGhbe.hGwzJfwTdYCFOkkHcVYRqErVAsSUgMq",post_id);
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!send_comment_et.getText().toString().trim().isEmpty())
                presenter.addComment("$2y$10$aOxpZjszXYGAD/pYvGhbe.hGwzJfwTdYCFOkkHcVYRqErVAsSUgMq",post_id,send_comment_et.getText().toString());
                else Toast.makeText(getApplicationContext(),"Write comment",Toast.LENGTH_SHORT).show();
            }
        });
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getCommentsByPage("$2y$10$aOxpZjszXYGAD/pYvGhbe.hGwzJfwTdYCFOkkHcVYRqErVAsSUgMq",post_id);
            }
        });
    }

    @Override
    public void successfulResponseComments(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponseComments(String message) {
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFocus(int size) {
        recyclerView.scrollToPosition(size-1);
    }

    @Override
    public void stopRefreshing() {
        swipe_refresh_layout.setRefreshing(false);
    }
}
