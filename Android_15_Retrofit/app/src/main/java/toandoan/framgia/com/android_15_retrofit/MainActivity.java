package toandoan.framgia.com.android_15_retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // https://api.github.com/ users/list?sort

    private final String BASE_URL = "https://api.github.com/";
    private TextView mTextResult;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDialog = new ProgressDialog(this);
        findViewById(R.id.button_get_data).setOnClickListener(this);
        mTextResult = (TextView) findViewById(R.id.text_result);
    }

    @Override
    public void onClick(View view) {
        // TODO: 25/04/2017 getData by retrofit
        // show dialog

        mDialog.show();
        // 1 khoi tao retrofit

        // Khoi tao service
        GithubService service = ServiceGenerator.createService(GithubService.class);
        service.getGithub("asc").enqueue(new Callback<GithubModel>() {
            @Override
            public void onResponse(Call<GithubModel> call, Response<GithubModel> response) {
                // connect thafnh cong
                if (response != null) {
                    GithubModel model = response.body();
                    // update len giao dien
                    mTextResult.setText(model.getUrl());
                }
                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GithubModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }
}
