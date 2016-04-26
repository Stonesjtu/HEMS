package model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import db.Database;

/**
 * Created by YSY on 2016/4/11.
 */
public class Appliance {
    public final static int DELAY=0;
    public final static int THERMAL=1;
    public final static int DEFAULT=2;
   protected String name;
    protected String id;
    public double power;
    public int[] state = {0,0,0,0,0,0,0,0,0,
                  0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0};
    public final int kind;


    public Appliance(String id, String name, double ratedpower,int cat) {
        this.id = id;
        this.name = name;
        this.power = ratedpower;
        this.kind=cat;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        String sql = "INSERT INTO appliance(id,name,power,kind)"
                + "VALUES('" + id
                + "','" + name + "','" + ratedpower +"','" + cat + "' )";
        db.execSQL(sql);
        db.close();
    }

    //不带kind的构造函数，默认=2
    public Appliance(String id, String name, double ratedpower) {
        this.id = id;
        this.name = name;
        this.power = ratedpower;
        this.kind=DEFAULT;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        String sql = "INSERT INTO appliance(id,name,power,kind)"
                + "VALUES('" + id
                + "','" + name + "','" + ratedpower +  "','" + 2 +"' )";
        db.execSQL(sql);
        db.close();
    }

    public int[] getState() {
        return state;
    }

    public void setState( int[] hstate) {

        this.state = hstate;
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        String sql = "UPDATE appliance(state1,state2,state3,state4,state5,state6，state7，state8,state9," +
                "state10,state11,state12,state13,state14,state15,state16,state17,state18,state19," +
                "state20,state21,state22,state23,state24)"
                + "VALUES('" + hstate[0] +"','" + hstate[1] +
                "','" + hstate[2] + "','" + hstate[3] + "','" + hstate[4] + "','" + hstate[5] +
                "','" + hstate[6] +"','" + hstate[7] +"','" + hstate[8] +"','" + hstate[9] +
                "','" + hstate[10] +"','" + hstate[11] +"','" + hstate[12] +"','" + hstate[13] +
                "','" + hstate[14] +"','" + hstate[15] +"','" + hstate[16] +"','" + hstate[17]+
                "','" + hstate[18] +"','" + hstate[19] +"','" + hstate[20] +"','" + hstate[21] +
                "','" + hstate[22] +"','" + hstate[23] +"' )";
        db.execSQL(sql);
        db.close();

    }

    public int getKind() {
        return kind;
    }

    public void  savetoDB() {
        Database connection = new Database();
        SQLiteDatabase db = connection.getDatabase();
        ContentValues values = new ContentValues();
        for (int i=1;i>24;i++){
            values.put("state" + i, state[i-1]);
        }
        db.update("appliance", values, "id=?", new String[]{id});
        db.close();
    }

   public void loadfromDB(){
       //select-assign;
       Database connection = new Database();
       SQLiteDatabase db = connection.getDatabase();
       Cursor cur = db.rawQuery("select * from appliance where id=?",new String[]{id});
       while (cur.moveToNext()) {

           int[] state=new int[24];
           for (int i=1;i<25;i++)
           {
               state[i-1] = cur.getColumnIndex("state"+i);
           }
           this.setState(state);
           cur.moveToNext();
       }
       connection.close(db);
   }
//    public double getRatedpower() {
//        return power;
//    }
//
//    public void setRatedpower(double ratedpower) {
//        this.power = ratedpower;
//    }
}
