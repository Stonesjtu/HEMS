package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/14.
 */
public class ResultActivity extends Activity {

    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();
    public int[] Total=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //每个电器总功率

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_new);
        setTitle(R.string.resulttitle);
        barChart= (BarChart)findViewById(R.id.barchart_result);

        for (int i=0;i<8;i++){
            for (int h=0;h<24;h++) {
                Total[h] += MainActivity.appliances[i].getPower()[h];
            }
        }

        Spinner chooseapp =(Spinner)findViewById(R.id.spinner2);
        chooseapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entries.clear();
                labels.clear();
                initEntriesData(position);
                initLableData();
                show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
        public void initEntriesData(int index){
            if(index!=8) {
                for (int i = 6; i < 24; i++) {
                    entries.add(new BarEntry(MainActivity.appliances[index].getPower()[i - 6], i));
                }
                for (int j = 18; j < 24; j++) {
                    entries.add(new BarEntry(MainActivity.appliances[index].getPower()[j], j - 18));
                }
            }
            else{
                    for (int i = 6; i < 24; i++) {
                        entries.add(new BarEntry(Total[i - 6], i));
                    }
                    for (int j = 18; j < 24; j++) {
                        entries.add(new BarEntry(Total[j], j - 18));
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
            dataset= new BarDataSet(entries,"每小时预测功率（W）");
            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            BarData data=new BarData(labels,dataset);
            LimitLine line=new LimitLine(6000f);
            barChart.setData(data);
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
            barChart.animateY(3000);
            barChart.setDescription("时刻");
        }

}
