package db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class  Database extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "hems.db";

    private static Context context;

    public static void setContext(Context context) {
        Database.context = context;
    }

    public Database() {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE appliance(" + "id     VARCHAR(10)	PRIMARY KEY  NOT NULL,"
                + "name 	VARCHAR(10)	NOT NULL,"
                + "power   REAL	 NOT NULL,"
                + "state1   INTEGER	 ,"
                + "state2   INTEGER	 ,"
                + "state3   INTEGER	 ,"
                + "state4   INTEGER	 ,"
                + "state5   INTEGER	 ,"
                + "state6   INTEGER	 ,"
                + "state7   INTEGER	 ,"
                + "state8   INTEGER	,"
                + "state9   INTEGER	,"
                + "state10   INTEGER	 ,"
                + "state11   INTEGER	 ,"
                + "state12   INTEGER	,"
                + "state13   INTEGER	 ,"
                + "state14   INTEGER	 ,"
                + "state15   INTEGER	,"
                + "state16   INTEGER	,"
                + "state17   INTEGER	 ,"
                + "state18  INTEGER	 ,"
                + "state19   INTEGER	,"
                + "state20   INTEGER	 ,"
                + "state21   INTEGER	,"
                + "state22   INTEGER	,"
                + "state23   INTEGER	 ,"
                + "state24   INTEGER	,"
                +"kind INTEGER );";

        String sql1 = "CREATE TABLE appliancedelay(" + "id     VARCHAR(10) PRIMARY KEY 	NOT NULL,"
                + "name 	VARCHAR(10)	NOT NULL,"
                + "power   REAL	 NOT NULL,"
                + "state1   INTEGER	 ,"
                + "state2   INTEGER	 ,"
                + "state3   INTEGER	 ,"
                + "state4   INTEGER	 ,"
                + "state5   INTEGER	,"
                + "state6   INTEGER	 ,"
                + "state7   INTEGER	 ,"
                + "state8   INTEGER	 ,"
                + "state9   INTEGER	 ,"
                + "state10   INTEGER	 ,"
                + "state11   INTEGER	 ,"
                + "state12   INTEGER	,"
                + "state13   INTEGER	 ,"
                + "state14   INTEGER	,"
                + "state15   INTEGER	 ,"
                + "state16   INTEGER	 ,"
                + "state17   INTEGER	 ,"
                + "state18  INTEGER	,"
                + "state19   INTEGER	 ,"
                + "state20   INTEGER	 ,"
                + "state21   INTEGER	,"
                + "state22   INTEGER	 ,"
                + "state23   INTEGER	,"
                + "state24   INTEGER	 ,"
                + "starttime   INTEGER	,"
                + "overtime  INTEGER	 ,"
                + "duration  INTEGER	 ,"
                +"kind INTEGER);";
        String sql2 = "CREATE TABLE appliancether(" + "id     VARCHAR(10) PRIMARY KEY 	NOT NULL,"
                + "name 	VARCHAR(10)	NOT NULL,"
                + "power   REAL	 NOT NULL,"
                + "state1    INTEGER	 ,"
                + "state2   INTEGER	,"
                + "state3    INTEGER	,"
                + "state4    INTEGER	,"
                + "state5    INTEGER	,"
                + "state6   INTEGER	 ,"
                + "state7    INTEGER	 ,"
                + "state8    INTEGER	,"
                + "state9    INTEGER	,"
                + "state10    INTEGER	 ,"
                + "state11    INTEGER	 ,"
                + "state12    INTEGER	 ,"
                + "state13    INTEGER	 ,"
                + "state14    INTEGER	 ,"
                + "state15    INTEGER	,"
                + "state16    INTEGER	 ,"
                + "state17    INTEGER	 ,"
                + "state18   INTEGER	 ,"
                + "state19   INTEGER ,"
                + "state20   INTEGER	 ,"
                + "state21   INTEGER	 ,"
                + "state22   INTEGER	 ,"
                + "state23    INTEGER ,"
                + "state24    INTEGER ,"
                + "starttime   INTEGER	,"
                + "overtime  INTEGER	 ,"
                + "Tset   REAL	,"
                +"kind INTEGER );";

        String sqlbx =" INSERT INTO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
        String sqltv =" INSERT INTO appliance(id,name,power,kind) VALUES('3','电视',150,2)";
        String sqlfj =" INSERT INTO appliance(id,name,power,kind) VALUES('11','风力发电',3000,2)";
        String sqlgf =" INSERT INTO appliance(id,name,power,kind) VALUES('12','光伏电池',3800,2)";
        String sqlac =" INSERT INTO appliancether(id,name,power,kind,starttime,overtime,Tset) " +
                "VALUES('1','空调',2500,1,19,9,22)";
        String sqlwh =" INSERT INTO appliancether(id,name,power,kind,starttime,overtime,Tset)" +
                " VALUES('4','热水器',1500,1,19,9,55)";
        String sqlev =" INSERT INTO appliancether(id,name,power,kind,starttime,overtime,Tset)" +
                " VALUES('7','电动汽车',3000,1,19,9,90)";
        String sqlxy =" INSERT INTO appliancedelay(id,name,power,duration,kind,starttime,overtime)" +
                " VALUES('5','洗衣机',600,2,0,20,3)";
        String sqlxw =" INSERT INTO appliancedelay(id,name,power,duration,kind,starttime,overtime)" +
                " VALUES('6','洗碗机',1000,1,0,20,3)";
        String sqlkj =" INSERT INTO appliancether(id,name,power,kind,starttime,overtime,Tset) " +
                "VALUES('8','空气净化器',300,1,19,9,20)";


        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sqlac);
        db.execSQL(sqlbx);
        db.execSQL(sqltv);
        db.execSQL(sqlwh);
        db.execSQL(sqlxy);
        db.execSQL(sqlxw);
        db.execSQL(sqlev);
        db.execSQL(sqlkj);
        db.execSQL(sqlfj);
        db.execSQL(sqlgf);



    }

//    public void updadeState(String tableName,int[] hstate)
//    {
//        String sql = "UPDATE " + tableName + "(state6，state7，state8,state9," +
//                "state10,state11,state12,state13,state14,state15,state16,state17,state18,state19," +
//                "state20,state21,state22,state23,state24,state1,state2,state3,state4,state5)"
//                + "VALUES('" + hstate[0] +"','" + hstate[1] +
//                "','" + hstate[2] + "','" + hstate[3] + "','" + hstate[4] + "','" + hstate[5] +
//                "','" + hstate[6] +"','" + hstate[7] +"','" + hstate[8] +"','" + hstate[9] +
//                "','" + hstate[10] +"','" + hstate[11] +"','" + hstate[12] +"','" + hstate[13] +
//                "','" + hstate[14] +"','" + hstate[15] +"','" + hstate[16] +"','" + hstate[17]+
//                "','" + hstate[18] +"','" + hstate[19] +"','" + hstate[20] +"','" + hstate[21] +
//                "','" + hstate[22] +"','" + hstate[23] +"' )";
//
//        getWritableDatabase().execSQL(sql);
//    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }

    public void close(SQLiteDatabase db) {
        db.close();
    }
}


    /**
 * Created by YSY on 2016/4/19.
 */


