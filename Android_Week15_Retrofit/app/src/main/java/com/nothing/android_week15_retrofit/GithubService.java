package com.nothing.android_week15_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by THM on 4/25/2017.
 */
public interface GithubService {
    @GET("users/list")
    Call<GithubModel> getGithub(@Query("sort") String sortValue);
}
