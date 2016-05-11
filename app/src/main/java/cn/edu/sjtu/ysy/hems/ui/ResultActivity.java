package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
   // public int[] Total=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //每个电器总功率

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_new);
        setTitle(R.string.resulttitle);
        barChart= (BarChart)findViewById(R.id.barchart_result);
        TextView textTest=(TextView)findViewById(R.id.testText);
        TextView textTest1=(TextView)findViewById(R.id.testText1);
        TextView textTest2=(TextView)findViewById(R.id.testText2);
        optm=new Optimize();
        textTest.setText(""+optm.Reshuiqi.getTset());
        textTest1.setText(""+optm.Kongtiao.getOvertime());
        textTest2.setText(""+optm.Dianche.getState()[7]);
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
        public void initEntriesData(int index){
            if(index==1 || index==2) {
                for (int i = 0; i < 24; i++) {
                    entries.add(new BarEntry(MainActivity.appliances[index].getPower()[i], i));
                }
            }
            else if(index==8){
                for (int i = 0; i < 24; i++) {
                    entries.add(new BarEntry(optm.Dianyuan.getPower()[i], i));
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
            dataset= new BarDataSet(entries,"每小时预测功率（W）");
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

}
