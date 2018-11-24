package hackjunction2018.c2c.hackthesauna.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.codetroopers.betterpickers.hmspicker.HmsPickerBuilder;

import hackjunction2018.c2c.hackthesauna.R;

public class PersonalActivity extends AppCompatActivity {

    Button mButtonTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mButtonTimer = findViewById(R.id.button_select_timer);

        mButtonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmsPickerBuilder hpb = new HmsPickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                hpb.show();
            }
        });
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }



}