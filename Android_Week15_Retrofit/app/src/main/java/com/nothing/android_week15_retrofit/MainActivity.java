package com.nothing.android_week15_retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // https://api.github.com/users/list?sort=desc
//    private final String BASE_URL = "https://api.github.com/";
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_get_data).setOnClickListener(this);
        mTextResult = (TextView) findViewById(R.id.text_result);
    }

    @Override
    public void onClick(View v) {
        // TODO: 4/25/2017  getData by retrofit
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
//        // Khoi tao retrofit
//        Retrofit retrofit;
//        Retrofit.Builder builder = new Retrofit.Builder();
//        builder.baseUrl(BASE_URL);
//        builder.addConverterFactory(GsonConverterFactory.create());
        // Khai bao log de lay ra gia tri cua request
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
//        OkHttpClient okHttpClient = okHttpClientBuilder.build();
//        retrofit = builder.client(okHttpClient).build();
        // Khoi tao service
//        GithubService service = retrofit.create(GithubService.class);
        GithubService service = GithubServiceGenerator.createService(GithubService.class);
        service.getGithub("desc").enqueue(new Callback<GithubModel>() {
            @Override
            public void onResponse(Call<GithubModel> call, Response<GithubModel> response) {
                if (response != null) {
                    GithubModel model = response.body();
                    mTextResult.setText(model.getUrl());
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<GithubModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
