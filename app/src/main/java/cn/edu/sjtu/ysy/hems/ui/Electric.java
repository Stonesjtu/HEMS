package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

import cn.edu.sjtu.ysy.hems.R;
import control.Calculate;

/**
 * Created by YSY on 2016/4/11.
 */
public class Electric extends Activity {
    float[] peak = new float[]{0, 0, 0, 0, 0, 0, 0,0};
    float[] valley = new float[]{0, 0, 0, 0, 0, 0, 0,0};
    float peaktotal = 0;
    float valleytotal = 0;
    int hour;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_eletric);
        setTitle(R.string.electrictitle);

        final TextView peakkwh=(TextView)findViewById(R.id.peakkwh);
        final TextView valleykwh=(TextView)findViewById(R.id.valleykwh);
        Spinner chooseapp=(Spinner)findViewById(R.id.spinner);
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);
        TextView hour_of_day=(TextView)findViewById(R.id.hour_of_day);
        hour_of_day.setText(""+hour);

        for (int j=0;j<8;j++){
            if (hour<6)  {
                valley[j]= Calculate.sum(MainActivity.appliances[j].getPower(),0,hour);
            }
            else if (hour <22)  {
                valley[j]= Calculate.sum(MainActivity.appliances[j].getPower(),0,5);
                peak[j]=Calculate.sum(MainActivity.appliances[j].getPower(),6,hour);
            }
            else {
                valley[j]= Calculate.sum(MainActivity.appliances[j].getPower(),0,5)+Calculate.sum(MainActivity.appliances[j].getPower(),22,hour);
                peak[j]=Calculate.sum(MainActivity.appliances[j].getPower(),6,21);
            }
            peaktotal += peak[j];
            valleytotal+=valley[j];
        }



        chooseapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");//把小数位数设为2位

                if (position != 8) {
                    peakkwh.setText(decimalFormat.format(peak[position]/1000));
                    valleykwh.setText(decimalFormat.format(valley[position]/1000));
                }
                else{
                        peakkwh.setText(decimalFormat.format(peaktotal/1000));
                        valleykwh.setText(decimalFormat.format(valleytotal/1000));
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Electric.this, "none you", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
