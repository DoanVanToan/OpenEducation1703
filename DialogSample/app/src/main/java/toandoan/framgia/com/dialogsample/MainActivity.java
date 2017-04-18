package toandoan.framgia.com.dialogsample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonExit = (Button) findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // show a dialog to confirm exit app
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 1 title
                .setTitle(R.string.title_exit_app)
                // 3 icon
                .setIcon(R.mipmap.ic_launcher_round)
                // 2. message
                .setMessage(R.string.message_exit_app)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }
}
