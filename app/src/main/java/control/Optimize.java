package control;

import java.util.Arrays;
import java.util.Random;

import cn.edu.sjtu.ysy.hems.ui.MainActivity;
import model.Appliance;
import model.ApplianceDelay;
import model.ApplianceTher;

/**
 * Created by YSY on 2016/5/4.
 */
public class Optimize {
    public Appliance Dianyuan;
    public  Appliance Chudian;
    public  Appliance Fuhe; //total power of consume
    public static ApplianceDelay Xiyiji;
    public static ApplianceDelay Xiwanji;
    public static ApplianceTher Kongtiao;
    public static ApplianceTher Reshuiqi;
    public static ApplianceTher Dianche;
    public static ApplianceTher Kongjing;
    public static Appliance[] Appliances;

    public static double[] Tout={27,27,28,29,30,31,32,32,33,31,30,30,29,28,28,27,26,25,25,25,24,25,26,26};
    public double[] Tin=new double[24];
    public  double[] Tset=new double[24]; //优化后实际变化的Tset
    public  double dT=3;
    public double[] Win=new double[24];
    public double[] Wset=new double[24];
    public double dW=5;

    private static final double r=0.9;
    private static final double q=-0.009;
    private static final double R=0.2;
    private static final double Q=13;
    private static final int[] ZERO= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private static final int Pchmax=3000;
    private static  final int Pdismax=-3000;
    private  static final double SOCmax=6000;
    private static  final double SOCmin=600;
    private static  final double gch=0.92;//充电效率
    private static  final double gdis=0.93;//放电效率

    public int[] genpower=new int[48];
    private  int[] conpower=ZERO;
    public  int[] buypower=ZERO;
    public  int[] selpower=ZERO;
    private int[] chupower=ZERO;

    public double peakFee;
    public double valleyFee;
    public double[] Fee;
    public double[] sell;
    public double sellFee;



    public Optimize(){
        Kongtiao=MainActivity.Kongtiao;
        Kongtiao.setState(ZERO);
        Fuhe=new Appliance("13");
        Dianyuan=new Appliance("14");
        Fuhe.setState(addState(MainActivity.Bingxiang.getState(),MainActivity.Dianshi.getState()));
        Dianyuan.setState(addState(MainActivity.Fengji.getState(),MainActivity.Guangfu.getState()));
        Appliances = new Appliance[] {Kongtiao,Fuhe,Dianyuan,Reshuiqi,Xiyiji,Xiwanji,Dianche,Kongjing};

        for (int i=3;i<8;i++){
            Appliances[i]=MainActivity.appliances[i];
            Appliances[i].setState(ZERO);
        }

        for(int i=0;i<24;i++){
            genpower[i]=Dianyuan.getState()[i];
            genpower[i+24]=Dianyuan.getState()[i];
        }

    }

    public double sum24(double[] price,int start,int end){
        double sumPrice=0;
        int lenth=price.length;
        if ((end-start+1)<=lenth) {
            for (int i = start; i <=end; i++) {
                sumPrice += price[i];
            }
        }
        return sumPrice;
    }

    public int[] addState(int[]state1,int[]state2){
        int[] stateTT=ZERO;
        for(int i=0;i<24;i++){
            stateTT[i]=state1[i]+state2[i];
        }
        return stateTT;
    }

    public int[] minusState(int[]state1,int[]state2){
        int[] stateTT=ZERO;
        for(int i=0;i<24;i++){
            stateTT[i]=state1[i]-state2[i];
        }
        return stateTT;
    }

    public double calTroom(double Tin0,double Tout1,int state1,int KorR){          //KorR=1表示空调，=0表示热水器
        double Tin1=0;
        if(KorR==1){
            Tin1=Tin0 +r*(Tout1-Tin0)+q*state1;
        }
        if (KorR==0){
            Tin1=Tin0 +R*(Tout1-Tin0)+Q*state1;
        }
      return Tin1;
    }

    public int calTherpower(double Tin1,double Tin0,double Tout1,int KorR){
        int state1=0;
        double power=0;
        if(KorR==1){
            power=(Tin1-Tin0-r*(Tout1-Tin0))/q;
        }
        if (KorR==0){
            power=(Tin1-Tin0-R*(Tout1-Tin0))/Q;
        }
        state1=(int)Math.rint(power);
        return state1;
    }

    public  int calHev(int SEV0,int Tset){
        int Hev=0;
       double h=0;
        h=10*(Tset-SEV0)/100.0;
        Hev=(int)Math.rint(h);
        return Hev;
    }

    public  int[] calMax(int start,int end) {  //按从大到小顺序，返回什么时间发电功率最大,index的每个元素必然在0——24之间
        int[] index = new int[end-start];
        int[] gepower= Arrays.copyOfRange(genpower,start,end);
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        for (int i = 0; i < gepower.length - 1; i++) {
            for (int j = i + 1; j < gepower.length; j++) {
                if (gepower[i] < gepower[j]) {
                    int temp = gepower[i];
                    int p = index[i];
                    gepower[i] = gepower[j];
                    index[i] = index[j];
                    gepower[j] = temp;
                    index[j] = p;
                }
            }
        }
        return index;
    }

    public  void main() {

        //>>>>>>电动汽车优化，从starttime到overtime时间段中找出Dianyuan功率最大的时间排序，按顺序消耗
        int SEV0 = 0;  //假定到家是电动汽车的剩余量为0
        int Hev = calHev(SEV0, Dianche.Tset);
        if (Dianche.overtime < Hev) {
            Hev = Dianche.overtime;
        }
        //不管峰谷，先满足发出来的电先用掉
        int[] abletime = calMax(Dianche.starttime - 6, Dianche.starttime + Dianche.overtime - 6);
        int index = 0;
        for (int i = 0; i < Hev; i++) {
            index = (abletime[i] + Dianche.starttime - 6) % 24;
            if (Dianyuan.getState()[index] > Dianche.power) {
                Dianche.state[index] = Dianche.power;
                genpower[index] -= Dianche.power;
                // Fuhe.state[index] += Dianche.power;
            } else {
                Dianche.state[index] = Dianche.power;
                buypower[index] = Dianche.power - genpower[index];
                genpower[index] = 0;
                //  Fuhe.state[index] += Dianche.power;
            }
        }

        //>>>>>>蓄电池储能

        //>>>>>>洗衣机2小时优化
        int indexstart = Xiyiji.starttime - 6;
        int indexend = Xiyiji.starttime + Xiyiji.overtime - 6;
        int[] abletimexy = calMax(indexstart, indexend);
        Random rand = new Random();
        int ablestart = (abletimexy[0] + Xiyiji.starttime - 6) % 24;

        if (genpower[ablestart] > Xiyiji.power) {
            Xiyiji.state[ablestart] = 1;
            Xiyiji.state[ablestart + 1] = 1;
            genpower[ablestart] -= Xiyiji.power;
            if (genpower[ablestart + 1] > Xiyiji.power) {
                genpower[abletimexy[1]] -= Xiyiji.power;
            } else {
                buypower[ablestart + 1] += Xiyiji.power - genpower[ablestart + 1];
                genpower[ablestart + 1] = 0;
            }

            // Fuhe.state[ablestart] += Xiyiji.power;
            //Fuhe.state[ablestart + 1] += Xiyiji.power;
        } else {
            int randNum = 0;
            if (indexend > 16 && indexstart < 22) {//可以在谷价22：00——6：00，index16——23买电
                //random
                if (indexstart < 16) {
                    if (indexend > 23) randNum = rand.nextInt(7) + 16;
                    else randNum = rand.nextInt(indexend - 16) + 16;
                } else {
                    if (indexend > 23) randNum = rand.nextInt(23 - indexstart) + 16;
                    else randNum = rand.nextInt(indexend - indexstart) + 16;
                }
            } else {
                randNum = rand.nextInt(Xiyiji.overtime) + indexstart;
            }
            Xiyiji.state[randNum % 24] = 1;
            Xiyiji.state[randNum % 24 + 1] = 1;
            buypower[randNum % 24] += Xiyiji.power;
            buypower[randNum % 24 + 1] += Xiyiji.power;
            //Fuhe.state[randNum % 24] += Xiyiji.power;
            //Fuhe.state[randNum % 24 + 1] += Xiyiji.power;
        }

        //>>>>>>洗碗机1小时优化
        indexstart = Xiwanji.starttime - 6;
        indexend = Xiwanji.starttime + Xiwanji.overtime - 6;
        int[] abletimexw = calMax(indexstart, indexend);
        ablestart = (abletimexw[0] + Xiwanji.starttime - 6) % 24;

        if (genpower[ablestart] > Xiwanji.power) {
            Xiwanji.state[ablestart] = 1;
            genpower[ablestart] -= Xiwanji.power;
            // Fuhe.state[ablestart] += Xiwanji.power;
        } else {
            int randnum = 0;
            if (indexend > 15 && indexstart < 24) {//可以在谷价22：00——6：00，index16——23买电
                //random
                if (indexstart < 16) {
                    if (indexend > 24) randnum = rand.nextInt(8) + 16;
                    else randnum = rand.nextInt(indexend - 16) + 16;
                } else {
                    if (indexend > 24) randnum = rand.nextInt(24 - indexstart) + 16;
                    else randnum = rand.nextInt(indexend - indexstart) + 16;
                }
            } else {
                randnum = rand.nextInt(Xiwanji.overtime) + indexstart;
            }
            Xiwanji.state[randnum % 24] = 1;
            buypower[randnum % 24] += Xiwanji.power;
            //Fuhe.state[randnum % 24] += Xiwanji.power;
        }


        //>>>>>>空调按照设定的准确温度运行时
        int[] exactstate = ZERO;
        indexend = (Kongtiao.starttime + Kongtiao.overtime - 6);
        if ((Kongtiao.starttime + Kongtiao.overtime) > 23) {
            for (int i = Kongtiao.starttime - 6; i < 24; i++) {
                exactstate[i] = calTherpower(Kongtiao.Tset, Kongtiao.Tset, Tout[i], 1);
            }
            for (int i = 0; i < (indexend % 24); i++) {
                exactstate[i] = calTherpower(Kongtiao.Tset, Kongtiao.Tset, Tout[i], 1);
            }
        } else {
            for (int i = Kongtiao.starttime - 6; i < indexend; i++) {
                exactstate[i] = calTherpower(Kongtiao.Tset, Kongtiao.Tset, Tout[i], 1);
            }
        }
        Tin[0] = Kongtiao.Tset;
        for (int i = 1; i < 24; i++) {
            Tin[i] = calTroom(Tin[i - 1], Tout[i], exactstate[i], 1);
        }
        MainActivity.Kongtiao.setState(exactstate);
        MainActivity.Kongtiao.savetoDB();

        //>>>>>>空调优化
        for (int i = 1; i < 24; i++) {
            if (exactstate[i] > genpower[i]) {
                Tin[i] = calTroom(Tin[i - 1], Tout[i], genpower[i], 1);
                if ((Tin[i] - Kongtiao.Tset) > dT) {
                    Tin[i] = Kongtiao.Tset + dT;
                    Kongtiao.state[i] = calTherpower(Tin[i], Tin[i - 1], Tout[i], 1);
                    buypower[i]=Kongtiao.state[i]-genpower[i];
                    genpower[i]=0;
                } else {
                    Kongtiao.state[i] = genpower[i];
                }

            } else {
                Kongtiao.state[i] = exactstate[i];
                genpower[i]-=Kongtiao.state[i];
            }
            Tset[i] = Tin[i];
            //Fuhe.state[i]=Kongtiao.state[i];
        }


        //>>>>>>热水器按照设定的准确温度运行时
        int[] exactstate2 = ZERO;
        indexend = (Reshuiqi.starttime + Reshuiqi.overtime - 6);
        if ((Reshuiqi.starttime + Reshuiqi.overtime) > 23) {
            for (int i = Reshuiqi.starttime - 6; i < 24; i++) {
                exactstate2[i] = calTherpower(Reshuiqi.Tset, Reshuiqi.Tset, Tout[i], 0);
            }
            for (int i = 0; i < (indexend % 24); i++) {
                exactstate2[i] = calTherpower(Reshuiqi.Tset, Reshuiqi.Tset, Tout[i], 0);
            }
        } else {
            for (int i = Reshuiqi.starttime - 6; i < indexend; i++) {
                exactstate2[i] = calTherpower(Reshuiqi.Tset, Reshuiqi.Tset, Tout[i], 0);
            }
        }
        MainActivity.Reshuiqi.setState(exactstate2);
        MainActivity.Reshuiqi.savetoDB();
        Win[0] = (1 - R) * Reshuiqi.Tset;
        for (int i = 1; i < 24; i++) {
            Win[i] = calTroom(Win[i - 1], Tout[i], exactstate2[i], 0);
        }

        //>>>>>>热水器优化
        for (int i = 1; i < 24; i++) {
            if (exactstate2[i] > genpower[i]) {
                Win[i] = calTroom(Win[i - 1], Tout[i], genpower[i], 0);
                if ((Win[i] - Reshuiqi.Tset) > dW) {
                    Win[i] = Reshuiqi.Tset + dW;
                    Reshuiqi.state[i] = calTherpower(Win[i], Win[i - 1], Tout[i], 0);
                    buypower[i]=Reshuiqi.state[i]-genpower[i];
                    genpower[i]=0;
                } else {
                   Reshuiqi.state[i] = genpower[i];
                }

            } else {
                Reshuiqi.state[i] = exactstate2[i];
                genpower[i]-=Reshuiqi.state[i];
            }
             Wset[i] = Win[i];
             //Fuhe.state[i]=Reshuiqi.state[i];
        }
        getBattery();

    }


    public void getBattery() {
        //>>>>>>蓄电池的工作状态
        int[] Py = Arrays.copyOfRange(genpower, 0, 24);
        int[] Ph = Fuhe.getState();
        double[] SOC = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 24; i++) {
            if (Py[i] > Ph[i]) {
                if ((Py[i] - Ph[i]) > Pchmax) {
                    chupower[i] = Pchmax;
                } else chupower[i] = Py[i] - Ph[i];
                SOC[i + 1] = SOC[i] + gch * chupower[i];
                if (SOC[i + 1] > SOCmax) {
                    selpower[i] = Py[i] - Ph[i];  //卖电，卖出为正
                    chupower[i] = 0;
                    SOC[i + 1] = SOC[i] + gch * chupower[i];
                    Chudian.state[i] = 0;
                } else Chudian.state[i] = 1;

            } else {
                if ((Py[i] - Ph[i]) < Pdismax) {
                    chupower[i] = Pdismax;
                } else chupower[i] = Py[i] - Ph[i];
                SOC[i + 1] = SOC[i] + gdis * chupower[i];
                if (SOC[i + 1] < SOCmin) {
                    buypower[i] = Ph[i] - Py[i];     //买电，买入为正
                    chupower[i] = 0;
                    SOC[i + 1] = SOC[i] + gdis * chupower[i];
                    Chudian.state[i] = 0;
                } else Chudian.state[i] = -1;
            }
            if(i==23)genpower[0]+=chupower[i];
            else genpower[i+1]+=chupower[i];
        }

    }
    public void calCharge(){
        for (int i=0;i<16;i++){
            Fee[i]=buypower[i] * Appliance.PEAK /1000.0;
            sell[i]=selpower[i]*Calculate.SELL/1000.0;
        }
        for (int i=16;i<24;i++){
            Fee[i]=buypower[i] * Appliance.VALLEY /1000.0;
            sell[i]=selpower[i]*Calculate.SELL/1000.0;
        }
        peakFee=sum24(Fee,0,15);
        valleyFee=sum24(Fee,16,23);
        sellFee=sum24(sell,0,23);
    }


}
