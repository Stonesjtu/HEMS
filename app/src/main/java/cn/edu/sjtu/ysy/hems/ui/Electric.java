package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/11.
 */
public class Electric extends Activity {
    float[] peak = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
    float[] valley = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
    float peaktotal = 0;
    float valleytotal = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_eletric);
        setTitle(R.string.electrictitle);

        final TextView peakkwh=(TextView)findViewById(R.id.peakkwh);
        final TextView valleykwh=(TextView)findViewById(R.id.valleykwh);
        Spinner chooseapp=(Spinner)findViewById(R.id.spinner);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 16; i++) {
                peak[j] +=( MainActivity.appliances[j].getPower()[i]/1000.0);
            }
            for (int i = 16; i < 24; i++) {
                valley[j] += (MainActivity.appliances[j].getPower()[i]/1000.0);
            }
            peaktotal += peak[j];
            valleytotal+=valley[j];
        }
        chooseapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 8) {
                    peakkwh.setText("" + peak[position]);
                    valleykwh.setText("" + valley[position]);
                }
                else{
                        peakkwh.setText("" + peaktotal);
                        valleykwh.setText("" + valleytotal);
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Electric.this, "none you", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
