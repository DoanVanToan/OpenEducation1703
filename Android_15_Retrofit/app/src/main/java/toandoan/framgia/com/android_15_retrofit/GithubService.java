package toandoan.framgia.com.android_15_retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by framgia on 25/04/2017.
 */

public interface GithubService {
    @GET("users/list")
    Call<GithubModel> getGithub(@Query("sort") String sortValue);
}
