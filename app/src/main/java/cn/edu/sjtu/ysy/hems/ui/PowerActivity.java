package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import cn.edu.sjtu.ysy.hems.R;


public class PowerActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realpower);
        setTitle(R.string.comforttitle);

       // TextClock time=(TextClock)findViewById(R.id.textClock);
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int total=0;

        TextView[] rpower= new TextView[9];
        rpower[0]=(TextView)findViewById(R.id.rpwer_kongtiao);
        rpower[1]=(TextView)findViewById(R.id.rpwer_bingxiang);
        rpower[2]=(TextView)findViewById(R.id.rpwer_dianshi);
        rpower[3]=(TextView)findViewById(R.id.rpwer_reshuiqi);
        rpower[4]=(TextView)findViewById(R.id.rpwer_xiyiji);
        rpower[5]=(TextView)findViewById(R.id.rpwer_xiwanji);
        rpower[6]=(TextView)findViewById(R.id.rpwer_dianche);
        rpower[7]=(TextView)findViewById(R.id.rpwer_kongjing);
        rpower[8]=(TextView)findViewById(R.id.rpwer_total);
        for(int i=0;i<8;i++){

                rpower[i].setText("" + MainActivity.appliances[i].getPower()[hour]);
                total += MainActivity.appliances[i].getPower()[hour];
        }
        rpower[8].setText("" +total);



    }
}


/**
 * Created by YSY on 2016/5/3.
 */
