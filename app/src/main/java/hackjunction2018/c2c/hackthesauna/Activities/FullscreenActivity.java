package hackjunction2018.c2c.hackthesauna.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import hackjunction2018.c2c.hackthesauna.ContentManager;
import hackjunction2018.c2c.hackthesauna.Model.ComplexSensor;
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
    private TextView moistureTxtView, lowestTempTxtView, highestTempTxtView,
            timeTxtView, dateTwtView, amtxtView;
    private ContentManager contentManager;
    Dialog dialog;
    private FloatingActionButton fabMap;

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
        moistureTxtView = findViewById(R.id.moisture_text);
        highestTempTxtView = findViewById(R.id.high_temp_text);
        lowestTempTxtView = findViewById(R.id.low_temp_text);
        dateTwtView = findViewById(R.id.date_text);
        timeTxtView = findViewById(R.id.time_text);
        amtxtView = findViewById(R.id.am_text);
        getInButton = findViewById(R.id.get_in_button);
        getOutButton = findViewById(R.id.get_out_button);
        fabMap = findViewById(R.id.fabMap);
        fabMap.setOnClickListener(this);
        getInButton.setOnClickListener(this);
        getOutButton.setOnClickListener(this);

    }


    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh the stuff here
        initialize();

    }

    @Override
    public void onRestart() {
        super.onRestart();
        initialize();
    }


    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()) {
            case R.id.get_in_button:
                Intent scanCardIntent = new Intent(this, ScanCardActivity.class);
                args.putString("type", "in");
                scanCardIntent.putExtras(args);
                startActivityForResult(scanCardIntent, 1);
                //finish();
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                break;
            case R.id.get_out_button:
                Intent scanCardIntent2 = new Intent(this, ScanCardActivity.class);
                args.putString("type", "ou");
                scanCardIntent2.putExtras(args);
                startActivityForResult(scanCardIntent2, 1);
                //finish();
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                break;
            case R.id.fabMap:
                showDialog();
                break;
        }
    }

    /**
     * Callbacks method to be notified from the ContentManager when the data is retrieved
     */
    @Override
    public void notifyRetrieved() {
        updateDateTime();
        double sumTemperature = 0;
        double sumHumidity = 0;
        double sumCarbon = 0;
        int sumEnthalpy = 0;
        int countHumiditySensor = 0;
        int countEthalpySensor = 0;
        int countCarbonSensor = 0;
        for (SimpleSensor simpleSensor : this.contentManager.getmSimpleSensorList()) {
            sumTemperature += simpleSensor.getmTemperature();
            if (simpleSensor.getmCarbon() != -1) {
                sumCarbon += simpleSensor.getmCarbon();
                countCarbonSensor++;
            }
            System.out.println(simpleSensor.getmTemperature());
            if (simpleSensor instanceof ComplexSensor) {
                sumEnthalpy += ((ComplexSensor) simpleSensor).getmEnthalpy();
                sumHumidity += ((ComplexSensor) simpleSensor).getmRelativeHumidity();
                ++countHumiditySensor;
                ++countEthalpySensor;
            }
        }

        this.contentManager.setAverageTemperature((int) (sumTemperature / (this.contentManager.getmSimpleSensorList().size())));
        this.contentManager.setAverageHumidity((sumHumidity / countHumiditySensor));
        this.contentManager.setAverageCarbonDioxideEmission((sumCarbon / countCarbonSensor));
        this.contentManager.setAverageEnthalpy((sumEnthalpy / countEthalpySensor));

        System.out.println("C: " + this.contentManager.getAverageCarbonDioxideEmission());
        System.out.println("E: " + this.contentManager.getAverageEnthalpy());
        //System.out.println(this.contentManager.getAverageHumidity());
        //System.out.println(this.contentManager.getAverageTemperature());

        try {
            SimpleSensor lowestTemperatureSensor = this.contentManager.getmSimpleSensorList()
                    .stream()
                    .filter(sensor -> sensor.getName().startsWith("Bench"))
                    .min(Comparator.comparing(SimpleSensor::getmTemperature))
                    .orElseThrow(NoSuchElementException::new);
            SimpleSensor highestTemperatureSensor = this.contentManager.getmSimpleSensorList()
                    .stream()
                    .filter(sensor -> sensor.getName().startsWith("Bench"))
                    .max(Comparator.comparing(SimpleSensor::getmTemperature))
                    .orElseThrow(NoSuchElementException::new);
            this.contentManager.setLowestTemperatureSensor(lowestTemperatureSensor);
            this.contentManager.setHighestTemperatureSensor(highestTemperatureSensor);
            //System.out.println(this.contentManager.getLowestTemperatureSensor());
            //System.out.println(this.contentManager.getHighestTemperatureSensor());

            //UPDATE UI
            gaugeTemperatureSauna.setSpeed(this.contentManager.getAverageTemperature());
            //gaugeTemperatureSauna.setSpeed(120);
            DecimalFormat percentageFormat = new DecimalFormat("00.0");
            String roundedAverageHumidity = percentageFormat.format(this.contentManager.getAverageHumidity());
            moistureTxtView.setText(roundedAverageHumidity + " %");
            lowestTempTxtView.setText(this.contentManager.getLowestTemperatureSensor().getName() + " | " + Double.toString(this.contentManager.getLowestTemperatureSensor().getmTemperature()) + "°C");
            highestTempTxtView.setText(this.contentManager.getHighestTemperatureSensor().getName() + " | " + Double.toString(this.contentManager.getHighestTemperatureSensor().getmTemperature()) + "°C");


        } catch (Throwable throwable) {
            System.out.println(throwable);
            throwable.printStackTrace();
        }

    }

    /**
     * Callbacks method to be notified from the ContentManager when the data couldn't be retrieved.
     */
    @Override
    public void notifyNotRetrieved() {
        Snackbar snackbar = Snackbar.make(root, "Sorry, a network error occured", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    /**
     * Method to update and display current time
     */
    private void updateDateTime() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("EEE, MMM d");
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String dateString = timeFormat.format(currentDate);
        dateTwtView.setText(dateString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String timeString = dateFormat.format(currentDate);
        timeTxtView.setText(timeString);
        SimpleDateFormat amFormat = new SimpleDateFormat("a");
        amFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String amString = amFormat.format(currentDate);
        amtxtView.setText(amString);
    }

    /**
     * Methods which initializes the UI with data
     */
    private void initialize() {
        //INIT DATA
        updateDateTime();
        contentManager = ContentManager.getInstance(this, this);
        contentManager.fetchAllData();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                contentManager.fetchAllData();
                handler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // launch next activity depending of the result
                /*String result=data.getStringExtra("type");
                if (result.equals("in")) {
                    Intent personalIntent = new Intent(this, PersonalActivity.class);
                    startActivityForResult(personalIntent, 1);
                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                } else {
                    Intent recapIntent = new Intent(this, RecapActivity.class);
                    startActivityForResult(recapIntent, 2);
                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                }*/
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // do nothing, it's from a button back pressed.
            }
        }
    }


    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        // set the custom dialog components - text, image and button
        ImageButton close = dialog.findViewById(R.id.btnClose);

        // Close Button
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Set the dialog to not focusable.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        // Show the dialog with NavBar hidden.
        dialog.show();

        // Set the dialog to focusable again.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

    }
}
