package com.framgia.toandoan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
    QuestionFragment.OnSendResult {
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_add_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_fragment:
                // Khoi tao fragment transaction thong qua fragment manager
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                // Khoi tao fragment add vao framelayout
//                QuestionFragment fragment = new QuestionFragment();
//                // add fragment vao framelayout
//                transaction.replace(R.id.frame_content, fragment);
//                // commit transaction
//                transaction.commit();
                mCount++;
                QuestionFragment fragment = QuestionFragment.newInstance(mCount);
                getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_content, fragment)
                    .addToBackStack(null)
                    .commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // getBackStackEntryCount trả về số luượng fragment trong stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            mCount--;
        } else super.onBackPressed();
    }

    @Override
    public void sendResult(int result) {
        Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
    }
}
