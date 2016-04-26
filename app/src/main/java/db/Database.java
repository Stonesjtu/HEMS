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
                + "state1   INTEGER	 NOT NULL,"
                + "state2   INTEGER	 NOT NULL,"
                + "state3   INTEGER	 NOT NULL,"
                + "state4   INTEGER	 NOT NULL,"
                + "state5   INTEGER	 NOT NULL,"
                + "state6   INTEGER	 NOT NULL,"
                + "state7   INTEGER	 NOT NULL,"
                + "state8   INTEGER	 NOT NULL,"
                + "state9   INTEGER	 NOT NULL,"
                + "state10   INTEGER	 NOT NULL,"
                + "state11   INTEGER	 NOT NULL,"
                + "state12   INTEGER	 NOT NULL,"
                + "state13   INTEGER	 NOT NULL,"
                + "state14   INTEGER	 NOT NULL,"
                + "state15   INTEGER	 NOT NULL,"
                + "state16   INTEGER	 NOT NULL,"
                + "state17   INTEGER	 NOT NULL,"
                + "state18  INTEGER	 NOT NULL,"
                + "state19   INTEGER	 NOT NULL,"
                + "state20   INTEGER	 NOT NULL,"
                + "state21   INTEGER	 NOT NULL,"
                + "state22   INTEGER	 NOT NULL,"
                + "state23   INTEGER	 NOT NULL,"
                + "state24   INTEGER	 NOT NULL,"
                +"kind INTEGER NOT NULL);";

        String sql1 = "CREATE TABLE appliancedelay(" + "id     VARCHAR(10) PRIMARY KEY 	NOT NULL,"
                + "name 	VARCHAR(10)	NOT NULL,"
                + "power   REAL	 NOT NULL,"
                + "state1   INTEGER	 NOT NULL,"
                + "state2   INTEGER	 NOT NULL,"
                + "state3   INTEGER	 NOT NULL,"
                + "state4   INTEGER	 NOT NULL,"
                + "state5   INTEGER	 NOT NULL,"
                + "state6   INTEGER	 NOT NULL,"
                + "state7   INTEGER	 NOT NULL,"
                + "state8   INTEGER	 NOT NULL,"
                + "state9   INTEGER	 NOT NULL,"
                + "state10   INTEGER	 NOT NULL,"
                + "state11   INTEGER	 NOT NULL,"
                + "state12   INTEGER	 NOT NULL,"
                + "state13   INTEGER	 NOT NULL,"
                + "state14   INTEGER	 NOT NULL,"
                + "state15   INTEGER	 NOT NULL,"
                + "state16   INTEGER	 NOT NULL,"
                + "state17   INTEGER	 NOT NULL,"
                + "state18  INTEGER	 NOT NULL,"
                + "state19   INTEGER	 NOT NULL,"
                + "state20   INTEGER	 NOT NULL,"
                + "state21   INTEGER	 NOT NULL,"
                + "state22   INTEGER	 NOT NULL,"
                + "state23   INTEGER	 NOT NULL,"
                + "state24   INTEGER	 NOT NULL,"
                + "starttime   INTEGER	,"
                + "overtime  INTEGER	 ,"
                + "duration  INTEGER	 ,"
                +"kind INTEGER NOT NULL);";
        String sql2 = "CREATE TABLE appliancether(" + "id     VARCHAR(10) PRIMARY KEY 	NOT NULL,"
                + "name 	VARCHAR(10)	NOT NULL,"
                + "power   REAL	 NOT NULL,"
                + "state1    INTEGER	 NOT NULL,"
                + "state2   INTEGER	 NOT NULL,"
                + "state3    INTEGER	 NOT NULL,"
                + "state4    INTEGER	 NOT NULL,"
                + "state5    INTEGER	 NOT NULL,"
                + "state6   INTEGER	 NOT NULL,"
                + "state7    INTEGER	 NOT NULL,"
                + "state8    INTEGER	 NOT NULL,"
                + "state9    INTEGER	 NOT NULL,"
                + "state10    INTEGER	 NOT NULL,"
                + "state11    INTEGER	 NOT NULL,"
                + "state12    INTEGER	 NOT NULL,"
                + "state13    INTEGER	 NOT NULL,"
                + "state14    INTEGER	 NOT NULL,"
                + "state15    INTEGER	 NOT NULL,"
                + "state16    INTEGER	 NOT NULL,"
                + "state17    INTEGER	 NOT NULL,"
                + "state18   INTEGER	 NOT NULL,"
                + "state19   INTEGER NOT NULL,"
                + "state20   INTEGER	 NOT NULL,"
                + "state21   INTEGER	 NOT NULL,"
                + "state22   INTEGER	 NOT NULL,"
                + "state23    INTEGER NOT NULL,"
                + "state24    INTEGER NOT NULL,"
                + "starttime   INTEGER	,"
                + "overtime  INTEGER	 ,"
                + "Tset   REAL	,"
                +"kind INTEGER NOT NULL);";

//        String sqlbx =" INSERT INTO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqltv =" INSERT INTO appliance(id,name,power,kind) VALUES('3','电视',150,2)";
//        String sqlac =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqlwh =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqlev =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqlxy =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqlxw =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";
//        String sqlkj =" INSERT INTEGERO appliance(id,name,power,kind) VALUES('2','冰箱',300,2)";


        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
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


