package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

import cn.edu.sjtu.ysy.hems.R;
import control.Calculate;
import control.Optimize;

/**
 * Created by YSY on 2016/4/14.
 */
public class CompareActivity extends Activity {

    double genbefore=0;

    public Optimize youhuahou;
    public Optimize youhuaqian;
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

        Calculate Cal=new Calculate();
        genbefore=(MainActivity.Fengji.getSumPower()+MainActivity.Guangfu.getSumPower())*Optimize.SUBSIDY/1000;

        youhuaqian=new Optimize();
        youhuaqian.initial();
        youhuaqian.calCharge();
        peak_before.setText("-"+decimalFormat.format(youhuaqian.peakFee));
        valley_before.setText("-"+decimalFormat.format(youhuaqian.valleyFee));
        gen_before.setText(decimalFormat.format(youhuaqian.sellFee+genbefore));
        double total=youhuaqian.sellFee+genbefore-Cal.sum24(youhuaqian.Fee);
        total_before.setText(decimalFormat.format(total));

        youhuahou=new Optimize();
        youhuahou.optim();
        youhuahou.calCharge();
       peak_after.setText("-" + decimalFormat.format(youhuahou.peakFee));
       valley_after.setText("-"+decimalFormat.format(youhuahou.valleyFee));
       gen_after.setText(decimalFormat.format(youhuahou.sellFee+genbefore));
       total=youhuahou.sellFee+genbefore-Cal.sum24(youhuahou.Fee);
       total_after.setText(decimalFormat.format(total));

    }



}
