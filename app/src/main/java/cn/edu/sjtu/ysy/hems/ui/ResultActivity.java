package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cn.edu.sjtu.ysy.hems.R;
import control.Optimize;

/**
 * Created by YSY on 2016/4/14.
 */
public class ResultActivity extends Activity {

    public Optimize optm;
    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();

    public LineChart lineChart;
    public ArrayList<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
    public LineData lineData=null;
   // public int[] Total=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //每个电器总功率

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_new);
        setTitle(R.string.resulttitle);
        barChart= (BarChart)findViewById(R.id.barchart_result);

        optm=new Optimize();

       // Toast.makeText(getApplicationContext(),""+optm.Dianche.getState(), Toast.LENGTH_LONG).show();
        optm.optim();


//        for (int i=0;i<8;i++){
//            for (int h=0;h<24;h++) {
//                Total[h] += MainActivity.appliances[i].getPower()[h];
//            }
//        }

        Spinner chooseapp =(Spinner)findViewById(R.id.spinner2);
        chooseapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entries.clear();
                labels.clear();
                initEntriesData(position);
                initLableData();
                show();
                TextView textTin=(TextView)findViewById(R.id.pred_Tin);
                textTin.setVisibility(View.GONE);
                lineChart= (LineChart)findViewById(R.id.linechart_result);
                lineChart.setVisibility(View.GONE);
                if (position==0){
                    textTin.setVisibility(View.VISIBLE);
                    lineChart.setVisibility(View.VISIBLE);
                    textTin.setText("预测室温");
                    LineData resultLineData=getLineData(position);
                    showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));
                }
                else if (position==3){
                    textTin.setVisibility(View.VISIBLE);
                    lineChart.setVisibility(View.VISIBLE);
                    textTin.setText("预测水温");
                    LineData resultLineData=getLineData(position);
                    showChart(lineChart, resultLineData, Color.rgb(110, 190, 224));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
        public void initEntriesData(int index){
            int[] totalPower=new int[24];
            if(index==1 || index==2 || index==7) {
                for (int i = 0; i < 24; i++) {
                    entries.add(new BarEntry(MainActivity.appliances[index].getPower()[i], i));
                }
            }
            else if(index==8){
                for (int i = 0; i < 24; i++) {
                    for (int n=0;n<8;n++){
                         totalPower[i]+=optm.Appliances[index].getPower()[i];
                         entries.add(new BarEntry(totalPower[i], i));
                    }
                }
            }       //total power
            else{
                    for (int i = 0; i < 24; i++) {
                        entries.add(new BarEntry(optm.Appliances[index].getPower()[i], i));
                    }
                }
            }

        public void initLableData(){
            for (int i=0;i<24;i++){
                String in= Integer.toString(i);
                labels.add(in);
            }
        }
        public void show(){
            dataset= new BarDataSet(entries,"优化后每小时功率（W）");
            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            BarData data=new BarData(labels,dataset);
         //   LimitLine line=new LimitLine(6000f);
            barChart.setData(data);
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
            barChart.getAxisRight().setEnabled(false);
            barChart.animateY(3000);
            barChart.setDescription("时刻");
        }

    public LineData getLineData(int count) {
        x.clear();
        y.clear();
        if (count == 0) {
            for (int i = 0; i < 24; i++) {  //X轴显示的数据
                x.add(i + "");
                y.add(new Entry((float) optm.Tin[i], i));
            }
        }
        else if(count==3){
            for (int i = 0; i < 24; i++) {  //X轴显示的数据
                x.add(i + "");
                y.add(new Entry((float) optm.Win[i], i));
            }
        }

        LineDataSet lineDataSet = new LineDataSet(y, "折线图");//y轴数据集合
        lineDataSet.setLineWidth(3f);//线宽
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.BLUE);//现实颜色
        lineDataSet.setCircleSize(Color.LTGRAY);//圆形颜色
        lineDataSet.setHighLightColor(Color.WHITE);//高度线的颜色
        lineDataSets.clear();
        lineDataSets.add(lineDataSet);
        lineData = new LineData(x, lineDataSets);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showChart(com.github.mikephil.charting.charts.LineChart lineChart,LineData lineData,int color){
        lineChart.setDrawBorders(false);//是否添加边框
        lineChart.setDescription("每小时预测温度");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.WHITE);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend=lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.SQUARE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.DKGRAY);//设置颜色
        lineChart.animateX(3000);//X轴的动画
    }
}
