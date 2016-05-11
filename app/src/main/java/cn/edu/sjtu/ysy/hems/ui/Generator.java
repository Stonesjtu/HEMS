package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/5/4.
 */
public class Generator extends Activity {


    public LineChart lineChart;
    public ArrayList<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
    public LineData lineData=null;
    int hour;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.daily_eletric);
        setTitle(R.string.Generatortitle);
        setContentView(R.layout.monthly_eletric);

        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);

        TextView hour_of_day=(TextView)findViewById(R.id.hour_of_day_g);
        hour_of_day.setText("" + hour);


        Button btn_fj = (Button) findViewById(R.id.fenji);
        Button btn_gf = (Button) findViewById(R.id.guangfu);


        btn_fj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView maxpower = (TextView) findViewById(R.id.maxpower);
                maxpower.setText(""+MainActivity.Fengji.power);
                //画折线图
                lineChart = (LineChart) findViewById(R.id.Linechart_generator);
                LineData resultLineData = getLineData(hour,true);
                showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));
            }
        });


        btn_gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView maxpower = (TextView) findViewById(R.id.maxpower);
                maxpower.setText(""+MainActivity.Guangfu.power);
                //画折线图
                lineChart = (LineChart) findViewById(R.id.Linechart_generator);
                LineData resultLineData = getLineData(hour,false);
                showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));
            }
        });
    }

    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count,boolean flag){
        x.clear();
        y.clear();
        for(int i=0;i<24;i++){  //X轴显示的数据
            x.add(i+"");
            if(count==0)  y.add(new Entry(0,i));
        }
        for(int i=0;i<count;i++){//y轴的数据
            int result=0;
            if (flag==true){
                result=MainActivity.Fengji.getState()[i];
            }
            else {
                result =MainActivity.Guangfu.getState()[i];
            }
            y.add(new Entry((float)result,i));
        }


        LineDataSet lineDataSet=new LineDataSet(y,"折线图");//y轴数据集合
        lineDataSet.setLineWidth(3f);//线宽
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.RED);//现实颜色
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setHighLightColor(Color.WHITE);//高度线的颜色
        lineDataSets.clear();
        lineDataSets.add(lineDataSet);
        lineData=new LineData(x,lineDataSets);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showChart(com.github.mikephil.charting.charts.LineChart lineChart,LineData lineData,int color){
        lineChart.setDrawBorders(false);//是否添加边框
        lineChart.setDescription("每小时发电功率");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.YELLOW);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend=lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.WHITE);//设置颜色
        lineChart.animateX(2000);//X轴的动画
    }
}
