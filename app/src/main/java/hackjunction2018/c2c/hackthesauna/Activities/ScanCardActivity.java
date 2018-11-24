package hackjunction2018.c2c.hackthesauna.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import hackjunction2018.c2c.hackthesauna.R;

public class ScanCardActivity extends AppCompatActivity {

    private String type;
    private ImageView scanCardImgView;
    private FloatingActionButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_card);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Bundle b = getIntent().getExtras();
        if (b != null)
            type = b.getString("type");

        scanCardImgView = findViewById(R.id.scanCardImgView);
        mBackButton = findViewById(R.id.back_button);

        scanCardImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardIsValid();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               goBackToMainActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onBackPressed() {
        goBackToMainActivity();
    }

    private void cardIsValid() {
        Bundle args = new Bundle();
        if (type.equals("in")) {
            Intent personalIntent = new Intent(this, PersonalActivity.class);
            args.putString("type", "in");
            personalIntent.putExtras(args);
            startActivityForResult(personalIntent, 5);
            //finish();
            overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
        } else {
            Intent recapIntent = new Intent(this, RecapActivity.class);
            args.putString("type", "out");
            recapIntent.putExtras(args);
            startActivityForResult(recapIntent, 6);
            //finish();
            overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
        }
    }

    private void goBackToMainActivity(){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


}
