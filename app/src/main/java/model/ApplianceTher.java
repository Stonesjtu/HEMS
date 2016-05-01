package model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class ApplianceTher extends Appliance{
    public int Tset;
    public int starttime;
    public int overtime;


    public ApplianceTher(String id, String name, int ratedpower) {
        super(THERMAL);
        this.id = id;
        this.name = name;
        this.power = ratedpower;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        String sql = "INSERT INTO appliancether(id,name,power,kind)"
                + "VALUES('" + id
                + "','" + name + "','" + ratedpower +  "','" + 1 +"' )";
        db.execSQL(sql);
        db.close();
    }

    public int getTset() {
        return Tset;
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

    public void setTset(int t) {
        this.Tset = t;
    }

    @Override
    public int[] getPower(){
        return state;
    }


    public void  savetoDB() {
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        ContentValues values = new ContentValues();
        for (int i=1;i>24;i++){
            values.put("state" + i, state[i-1]);
        }
        values.put("Tset",Tset);
        values.put("starttime",starttime);
        values.put("overtime",overtime);
        db.update("appliancether", values, "id=?", new String[]{id});
        db.close();
    }
}
