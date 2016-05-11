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

    double peakbefore=0;
    double valleybefore=0;
    double totalbefore=0;
    double genbefore=0;

    public Optimize youhua;
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
        for (int i=0;i<8;i++) {
            peakbefore+= Cal.sum(MainActivity.appliances[i].getPrice(),6,21);
            valleybefore+=Cal.sum(MainActivity.appliances[i].getPrice(),0,5);
            valleybefore+=Cal.sum(MainActivity.appliances[i].getPrice(),22,23);
            totalbefore+=MainActivity.appliances[i].getSumPrice();
        }

        genbefore=(MainActivity.Fengji.getSumPower()+MainActivity.Guangfu.getSumPower())*Optimize.SUBSIDY/1000;
        peak_before.setText("-"+decimalFormat.format(peakbefore));
        valley_before.setText("-"+decimalFormat.format( valleybefore));
        gen_before.setText(decimalFormat.format(genbefore));
        double total=genbefore-totalbefore;
        total_before.setText(decimalFormat.format(total));

        youhua=new Optimize();
        youhua.optim();
       youhua.calCharge();
       peak_after.setText("-" + decimalFormat.format(youhua.peakFee));
       valley_after.setText("-"+decimalFormat.format(youhua.valleyFee));
       gen_after.setText(decimalFormat.format(youhua.sellFee+genbefore));
       total=youhua.sellFee+genbefore-Cal.sum24(youhua.Fee);
       total_after.setText(decimalFormat.format(total));

    }



}
