package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/8.
 */
public class KongtiaoActivity extends Activity{

    public static final String[] aname={"空 调","冰 箱","电 视","热水器","洗衣机","洗碗机","电动汽车","空气净化器"};
    public static final String[] apower={"2.5K","300","150","1.5K","600","1K","3K","100"};
    public static final String[] aduraion={"不定","全天","不定","不定","1","1","6—10","不定"};


    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kongtiao);
        barChart= (BarChart) findViewById(R.id.barchart_kongtiao);
        initEntriesData();
        initLableData();
        show();

        setTitle(R.string.kongtiaotitle);
        Intent intent=getIntent();

        int index=intent.getIntExtra("dianqi", -1);

        TextView mingcheng=(TextView)findViewById(R.id.mingcheng);
        mingcheng.setText(aname[index]);
        TextView ratedpower=(TextView)findViewById(R.id.rated_power);
        ratedpower.setText(apower[index]);
        TextView duration=(TextView)findViewById(R.id.duration);
        duration.setText(aduraion[index]);

//        ImageView dqmore=(ImageView)findViewById(R.id.dqmore);
//        switch (index){
//            case 0:
//                dqmore.setImageResource(R.drawable.acmore);
//                break;
//            case 3:
//                dqmore.setImageResource(R.drawable.whmore);
//                break;
//            case 5:
//                dqmore.setImageResource(R.drawable.bowlmore);
//                break;
//            default:dqmore.setImageResource(R.drawable.evmore);
//
//        }
    }



    public void initEntriesData() {
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
    }

    public void initLableData(){
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
    }
    public void show(){
        dataset= new BarDataSet(entries,"每小时平均用电功率");
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);
        BarData data=new BarData(labels,dataset);
        LimitLine line=new LimitLine(10f);
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
        barChart.animateY(1000);
        barChart.getAxisRight().setEnabled(false);
        barChart.setDescription("时刻");
    }

}
