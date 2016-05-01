package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
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
    public static final String[] apower={"2500","300","150","1500","600","1000","3000","100"};
    public static final String[] aduraion={"不定","全天","不定","不定","1","1","6—10","不定"};

    public int[] starttimeint = new int[24];
    public int[] overtimeint = new int[24];
    public int[] Tset = new int[24];
    public int index;

    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kongtiao);
        Intent intent=getIntent();
        index=intent.getIntExtra("dianqi", -1);
        barChart= (BarChart) findViewById(R.id.barchart_kongtiao);
        initEntriesData();
        initLableData();
        show();
        setTitle(R.string.kongtiaotitle);

        TextView mingcheng=(TextView)findViewById(R.id.mingcheng);
        mingcheng.setText(aname[index]);
        TextView ratedpower=(TextView)findViewById(R.id.rated_power);
        ratedpower.setText(apower[index]);
        TextView duration=(TextView)findViewById(R.id.duration);
        duration.setText(aduraion[index]);

        //隐藏edittext和Tset
        if (index==1 || index==2){
            LinearLayout LinLayappcat=(LinearLayout)findViewById(R.id.LinLayappcat);
            LinLayappcat.setVisibility(View.GONE);
        }
        //隐藏Tset
         if (index==4 || index==5) {
            LinearLayout LinLayTset=(LinearLayout)findViewById(R.id.LinLayTset);
            LinLayTset.setVisibility(View.GONE);
            EditText starttime = (EditText) findViewById(R.id.starttime);
            starttimeint[index] = Integer.parseInt(starttime.getText().toString());
            EditText overtime = (EditText) findViewById(R.id.overtime);
            overtimeint[index] = Integer.parseInt(overtime.getText().toString());
            if (index==4){
                MainActivity.Xiyiji.setStarttime(starttimeint[index]);
                MainActivity.Xiyiji.setOvertime(overtimeint[index]);
            }
            if(index==5){
                MainActivity.Xiwanji.setStarttime(starttimeint[index]);
                MainActivity.Xiwanji.setOvertime(overtimeint[index]);
            }
        }
        if (index==0 || index==3 || index==7){
            EditText starttime = (EditText) findViewById(R.id.starttime);
            starttimeint[index] = Integer.parseInt(starttime.getText().toString());
            EditText overtime = (EditText) findViewById(R.id.overtime);
            overtimeint[index] = Integer.parseInt(overtime.getText().toString());
            EditText tset = (EditText) findViewById(R.id.Tset);
            Tset[index] = Integer.parseInt(tset.getText().toString());
            switch (index){
                case 0:;
                    MainActivity.Kongtiao.setStarttime(starttimeint[index]);
                    MainActivity.Kongtiao.setOvertime(overtimeint[index]);
                    MainActivity.Kongtiao.setTset(Tset[index]);
                    break;
                case 3:
                    MainActivity.Reshuiqi.setStarttime(starttimeint[index]);
                    MainActivity.Reshuiqi.setOvertime(overtimeint[index]);
                    MainActivity.Reshuiqi.setTset(Tset[index]);
                    break;
//                case 6:
//                    MainActivity.Dianche.setStarttime(starttimeint[index]);
//                    MainActivity.Dianche.setOvertime(overtimeint[index]);
//                    MainActivity.Dianche.setTset(Tset[index]);
//                    break;
                case 7:
                    MainActivity.Kongjing.setStarttime(starttimeint[index]);
                    MainActivity.Kongjing.setOvertime(overtimeint[index]);
                    MainActivity.Kongjing.setTset(Tset[index]);
                    break;
                default:break;

            }

        }
        //电动汽车将设置温度改为设置预期充电量
        if (index==6)
        {
            TextView temp=(TextView)findViewById(R.id.temp);
            temp.setText("设置充电量");
            EditText tset = (EditText) findViewById(R.id.Tset);
            tset.setHint("输入百分制,如80");
            EditText starttime = (EditText) findViewById(R.id.starttime);
            starttimeint[index] = Integer.parseInt(starttime.getText().toString());
            EditText overtime = (EditText) findViewById(R.id.overtime);
            overtimeint[index] = Integer.parseInt(overtime.getText().toString());
            Tset[index] = Integer.parseInt(tset.getText().toString());

            MainActivity.Dianche.setStarttime(starttimeint[index]);
            MainActivity.Dianche.setOvertime(overtimeint[index]);
            MainActivity.Dianche.setTset(Tset[index]);

        }



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
        Toast.makeText(getApplicationContext(),"   "+index,Toast.LENGTH_LONG).show();
        for (int i=0;i<24;i++)
        {
            switch (index){

                case 0: entries.add(new BarEntry(MainActivity.Kongtiao.getState()[i],i));break;
                case 1: entries.add(new BarEntry(MainActivity.Bingxiang.getState()[i],i));break;
                case 2: entries.add(new BarEntry(MainActivity.Dianshi.getState()[i],i));break;
                case 3: entries.add(new BarEntry(MainActivity.Reshuiqi.getState()[i],i));break;
                case 4: entries.add(new BarEntry(MainActivity.Xiyiji.getState()[i],i));break;
                case 5: entries.add(new BarEntry(MainActivity.Xiwanji.getState()[i],i));break;
                case 6: entries.add(new BarEntry(MainActivity.Dianche.getState()[i],i));break;
                case 7: entries.add(new BarEntry(MainActivity.Kongjing.getState()[i],i));break;
                default:break;
            }

        }
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
    }

    public void initLableData(){
        for (int i=0;i<24;i++){
            String in= Integer.toString(i);
            labels.add(in);
        }
//        labels.add("6");
//        labels.add("7");
//        labels.add("8");
//        labels.add("9");
//        labels.add("10");
//        labels.add("11");
//        labels.add("12");
    }
    public void show(){
        dataset= new BarDataSet(entries,"每小时平均用电功率");
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);
        BarData data=new BarData(labels,dataset);
      //  LimitLine line=new LimitLine(10f);
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
        barChart.animateY(1000);
        barChart.getAxisRight().setEnabled(false);
        barChart.setDescription("时刻");
    }

}
