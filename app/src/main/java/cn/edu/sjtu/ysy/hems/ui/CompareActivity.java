package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/14.
 */
public class CompareActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison);
        setTitle(R.string.comparetitle);

        Button before =(Button)findViewById(R.id.before);
        Button after = (Button)findViewById(R.id.after);
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView qian=(TextView)findViewById(R.id.qian);
                qian.setText("优化前");
                ImageView imageqian=(ImageView)findViewById(R.id.imageqian);
                imageqian.setImageResource(R.drawable.whmore);

            }
        });
        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView qian=(TextView)findViewById(R.id.qian);
                qian.setText("优化后");
                ImageView imageqian=(ImageView)findViewById(R.id.imageqian);
                imageqian.setImageResource(R.drawable.bowlmore);
            }
        });
    }


}
