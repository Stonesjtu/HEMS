package control;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YSY on 2016/6/1.
 */
public class Communication {
    public int[] ktstate = new int[24];
    public int bxstate[] = new int[24];
    public int tvstate[] = new int[24];
    public int rsqstate[] = new int[24];
    public int xyjstate[] = new int[24];
    public int xwjstate[] = new int[24];
    public int evstate[] = new int[24];
    public int fjstate[] = new int[24];
    public int gfstate[] = new int[24];
    public int qtstate[] = new int[24];

    public Communication() {
        //定义访问的服务器地址
        String strurl = "http://45.62.118.181:800/ysy";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String result = "";
        try {
            URL url = new URL(strurl); //根据地址创建URL对象
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection(); //打开网络链接
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream()); //获取响应的输入流对象
            BufferedReader bufferReader = new BufferedReader(in); //创建包装流,将服务器得到的流对象存入缓冲区
            String readline = null;                             //定义String类型用于储存单行数据
            while ((readline = bufferReader.readLine()) != null) {
                result += readline;                                 //循环读取全部数据
            }
            in.close();             //释放资源
            urlConn.disconnect();  //关闭链接

        } catch (Exception e) {
            e.printStackTrace();
        }

        //json deserialize

        try {
            JSONObject object = (JSONObject) new JSONTokener(result).nextValue();
            JSONArray jakt = object.getJSONArray("Kongtiao");
            JSONArray jabx = object.getJSONArray("Bingxiang");
            JSONArray jatv = object.getJSONArray("Dianshi");
            JSONArray jarsq = object.getJSONArray("Reshuiqi");
            JSONArray jaxyj = object.getJSONArray("Xiyiji");
            JSONArray jaxwj = object.getJSONArray("Xiwanji");
            JSONArray jaev = object.getJSONArray("Dianche");
            JSONArray jaqt = object.getJSONArray("Qita");
            JSONArray jafj = object.getJSONArray("Fengji");
            JSONArray jagf = object.getJSONArray("Guangfu");

            for (int i = 0; i != 24; i++) {
                ktstate[i] = jakt.getInt(i);
                bxstate[i] = jabx.getInt(i);
                tvstate[i] = jatv.getInt(i);
                rsqstate[i] = jarsq.getInt(i);
                xyjstate[i] = jaxyj.getInt(i);
                xwjstate[i] = jaxwj.getInt(i);
                evstate[i] = jaev.getInt(i);
                qtstate[i] = jaqt.getInt(i);
                fjstate[i] = jafj.getInt(i);
                gfstate[i] = jagf.getInt(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
