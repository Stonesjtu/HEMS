package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

import cn.edu.sjtu.ysy.hems.R;
import control.Calculate;

/**
 * Created by YSY on 2016/4/14.
 */
public class CompareActivity extends Activity {


    //在control或执行优化算法的程序中，在存入数据库之前，共享优化前后的state,假设

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison);
        setTitle(R.string.comparetitle);

        DecimalFormat decimalFormat=new DecimalFormat("0.00");//把小数位数设为2位

        TextView peak_before =(TextView)findViewById(R.id.peak_before);
        TextView peak_after =(TextView)findViewById(R.id.peak_after);
        TextView valley_before =(TextView)findViewById(R.id.valley_before);
        TextView valley_after =(TextView)findViewById(R.id.valley_after);
        TextView gen_before =(TextView)findViewById(R.id.gen_before);
        TextView gen_after =(TextView)findViewById(R.id.gen_after);
        TextView total_before =(TextView)findViewById(R.id.total_before);
        TextView total_after =(TextView)findViewById(R.id.total_after);

        Calculate Calresult=new Calculate();

        peak_before.setText("-"+decimalFormat.format(Calresult.getChargepeak()));
        valley_before.setText("-"+decimalFormat.format(Calresult.getChargevalley()));
        gen_before.setText(decimalFormat.format(Calresult.getChargegen()));
        double total=Calresult.getChargegen()-Calresult.getCharge();
        total_before.setText(decimalFormat.format(total));

       MainActivity.youhua.calCharge();
       peak_after.setText("-" + decimalFormat.format(MainActivity.youhua.peakFee));
       valley_after.setText("-"+decimalFormat.format(MainActivity.youhua.valleyFee));
       gen_after.setText(decimalFormat.format(MainActivity.youhua.sellFee+Calresult.getChargegen()));
       total=MainActivity.youhua.sellFee+Calresult.getChargegen()-sum24(MainActivity.youhua.Fee,0,23);
       total_after.setText(decimalFormat.format(total));

    }

    public double sum24(double[] price,int start,int end){
        double sumPrice=0;
        int lenth=price.length;
        if ((end-start+1)<=lenth) {
            for (int i = start; i <=end; i++) {
                sumPrice += price[i];
            }
        }
        return sumPrice;
    }



}
