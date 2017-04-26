package com.nothing.android_week15_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by THM on 4/26/2017.
 */
public interface WeatherService {
    @GET("forecast/{key}/{longitude},{latitude}")
    Call<WeatherModel> getWeather(@Path("key") String key, @Path("longitude") double longitude,
                                  @Path("latitude") double latitude);
}
