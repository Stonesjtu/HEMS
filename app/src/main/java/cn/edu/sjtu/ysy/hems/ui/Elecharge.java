package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cn.edu.sjtu.ysy.hems.R;
import control.Calculate;

/**
 * Created by YSY on 2016/4/11.
 */
public class Elecharge extends Activity {

    public LineChart lineChart;
    public ArrayList<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
    public LineData lineData=null;
    public int hour;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elecharge);
        setTitle(R.string.elechargetitle);
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);
        TextView hour_of_day=(TextView)findViewById(R.id.hour_of_day_c);
        hour_of_day.setText(""+hour);

        TextView totalmoney=(TextView)findViewById(R.id.totalmoney);
        double total=0;
        if(hour!=0) {
            for (int i=0;i<hour;i++) {
                for (int j= 0; j < 8; j++) {
                    total += MainActivity.appliances[j].getPrice()[i];
                }
            }
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        totalmoney.setText(decimalFormat.format(total));

        lineChart= (LineChart)findViewById(R.id.Linechart_charge);
        LineData resultLineData=getLineData(hour);
        showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));

    }

    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count){
        double[] result={0,0,0,0,0,0,0,0};
        double sumresult=0;
        for(int i=0;i<24;i++) {  //X轴显示的数据
            x.add(i + "");
            if (count==0) y.add(new Entry((float) sumresult, i));
        }
        if(count!=0) {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < 8; j++) {
                    result[j] = Calculate.sum(MainActivity.appliances[j].getPrice(), 0, i);
                }
                sumresult = Calculate.sum(result, 0, 7);
                y.add(new Entry((float) sumresult, i));
            }
        }



        LineDataSet lineDataSet=new LineDataSet(y,"折线图");//y轴数据集合
        lineDataSet.setLineWidth(3f);//线宽
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.YELLOW);//现实颜色
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setHighLightColor(Color.WHITE);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData=new LineData(x,lineDataSets);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showChart(com.github.mikephil.charting.charts.LineChart lineChart,LineData lineData,int color){
        lineChart.setDrawBorders(false);//是否添加边框
        lineChart.setDescription("每小时用电费用");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.CYAN);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend=lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.SQUARE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.WHITE);//设置颜色
        lineChart.animateX(3000);//X轴的动画
    }
}


