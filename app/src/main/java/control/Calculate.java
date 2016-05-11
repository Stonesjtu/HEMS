package control;

/**
 * Created by YSY on 2016/4/26.
 */
public class Calculate {




    public static int sum(int[] state,int start,int end){
        int sumState=0;
        int lenth=state.length;
        if ((end-start+1)<=lenth) {
            for (int i = start; i <=end; i++) {
                sumState += state[i];
            }
        }
        return sumState;
    }


    public static double sum(double[] state,int start,int end){
        double sumState=0;
        int lenth=state.length;
        if ((end-start+1)<=lenth) {
            for (int i = start; i <=end; i++) {
                sumState += state[i];
            }
        }
        return sumState;
    }

    public static int sum24(int[] state){
        return sum(state,0,24);
    }

    public static double sum24(double[] state){
        return sum(state,0,24);
    }
}

//
//    public double charge = 0; //每日用电费用
//    public double chargepeak = 0;
//    public double chargevalley = 0;
//    public double chargegen = 0;
//    public double chargepeakgen = 0;
//    public double chargevalleygen = 0;
//
//    public int work = 0; //每日电器总功率
//    public int workpeak = 0;
//    public int workvalley = 0;


//    public int getWorkpeak(){
//        workpeak=0;
//        for (int i=0;i<16;i++){
//            workpeak+= MainActivity.Bingxiang.getPower()[i];
//            workpeak+=MainActivity.Kongtiao.getPower()[i];
//            workpeak+=MainActivity.Reshuiqi.getPower()[i];
//            workpeak+=MainActivity.Dianshi.getPower()[i];
//            workpeak+=MainActivity.Xiyiji.getPower()[i];
//            workpeak+=MainActivity.Xiwanji.getPower()[i];
//            workpeak+=MainActivity.Dianche.getPower()[i];
//            workpeak+=MainActivity.Kongjing.getPower()[i];
//        }
//
//        return workpeak;
//    }
//
//
//
//    public  int getWorkvalley(){
//        workvalley=0;
//        for (int i=16;i<24;i++){
//            workvalley+= MainActivity.Bingxiang.getPower()[i];
//            workvalley+=MainActivity.Kongtiao.getPower()[i];
//            workvalley+=MainActivity.Reshuiqi.getPower()[i];
//            workvalley+=MainActivity.Dianshi.getPower()[i];
//            workvalley+=MainActivity.Xiyiji.getPower()[i];
//            workvalley+=MainActivity.Xiwanji.getPower()[i];
//            workvalley+=MainActivity.Dianche.getPower()[i];
//            workvalley+=MainActivity.Kongjing.getPower()[i];
//        }
//
//        return workvalley;
//    }
//
//    public int getWork(){
//
//        work=0;
//        work+= MainActivity.Bingxiang.getSumPower();
//        work+= MainActivity.Kongtiao.getSumPower();
//        work+= MainActivity.Dianshi.getSumPower();
//        work+= MainActivity.Reshuiqi.getSumPower();
//        work+= MainActivity.Xiyiji.getSumPower();
//        work+= MainActivity.Xiwanji.getSumPower();
//        work+= MainActivity.Dianche.getSumPower();
//        work+= MainActivity.Kongjing.getSumPower();
//
//        return work;
//    }
//
//    public double getChargepeak(){
//        chargepeak=0;
//        for (int i=0;i<16;i++){
//           chargepeak+= MainActivity.Bingxiang.getPrice()[i];
//            chargepeak+=MainActivity.Kongtiao.getPrice()[i];
//            chargepeak+=MainActivity.Reshuiqi.getPrice()[i];
//            chargepeak+=MainActivity.Dianshi.getPrice()[i];
//            chargepeak+=MainActivity.Xiyiji.getPrice()[i];
//            chargepeak+=MainActivity.Xiwanji.getPrice()[i];
//            chargepeak+=MainActivity.Dianche.getPrice()[i];
//            chargepeak+=MainActivity.Kongjing.getPrice()[i];
//        }
//        return chargepeak;
//    }
//
//    public double getChargevalley(){
//        chargevalley=0;
//        for (int i=0;i<16;i++){
//            chargevalley += Bingxiang.getPrice()[i];
//            chargevalley += Kongtiao.getPrice()[i];
//            chargevalley += Reshuiqi.getPrice()[i];
//            chargevalley += Dianshi.getPrice()[i];
//            chargevalley += Xiyiji.getPrice()[i];
//            chargevalley += Xiwanji.getPrice()[i];
//            chargevalley += Dianche.getPrice()[i];
//            chargevalley += Kongjing.getPrice()[i];
//        }
//        return chargevalley;
//    }
//
//    public double getCharge(){
//        charge=0;
//        charge= getChargepeak()+getChargevalley();
//
//        return charge;
//    }
//
//
//    public double getChargepeakgen(){  //电源实例化要先打开generator界面
//        chargepeakgen=0;
//        for (int i=0;i<16;i++){
//            chargepeakgen += (MainActivity.Fengji.getPower()[i] * SUBSIDY/1000);
//            chargepeakgen += (MainActivity.Guangfu.getPower()[i]*SUBSIDY/1000);
//        }
//        return chargepeakgen;
//    }
//
//    public double getChargevalleygen(){  //电源实例化要先打开generator界面
//        chargevalleygen=0;
//        for (int i=16;i<24;i++){
//            chargevalleygen += MainActivity.Fengji.getPower()[i] * SUBSIDY/1000;
//            chargevalleygen += MainActivity.Fengji.getPower()[i] * SUBSIDY/1000;
//        }
//        return chargevalleygen;
//    }
//
//    public  double getChargegen(){  //电源实例化要先打开generator界面
//       chargegen=0;
//        chargegen=getChargepeakgen()+getChargevalleygen();
//        return chargegen;
//    }
