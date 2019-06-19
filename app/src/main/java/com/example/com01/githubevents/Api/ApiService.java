package com.example.com01.githubevents.Api;

import com.example.com01.githubevents.Model.EventsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("User-Agent:sedtavud")
    @GET("events")
    Call<List<EventsResponse>> fetchData();


    @Headers("User-Agent:sedtavud")
    @GET("events")
    Call<String> testGetResult();
}
