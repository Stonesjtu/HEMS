package model;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class ApplianceDelay extends Appliance {
    private int duration;
    public int starttime=0;
    public int overtime=24;



    public ApplianceDelay(String id) {
         super(id,DELAY);
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
    @Override
    public int[] getPower(){

        int[] nowpower=new int[24];
        for (int i=0;i<24;i++){
             nowpower[i]= power * getState()[i];

        }
        return nowpower;
    }

    public void setOvertime(int hour) {
        this.overtime = hour;

    }

    public void setStarttime(int hour) {
        this.starttime = hour;
    }

    @Override
    public void setState( int[] hstate) {

        this.state = hstate;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();

        for (int i=1;i<24;i++) {
            String sql = "UPDATE appliancedelay SET ";
            sql = sql + "state" + i +"="+hstate[i-1]+" where id=" +id;
            db.execSQL(sql);
        }
        db.close();

    }

    @Override
   public void  savetoDB() {
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        ContentValues values = new ContentValues();
        for (int i=1;i<24;i++){
            values.put("state" + i, state[i-1]);
        }
       values.put("duration",duration);
       values.put("starttime",starttime);
       values.put("overtime",overtime);
        db.update("appliancedelay", values, "id=?", new String[]{id});
        db.close();
    }

    @Override
    public void loadfromDB(){
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        Cursor cur = db.rawQuery("select * from appliancedelay where id=?",new String[]{id});
        while (cur.moveToNext()) {
            name=cur.getString(cur.getColumnIndex("name"));
            power=cur.getInt(cur.getColumnIndex("power"));
            duration=cur.getInt(cur.getColumnIndex("duration"));
            starttime=cur.getInt(cur.getColumnIndex("starttime"));
            overtime=cur.getInt(cur.getColumnIndex("overtime"));
            int[] state=new int[24];
            for (int i=1;i<25;i++) {
                state[i-1] = cur.getInt(cur.getColumnIndex("state"+i));
            }
            this.setState(state);
            cur.moveToNext();
        }
        connection.close(db);
    }
}
