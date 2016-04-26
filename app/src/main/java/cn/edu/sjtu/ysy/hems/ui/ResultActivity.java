package cn.edu.sjtu.ysy.hems.ui;

import android.app.Activity;
import android.os.Bundle;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/14.
 */
public class ResultActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle(R.string.resulttitle);
    }
}
