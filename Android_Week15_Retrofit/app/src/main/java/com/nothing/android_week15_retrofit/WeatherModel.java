package com.nothing.android_week15_retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by THM on 4/26/2017.
 */
public class WeatherModel {
    @SerializedName("latitude")
    private double mLatitude;
    @SerializedName("longitude")
    private double mLongitude;
    @SerializedName("timezone")
    private String mTimeZone;
    @SerializedName("currently")
    private Currently mCurrently;

    public WeatherModel(double latitude, double longitude, String timeZone,
                        Currently currently) {
        mLatitude = latitude;
        mLongitude = longitude;
        mTimeZone = timeZone;
        mCurrently = currently;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public Currently getCurrently() {
        return mCurrently;
    }

    public void setCurrently(Currently currently) {
        mCurrently = currently;
    }

    public class Currently {
        @SerializedName("temperature")
        private double mTemperature;
        @SerializedName("humidity")
        private double mHumidity;
        @SerializedName("windSpeed")
        private double mWindSpeed;

        public Currently(double temperature, double humidity, double windSpeed) {
            mTemperature = temperature;
            mHumidity = humidity;
            mWindSpeed = windSpeed;
        }

        public double getTemperature() {
            return mTemperature;
        }

        public void setTemperature(double temperature) {
            mTemperature = temperature;
        }

        public double getHumidity() {
            return mHumidity;
        }

        public void setHumidity(double humidity) {
            mHumidity = humidity;
        }

        public double getWindSpeed() {
            return mWindSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            mWindSpeed = windSpeed;
        }
    }
}
