package hackjunction2018.c2c.hackthesauna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import in.unicodelabs.kdgaugeview.KdGaugeView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {

    KdGaugeView gaugeTemperatureSauna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getSupportActionBar().hide();


        gaugeTemperatureSauna = findViewById(R.id.speedMeter);

        gaugeTemperatureSauna.setSpeed(80);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch(view.getId()){
            case R.id.get_in_button:
                Intent scanCardIntent = new Intent(this, ScanCardActivity.class);
                args.putString("type","in");
                scanCardIntent.putExtras(args);
                startActivity(scanCardIntent);
                break;
            case R.id.get_out_button:
                Intent scanCardIntent2 = new Intent(this, ScanCardActivity.class);
                args.putString("type","out");
                scanCardIntent2.putExtras(args);
                startActivity(scanCardIntent2);
                break;
        }
    }
}
