package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    EditText starttime;
    EditText overtime;
    EditText tset;


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

        starttime = (EditText) findViewById(R.id.starttime);
        overtime = (EditText) findViewById(R.id.overtime);
        tset = (EditText) findViewById(R.id.Tset);

        TextView mingcheng=(TextView)findViewById(R.id.mingcheng);
        mingcheng.setText(aname[index]);
        TextView ratedpower=(TextView)findViewById(R.id.rated_power);
        ratedpower.setText(apower[index]);
        TextView duration=(TextView)findViewById(R.id.duration);
        duration.setText(aduraion[index]);
        LinearLayout LinLayTset=(LinearLayout)findViewById(R.id.LinLayTset);
        LinearLayout LinLayappcat=(LinearLayout)findViewById(R.id.LinLayappcat);

        // 显示开始时间和结束时间
        switch (index){
            case 0:
                starttime.setText("" + MainActivity.Kongtiao.getStarttime());
                overtime.setText("" + MainActivity.Kongtiao.getOvertime());
                tset.setText("" + MainActivity.Kongtiao.getTset());
                break;
            case 3:
                starttime.setText("" + MainActivity.Reshuiqi.getStarttime());
                overtime.setText("" + MainActivity.Reshuiqi.getOvertime());
                tset.setText("" + MainActivity.Reshuiqi.getTset());
                break;
            case 4:
                LinLayTset.setVisibility(View.GONE);
                starttime.setText("" + MainActivity.Xiyiji.getStarttime());
                overtime.setText("" + MainActivity.Xiyiji.getOvertime());
                break;
            case 5:
                LinLayTset.setVisibility(View.GONE);
                starttime.setText("" + MainActivity.Xiwanji.getStarttime());
                overtime.setText("" + MainActivity.Xiwanji.getOvertime());
                break;
            case 6:
                TextView temp=(TextView)findViewById(R.id.temp);
                temp.setText("设置充电量");
                tset.setHint("输入百分制,如80");
                tset.setText("80");
                starttime.setText("" + MainActivity.Dianche.getStarttime());
                overtime.setText("" + MainActivity.Dianche.getOvertime());
                tset.setText("" + MainActivity.Dianche.getTset());
                break;
            case 7:
                starttime.setText("" + MainActivity.Kongjing.getStarttime());
                overtime.setText("" + MainActivity.Kongjing.getOvertime());
                tset.setText("" + MainActivity.Kongjing.getTset());
                break;
            default:
                //隐藏edittext和Tset
                LinLayappcat.setVisibility(View.GONE);
                break;
        }
        //jump
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String strurl="http://45.62.118.181/ysy?name=kongtiao";
        try {
            URL url=new URL(strurl);
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            InputStreamReader in =new InputStreamReader(urlConn.getInputStream());
            BufferedReader bufferReader= new BufferedReader(in);
            String result="";
            String readline=null;
            while ((readline=bufferReader.readLine())!=null){
                result +=readline;
            }
            in.close();
            urlConn.disconnect();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void initEntriesData() {
        for (int i=6;i<24;i++)
        {
            switch (index){

                case 0: entries.add(new BarEntry(MainActivity.Kongtiao.getPower()[i-6],i));break;
                case 1: entries.add(new BarEntry(MainActivity.Bingxiang.getPower()[i-6],i));break;
                case 2: entries.add(new BarEntry(MainActivity.Dianshi.getPower()[i-6],i));break;
                case 3: entries.add(new BarEntry(MainActivity.Reshuiqi.getPower()[i-6],i));break;
                case 4: entries.add(new BarEntry(MainActivity.Xiyiji.getPower()[i-6],i));break;
                case 5: entries.add(new BarEntry(MainActivity.Xiwanji.getPower()[i-6],i));break;
                case 6: entries.add(new BarEntry(MainActivity.Dianche.getPower()[i-6],i));break;
                case 7: entries.add(new BarEntry(MainActivity.Kongjing.getPower()[i-6],i));break;
                default:break;
            }
            }
        for (int j=18;j<24;j++){
            entries.add(new BarEntry(MainActivity.appliances[index].getPower()[j],j-18));

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
        dataset= new BarDataSet(entries,"每小时平均用电功率（W）");
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            finish();
            return true;
        }
        else  if (id == R.id.action_save) {
            //判断输入是否合法，若合法，存入对象和数据库中，否则弹出对话框,缺少空指针处理
            if (index!=1 && index!=2) {
                starttimeint[index] = Integer.parseInt(starttime.getText().toString());
                overtimeint[index] = Integer.parseInt(overtime.getText().toString());
                if (starttimeint[index] > 24 || starttimeint[index] < 0 || overtimeint[index] > 24
                        || overtimeint[index] < 0 )
                    showDialog();
                else
                    switch (index) {
                        case 0:
                            Tset[index]=Integer.parseInt(tset.getText().toString());
                            if(Tset[index]>32 || Tset[index]<16){
                                Toast.makeText(getApplicationContext(),"您输入的温度范围无法实现",Toast.LENGTH_LONG).show();
                                break;
                            }
                            MainActivity.Kongtiao.setStarttime(starttimeint[index]);
                            MainActivity.Kongtiao.setOvertime(overtimeint[index]);
                            MainActivity.Kongtiao.setTset(Tset[index]);
                            MainActivity.Kongtiao.savetoDB();
                            break;
                        case 3:
                            Tset[index]=Integer.parseInt(tset.getText().toString());
                            if(Tset[index]>70 || Tset[index]<10){
                                Toast.makeText(getApplicationContext(),"您输入的温度范围无法实现",Toast.LENGTH_LONG).show();
                                break;
                            }
                            MainActivity.Reshuiqi.setStarttime(starttimeint[index]);
                            MainActivity.Reshuiqi.setOvertime(overtimeint[index]);
                            MainActivity.Reshuiqi.setTset(Tset[index]);
                            MainActivity.Reshuiqi.savetoDB();
                            break;
                        case 4:
                            MainActivity.Xiyiji.setStarttime(starttimeint[index]);
                            MainActivity.Xiyiji.setOvertime(overtimeint[index]);
                            MainActivity.Xiyiji.savetoDB();
                            break;
                        case 5:
                            MainActivity.Xiwanji.setStarttime(starttimeint[index]);
                            MainActivity.Xiwanji.setOvertime(overtimeint[index]);
                            MainActivity.Xiwanji.savetoDB();
                            break;
                        case 6:
                            Tset[index]=Integer.parseInt(tset.getText().toString());
                            if(Tset[index]>100 || Tset[index]<1){
                                Toast.makeText(getApplicationContext(),"您输入的充电量范围无法实现",Toast.LENGTH_LONG).show();
                                break;
                            }
                            MainActivity.Dianche.setStarttime(starttimeint[index]);
                            MainActivity.Dianche.setOvertime(overtimeint[index]);
                            MainActivity.Dianche.setTset(Tset[index]);
                            MainActivity.Dianche.savetoDB();
                        case 7:
                            Tset[index]=Integer.parseInt(tset.getText().toString());
                            if(Tset[index]>70 || Tset[index]<10){
                                Toast.makeText(getApplicationContext(),"您输入的温度范围无法实现",Toast.LENGTH_LONG).show();
                                break;
                            }
                            MainActivity.Kongjing.setStarttime(starttimeint[index]);
                            MainActivity.Kongjing.setOvertime(overtimeint[index]);
                            MainActivity.Kongjing.setTset(Tset[index]);
                            MainActivity.Kongjing.savetoDB();
                            break;
                        default:break;
                    };
                finish();

            }

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(
                KongtiaoActivity.this);
        builder.setTitle("消息").setIcon(android.R.drawable.stat_notify_error);
        builder.setMessage("您输入的格式不合法，请修改");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        dialog = builder.create();
        dialog.show();
    }


}
