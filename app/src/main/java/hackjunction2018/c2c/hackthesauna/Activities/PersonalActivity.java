package hackjunction2018.c2c.hackthesauna.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetroopers.betterpickers.hmspicker.HmsPickerBuilder;
import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;

import java.util.ArrayList;
import java.util.List;

import hackjunction2018.c2c.hackthesauna.Model.Session;
import hackjunction2018.c2c.hackthesauna.R;
import hackjunction2018.c2c.hackthesauna.SessionAdapter;

public class PersonalActivity extends AppCompatActivity implements HmsPickerDialogFragment.HmsPickerDialogHandlerV2 {

    private LinearLayout linearLayoutRecapTimer;
    private Button mButtonTimer, mButtonSave;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txtViewHours, txtViewMinutes, txtViewSeconds;
    private int hours, minutes, seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        linearLayoutRecapTimer = findViewById(R.id.linearTimerRecap);
        mButtonTimer = findViewById(R.id.button_select_timer);
        mButtonSave = findViewById(R.id.button_save);
        txtViewHours = findViewById(R.id.txtViewHours);
        txtViewMinutes = findViewById(R.id.txtViewMinutes);
        txtViewSeconds = findViewById(R.id.txtViewSeconds);

        mButtonTimer.setOnClickListener(v -> {
            View decorView1 = getWindow().getDecorView();
            decorView1.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            HmsPickerBuilder hpb = new HmsPickerBuilder()
                    .setFragmentManager(getSupportFragmentManager())
                    .setStyleResId(R.style.BetterPickersDialogFragment);
            hpb.show();
            mButtonTimer.setVisibility(View.INVISIBLE);
        });

        mButtonSave.setOnClickListener(view -> {
            Intent mainIntent = new Intent(getApplicationContext(), FullscreenActivity.class);
            startActivity(mainIntent);
            finish();
        });

        mRecyclerView = findViewById(R.id.last_sessions_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        List<Session> lastSessions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int randomTemp = (int) (Math.random() * 100 + 1);
            int randomMin = (int) (Math.random() * 59 + 1);
            int randomCalories = (int) (Math.random() * 300 + 1);
            lastSessions.add(new Session(i + 1 + " November 2018", String.valueOf(randomTemp) + " °C", String.valueOf(randomMin) + " min", String.valueOf(randomCalories) + " cal"));
        }

        mAdapter = new SessionAdapter(lastSessions);
        mRecyclerView.setAdapter(mAdapter);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }

    @Override
    public void onDialogHmsSet(int reference, boolean isNegative, int hours, int minutes, int seconds) {
        linearLayoutRecapTimer.setVisibility(View.VISIBLE);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        txtViewSeconds.setText(Integer.toString(seconds));
        txtViewMinutes.setText(Integer.toString(minutes));
        txtViewHours.setText(Integer.toString(hours));

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

}
