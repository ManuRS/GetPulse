package esp.uam.eps.gnb.getpulse;

import android.Manifest;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("LAST_MEASURE", "0");

        if (number!="0") {
            TextView tv = (TextView) findViewById(R.id.number);
            tv.setText(number);


            RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
            tv = (TextView) findViewById(R.id.text);
            if (Double.parseDouble(number) > 90) {
                //Cosa mala
                rb.setRating(0);
                tv.setText("Your heart rate is to high");
            } else {
                //Cosa buena
                rb.setRating(1);
                tv.setText("Your heart rate is correct");
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        final Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Measure.class);
                startActivity(intent);
            }
        });
    }
}
