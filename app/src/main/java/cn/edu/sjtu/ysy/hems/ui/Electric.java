package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/11.
 */
public class Electric extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.daily_eletric);
        setTitle(R.string.electrictitle);
        Intent intent=getIntent();
        int index=intent.getIntExtra("eletric", -1);
       switch (index){
           case 0:setContentView(R.layout.daily_eletric);break;
           case 1:setContentView(R.layout.monthly_eletric);break;
       }

    }
}
