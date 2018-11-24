package hackjunction2018.c2c.hackthesauna;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
