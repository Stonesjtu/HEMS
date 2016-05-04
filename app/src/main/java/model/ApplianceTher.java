package model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class ApplianceTher extends Appliance{
    public int Tset;
    public int starttime;
    public int overtime;


    public ApplianceTher(String id) {
        super(id, THERMAL);
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
    public void setState( int[] hstate) {

        this.state = hstate;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();

        for (int i=1;i<24;i++) {
            String sql = "UPDATE appliancether SET ";
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
        values.put("Tset",Tset);
        values.put("starttime",starttime);
        values.put("overtime",overtime);
        db.update("appliancether", values, "id=?", new String[]{id});
        db.close();
    }

    @Override
    public void loadfromDB(){
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        Cursor cur = db.rawQuery("select * from appliancether where id=?",new String[]{id});
        while (cur.moveToNext()) {
            name=cur.getString(cur.getColumnIndex("name"));
            power=cur.getInt(cur.getColumnIndex("power"));
            Tset=cur.getInt(cur.getColumnIndex("Tset"));
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
