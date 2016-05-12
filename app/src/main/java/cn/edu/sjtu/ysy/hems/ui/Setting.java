package cn.edu.sjtu.ysy.hems.ui;


import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/16.
 */


public class Setting  extends PreferenceActivity {

    private SwitchPreference sunny;
    private SwitchPreference control;
//    public static boolean ynoptm;
//    public static boolean yncontrol;
//    SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);
//    public static boolean ynyouhua=shp.getBoolean("if_sunny",true);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_setting);
        sunny=(SwitchPreference)findPreference("if_sunny");
//        ynoptm=sunny.isChecked();
        control=(SwitchPreference)findPreference("if_control");
//        yncontrol=control.isChecked();
        setupActionBar();


    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
