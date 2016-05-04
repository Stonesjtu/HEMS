package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.sjtu.ysy.hems.R;
import control.Calculate;

/**
 * Created by YSY on 2016/4/14.
 */
public class CompareActivity extends Activity {

    //在control或执行优化算法的程序中，在存入数据库之前，共享优化前后的state,假设
    public  int[] ktbefore;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison);
        setTitle(R.string.comparetitle);

        TextView peak_before =(TextView)findViewById(R.id.peak_before);
        TextView peak_after =(TextView)findViewById(R.id.peak_after);
        TextView valley_before =(TextView)findViewById(R.id.valley_before);
        TextView valley_after =(TextView)findViewById(R.id.valley_after);
        TextView gen_before =(TextView)findViewById(R.id.gen_before);
        TextView gen_after =(TextView)findViewById(R.id.gen_after);
        TextView total_before =(TextView)findViewById(R.id.total_before);
        TextView total_after =(TextView)findViewById(R.id.total_after);

        if (Setting.ynyouhua==true){
            peak_before.setText(""+ Calculate.getChargepeak());
            valley_before.setText(""+Calculate.getChargevalley());
            gen_before.setText(""+Calculate.getChargegen());
            total_before.setText(""+Calculate.getCharge());

        }
        else{
            peak_after.setText(""+ Calculate.getChargepeak());
            valley_after.setText(""+Calculate.getChargevalley());
            gen_after.setText(""+Calculate.getChargegen());
            total_after.setText(""+Calculate.getCharge());
        }


    }

}
