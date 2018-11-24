package hackjunction2018.c2c.hackthesauna.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import hackjunction2018.c2c.hackthesauna.ContentManager;
import hackjunction2018.c2c.hackthesauna.R;
import in.unicodelabs.kdgaugeview.KdGaugeView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class FullscreenActivity extends AppCompatActivity implements ContentManager.DataListener, View.OnClickListener {
    private LinearLayout root;
    private ContentManager contentManager;

    KdGaugeView gaugeTemperatureSauna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        root = findViewById(R.id.root);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getSupportActionBar().hide();

        gaugeTemperatureSauna = findViewById(R.id.speedMeter);

        contentManager = ContentManager.getInstance(this, this);

        gaugeTemperatureSauna.setSpeed(80);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()) {
            case R.id.get_in_button:
                Intent scanCardIntent = new Intent(this, ScanCardActivity.class);
                args.putString("type", "in");
                scanCardIntent.putExtras(args);
                startActivity(scanCardIntent);
                break;
            case R.id.get_out_button:
                Intent scanCardIntent2 = new Intent(this, ScanCardActivity.class);
                args.putString("type", "out");
                scanCardIntent2.putExtras(args);
                startActivity(scanCardIntent2);
                break;
        }
    }

    @Override
    public void notifyRetrieved() {
        System.out.println("BENCH1: " + this.contentManager.getmBench1());
        System.out.println("BENCH2: " + this.contentManager.getmBench2());
        System.out.println("BENCH3: " + this.contentManager.getmBench3());
        System.out.println("CEILING 1: " + this.contentManager.getmCeiling1());
        System.out.println("CEILING 2: " + this.contentManager.getmCeiling1());
        System.out.println("FLOOR 1: " + this.contentManager.getmFloor1());
        System.out.println("STOVE 1: " + this.contentManager.getmStove1());
        System.out.println("STOVE 2: " + this.contentManager.getmStove2());
        System.out.println("DOORWAY 1: " + this.contentManager.getmDoorway1());
        System.out.println("OUTDOOR 1: " + this.contentManager.getmOutdoor1());
    }

    @Override
    public void notifyNotRetrieved() {
        Snackbar snackbar = Snackbar
                .make(root, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
