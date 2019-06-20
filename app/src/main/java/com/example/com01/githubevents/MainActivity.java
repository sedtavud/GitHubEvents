package com.example.com01.githubevents;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.com01.githubevents.Api.ApiService;
import com.example.com01.githubevents.Api.Apis;
import com.example.com01.githubevents.Model.EventsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements EventsAdapter.OnItemClickListener {

    ApiService apiService;
    private RecyclerView recyclerView;
    private EventsAdapter adapter;
    private List<EventsResponse> resData;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_LOGIN = "display_login";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_IMAGE = "avatar_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FetchNewData();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FetchNewData();
            }
        });

    }
    public void initView(){
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh_items);
        recyclerView = findViewById(R.id.recycler_view);
    }
    public void FetchNewData(){
        apiService = new Apis(getApplicationContext()).apiService();
        Call<List<EventsResponse>> responseCall = apiService.fetchData();
        responseCall.enqueue(new Callback<List<EventsResponse>>() {
            @Override
            public void onResponse(Call<List<EventsResponse>> call, Response<List<EventsResponse>> response) {
//                Toast.makeText(getApplicationContext(),"OK Work"+response.code(),Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    resData = response.body();

                    adapter = new EventsAdapter(resData);
                    adapter.setOnItemClickListener(MainActivity.this);
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.e("Main","not connect !!");
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<EventsResponse>> call, Throwable t) {
                Log.e("Main","Error : " + t.getMessage());
                Toast.makeText(getApplicationContext(),"can't connect to server!",Toast.LENGTH_LONG).show();
            }


        });
    }

    @Override
    public void onItemClick(int position) {
        EventsResponse data = resData.get(position);
        Intent intent = new Intent(this,EventDetail.class);
//        Toast.makeText(getApplicationContext(),"ID : "+data.getActor().getId(),Toast.LENGTH_LONG).show();
        intent.putExtra(EXTRA_ID,String.valueOf(data.getActor().getId()));
        intent.putExtra(EXTRA_LOGIN,data.getActor().getDisplay_login());
        intent.putExtra(EXTRA_URL,data.getActor().getUrl());
        intent.putExtra(EXTRA_IMAGE,data.getActor().getAvatar_url());
        startActivity(intent);
    }
}
