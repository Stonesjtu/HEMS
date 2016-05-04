package model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class Appliance {
    public final static int DELAY=0;
    public final static int THERMAL=1;
    public final static int DEFAULT=2;
    public final static double PEAK = 0.977;
    public final static double VALLEY = 0.487;
   public String name;
    protected String id;
    public int  power;
    public int[] state = {0,0,0,0,0,0,0,0,0,
                  0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0};
    public final int kind;

    public  Appliance(String id,int kin){
        this.id=id;
        this.kind=kin;
    }

//    public Appliance(String id, String name, int ratedpower,int cat) {
//        this.id = id;
//        this.name = name;
//        this.power = ratedpower;
//        this.kind=cat;
//        Database connection = new Database();
//        SQLiteDatabase db = connection.getDatabase();
//        String sql = "INSERT INTO appliance(id,name,power,kind)"
//                + "VALUES('" + id
//                + "','" + name + "','" + ratedpower +"','" + cat + "' )";
//        db.execSQL(sql);
//        db.close();
//    }
//
    //不带kind的构造函数，默认=2
    public Appliance(String id) {
        this.id = id;
        this.kind=DEFAULT;
    }

    public int[] getState() {
        return state;
    }

    public int[] getPower(){
        return state;
    }

    public int getSumPower(){
        int sumpower=0;
        for (int i=0;i<24;i++){
            sumpower+=getPower()[i];
        }
        return sumpower;
    }

    public double[] getPrice(){
        double[] nowprice =new double[24];
        for (int i=0;i<16;i++){
            nowprice[i]= PEAK/1000 * getPower()[i];
        }
        for (int i=16;i<24;i++){
            nowprice[i]= VALLEY/1000 * getPower()[i];
        }
        return nowprice;
    }


    public double getSumPrice(){
        int sumprice=0;
        for (int i=0;i<24;i++){
            sumprice+=getPrice()[i];
        }
        return sumprice;
    }



    public void setState( int[] hstate) {

        this.state = hstate;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();

        for (int i=1;i<24;i++) {
            String sql = "UPDATE appliance SET ";
            sql = sql + "state" + i +"="+hstate[i-1]+" where id=" +id;
            db.execSQL(sql);
        }
        db.close();

    }

    public int getKind() {
        return kind;
    }

    public void  savetoDB() {
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        ContentValues values = new ContentValues();
        for (int i=1;i<24;i++){
            values.put("state" + i, state[i-1]);
        }
        db.update("appliance", values, "id=?", new String[]{id});
        db.close();
    }

   public void loadfromDB(){
       Database connection = new Database();
       SQLiteDatabase db = connection.getDatabase();
       Cursor cur = db.rawQuery("select * from appliance where id=?",new String[]{id});
       while (cur.moveToNext()) {
           name=cur.getString(cur.getColumnIndex("name"));
           power=cur.getInt(cur.getColumnIndex("power"));
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
