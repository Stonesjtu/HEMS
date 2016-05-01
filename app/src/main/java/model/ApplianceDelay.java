package model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class ApplianceDelay extends Appliance {
    private int duration;
    public int starttime=0;
    public int overtime=24;



    public ApplianceDelay(String id, String name,int ratedpower, int duration) {
         super(DELAY);
        this.duration=duration;
        this.id = id;
        this.name = name;
        this.power = ratedpower;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        String sql = "INSERT INTO appliancedelay(id,name,power,kind)"
                + "VALUES('" + id
                + "','" + name + "','" + ratedpower +  "','" + 0 +"' )";
        db.execSQL(sql);
        db.close();

    }

    public int getDuration() {
        return duration;
    }

    public int getStarttime() {
        return starttime;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int hour) {
        this.overtime = hour;

    }

    public void setStarttime(int hour) {
        this.starttime = hour;
    }

   public void  savetoDB() {
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        ContentValues values = new ContentValues();
        for (int i=1;i>24;i++){
            values.put("state" + i, state[i-1]);
        }
       values.put("duration",duration);
       values.put("starttime",starttime);
       values.put("overtime",overtime);
        db.update("appliancedelay", values, "id=?", new String[]{id});
        db.close();
    }

}
