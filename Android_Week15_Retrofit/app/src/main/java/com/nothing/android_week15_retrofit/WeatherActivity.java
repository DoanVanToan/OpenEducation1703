package com.nothing.android_week15_retrofit;

import android.app.ProgressDialog;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    private final String USER_KEY = "6e0e5798288b83cc170bed9ac5265093";
    private final double LATITUDE = 37.8267;
    private final double LONGITUDE = -122.4233;
    private TextView mTextLatitude, mTextLongitude, mTextTemperature, mTextHumidity, mTextWindSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mTextHumidity = (TextView) findViewById(R.id.text_humidity);
        mTextLongitude = (TextView) findViewById(R.id.text_longitude);
        mTextTemperature = (TextView) findViewById(R.id.text_temperature);
        mTextLatitude = (TextView) findViewById(R.id.text_latitude);
        mTextWindSpeed = (TextView) findViewById(R.id.text_wind_speed);
        findViewById(R.id.button_get_weather_data).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
        WeatherService service = WeatherServiceGenerator.createService(WeatherService.class);
        service.getWeather(USER_KEY, LATITUDE, LONGITUDE).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response != null) {
                    WeatherModel model = response.body();
                    mTextLatitude.setText(String.valueOf(model.getLatitude()));
                    mTextLongitude.setText(String.valueOf(model.getLongitude()));
                    mTextWindSpeed.setText(String.valueOf(model.getCurrently().getWindSpeed()));
                    mTextTemperature.setText(String.valueOf(model.getCurrently().getTemperature()));
                    mTextHumidity.setText(String.valueOf(model.getCurrently().getHumidity()));
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(WeatherActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
