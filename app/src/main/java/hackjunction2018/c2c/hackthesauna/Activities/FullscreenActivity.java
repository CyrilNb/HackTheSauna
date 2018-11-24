package hackjunction2018.c2c.hackthesauna.Activities;

import android.annotation.SuppressLint;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.support.design.widget.Snackbar;
>>>>>>> 559cc36ae2bd0053f3ecd0c0e82cc4dd25531dd3
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import hackjunction2018.c2c.hackthesauna.R;
import in.unicodelabs.kdgaugeview.KdGaugeView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
<<<<<<< HEAD
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {
=======
public class FullscreenActivity extends AppCompatActivity implements ContentManager.DataListener {
    private FrameLayout root;
    private ContentManager contentManager;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar
>>>>>>> 559cc36ae2bd0053f3ecd0c0e82cc4dd25531dd3

    KdGaugeView gaugeTemperatureSauna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

<<<<<<< HEAD
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
=======
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        root = findViewById(R.id.root);
>>>>>>> 559cc36ae2bd0053f3ecd0c0e82cc4dd25531dd3

        getSupportActionBar().hide();


<<<<<<< HEAD
        gaugeTemperatureSauna = findViewById(R.id.speedMeter);
=======
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        contentManager = ContentManager.getInstance(this, this);

    }
>>>>>>> 559cc36ae2bd0053f3ecd0c0e82cc4dd25531dd3

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
