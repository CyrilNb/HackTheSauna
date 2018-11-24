package hackjunction2018.c2c.hackthesauna.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hackjunction2018.c2c.hackthesauna.R;

public class ScanCardActivity extends AppCompatActivity {

    String type;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_card);

        Bundle b = getIntent().getExtras();
        if(b != null)
            type = b.getString("type");

        layout = findViewById(R.id.scanCardLayout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardIsValid();
            }
        });

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void cardIsValid(){
        if(type.equals("in")){
            Intent personalIntent = new Intent(this, PersonalActivity.class);
            startActivity(personalIntent);
        }else{
            Intent recapIntent = new Intent(this, RecapActivity.class);
            startActivity(recapIntent);
        }
    }

}
