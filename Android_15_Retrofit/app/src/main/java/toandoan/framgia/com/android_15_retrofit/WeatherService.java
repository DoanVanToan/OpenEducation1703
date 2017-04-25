package toandoan.framgia.com.android_15_retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by framgia on 25/04/2017.
 */

public interface WeatherService {
    @GET("forecast/{key}/{location}")
    Call<ResponseBody> getWeather(@Path("key") String key,
            @Path("location") String location);
}
