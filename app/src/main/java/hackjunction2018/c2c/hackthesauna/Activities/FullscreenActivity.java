package hackjunction2018.c2c.hackthesauna.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Comparator;
import java.util.NoSuchElementException;

import hackjunction2018.c2c.hackthesauna.ContentManager;
import hackjunction2018.c2c.hackthesauna.Model.HumiditySensor;
import hackjunction2018.c2c.hackthesauna.Model.SimpleSensor;
import hackjunction2018.c2c.hackthesauna.R;
import in.unicodelabs.kdgaugeview.KdGaugeView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class FullscreenActivity extends AppCompatActivity implements ContentManager.DataListener, View.OnClickListener {
    private LinearLayout root;
    private Button getInButton, getOutButton;
    private ContentManager contentManager;

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        root = findViewById(R.id.root);
        gaugeTemperatureSauna = findViewById(R.id.speedMeter);
        getInButton = findViewById(R.id.get_in_button);
        getOutButton = findViewById(R.id.get_out_button);
        getInButton.setOnClickListener(this);
        getOutButton.setOnClickListener(this);

        contentManager = ContentManager.getInstance(this, this);

        gaugeTemperatureSauna.setSpeed(80);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                contentManager.fetchAllData();
                handler.postDelayed(this, 3000);
            }
        }, 3000);
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
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
        double sumTemperature = 0;
        double sumHumdity = 0;
        int countHumditySensor = 0;
        for (SimpleSensor simpleSensor : this.contentManager.getmSimpleSensorList()) {
            sumTemperature += simpleSensor.getmTemperature();
            if (simpleSensor instanceof HumiditySensor) {
                sumHumdity += ((HumiditySensor) simpleSensor).getmRelativeHumidity();
                ++countHumditySensor;
            }
        }

        this.contentManager.setAverageTemperature((int) (sumTemperature / (this.contentManager.getmSimpleSensorList().size())));
        this.contentManager.setAverageHumidity((int) (sumHumdity / countHumditySensor));
        System.out.println(this.contentManager.getAverageHumidity());
        System.out.println(this.contentManager.getAverageTemperature());

        try {
            SimpleSensor lowestTemperatureSensor = this.contentManager.getmSimpleSensorList()
                    .stream()
                    .min(Comparator.comparing(SimpleSensor::getmTemperature))
                    .orElseThrow(NoSuchElementException::new);
            SimpleSensor highestTemperatureSensor = this.contentManager.getmSimpleSensorList()
                    .stream()
                    .max(Comparator.comparing(SimpleSensor::getmTemperature))
                    .orElseThrow(NoSuchElementException::new);
            this.contentManager.setLowestTemperatureSensor(lowestTemperatureSensor);
            this.contentManager.setHighestTemperatureSensor(highestTemperatureSensor);
            System.out.println(this.contentManager.getLowestTemperatureSensor());
            System.out.println(this.contentManager.getHighestTemperatureSensor());
        } catch (Throwable throwable) {
            System.out.println(throwable);
            throwable.printStackTrace();
        }

    }

    @Override
    public void notifyNotRetrieved() {
        Snackbar snackbar = Snackbar.make(root, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

<<<<<<< HEAD
    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
=======
    /*private void longPollingUpdate() {
        mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                contentManager.fetchAllData();
                                System.out.println("UPDATED");
                                System.out.println(contentManager.getHighestTemperatureSensor().getmTimeStamp());
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }*/
>>>>>>> 4ef6114499cace5209b5b5da7f351ec31d7d4dde
}
