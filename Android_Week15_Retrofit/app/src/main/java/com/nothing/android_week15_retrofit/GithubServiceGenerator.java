package com.nothing.android_week15_retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by THM on 4/25/2017.
 */
public class GithubServiceGenerator {
    private final static String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());
    private static HttpLoggingInterceptor httpLoggingInterceptor = new
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor);
    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> serviceClass){
        retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }
}
