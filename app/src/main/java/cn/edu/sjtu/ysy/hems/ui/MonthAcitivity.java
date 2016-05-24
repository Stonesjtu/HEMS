package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import cn.edu.sjtu.ysy.hems.R;

public class MonthAcitivity extends Activity {

    public LineChart lineChart;
    public ArrayList<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
    public LineData lineData=null;
    int year_now;
    int month_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);
        setTitle(R.string.monthtitle);

        Calendar c=Calendar.getInstance();
        year_now=c.get(Calendar.YEAR);
        month_now=c.get(Calendar.MONTH);
        Spinner choosemonth=(Spinner)findViewById(R.id.spinner3);
        final TextView year=(TextView)findViewById(R.id.year);

        choosemonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>month_now){
                    year.setText("2015");
                }
                lineChart =(LineChart)findViewById(R.id.Linechart_month);
                LineData resultLineData=getLineData(position);
                showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MonthAcitivity.this, "Choose month", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count){
        Random rand=new Random();
        x.clear();
        y.clear();
        double result=0;

        if (count==1) {
            for (int i = 1; i < 30; i++) {  //X轴显示的数据
                x.add(i + "");
                result=rand.nextInt(40)+20;
                y.add(new Entry((float)result,i));
            }
        }
        else if (count==0 || count==2 || count==4 || count==6||count==7||count==9||count==11) {
            for (int i = 1; i < 32; i++) {  //X轴显示的数据
                x.add(i + "");
                result=rand.nextInt(40)+20;
                y.add(new Entry((float)result,i));
            }
        }
        else {
            for (int i = 1; i < 32; i++) {  //X轴显示的数据
                x.add(i + "");
               // result=(Math.round(rand.nextDouble())/1.00);
                result=rand.nextInt(40)+20;
                y.add(new Entry((float)result,i));
            }

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
        lineChart.setDescription("每日用电费用");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.LTGRAY);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend=lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.WHITE);//设置颜色
        lineChart.animateX(2000);//X轴的动画
    }

}
