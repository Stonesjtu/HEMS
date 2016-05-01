package control;

import cn.edu.sjtu.ysy.hems.ui.MainActivity;

import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Bingxiang;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Dianche;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Dianshi;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Kongjing;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Kongtiao;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Reshuiqi;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Xiwanji;
import static cn.edu.sjtu.ysy.hems.ui.MainActivity.Xiyiji;

/**
 * Created by YSY on 2016/4/26.
 */
public class Calculate {

    public final static double subsidy = 0.42;
    public final static double sell = 0.44;


    public double charge=0; //每日用电费用
    public double chargepeak=0;
    public double chargevalley=0;

    public int work=0; //每日电器总功率
    public int workpeak=0;
    public int workvalley=0;

    public int getWorkpeak(){
        for (int i=0;i<16;i++){
            workpeak+= MainActivity.Bingxiang.getPower()[i];
            workpeak+=MainActivity.Kongtiao.getPower()[i];
            workpeak+=MainActivity.Reshuiqi.getPower()[i];
            workpeak+=MainActivity.Dianshi.getPower()[i];
            workpeak+=MainActivity.Xiyiji.getPower()[i];
            workpeak+=MainActivity.Xiwanji.getPower()[i];
            workpeak+=MainActivity.Dianche.getPower()[i];
            workpeak+=MainActivity.Kongjing.getPower()[i];
        }

        return workpeak;
    }



    public int getWorkvalley(){
        for (int i=16;i<24;i++){
            workvalley+= MainActivity.Bingxiang.getPower()[i];
            workvalley+=MainActivity.Kongtiao.getPower()[i];
            workvalley+=MainActivity.Reshuiqi.getPower()[i];
            workvalley+=MainActivity.Dianshi.getPower()[i];
            workvalley+=MainActivity.Xiyiji.getPower()[i];
            workvalley+=MainActivity.Xiwanji.getPower()[i];
            workvalley+=MainActivity.Dianche.getPower()[i];
            workvalley+=MainActivity.Kongjing.getPower()[i];
        }

        return workvalley;
    }

    public int getWork(){

        work+= MainActivity.Bingxiang.getSumPower();
        work+= MainActivity.Kongtiao.getSumPower();
        work+= MainActivity.Dianshi.getSumPower();
        work+= MainActivity.Reshuiqi.getSumPower();
        work+= MainActivity.Xiyiji.getSumPower();
        work+= MainActivity.Xiwanji.getSumPower();
        work+= MainActivity.Dianche.getSumPower();
        work+= MainActivity.Kongjing.getSumPower();

        return work;
    }

    public double getChargepeak(){
        for (int i=0;i<16;i++){
           chargepeak+= MainActivity.Bingxiang.getPrice()[i];
            chargepeak+=MainActivity.Kongtiao.getPrice()[i];
            chargepeak+=MainActivity.Reshuiqi.getPrice()[i];
            chargepeak+=MainActivity.Dianshi.getPrice()[i];
            chargepeak+=MainActivity.Xiyiji.getPrice()[i];
            chargepeak+=MainActivity.Xiwanji.getPrice()[i];
            chargepeak+=MainActivity.Dianche.getPrice()[i];
            chargepeak+=MainActivity.Kongjing.getPrice()[i];
        }
        return chargepeak;
    }

    public double getChargevalley(){
        for (int i=0;i<16;i++){
            chargevalley += Bingxiang.getPrice()[i];
            chargevalley += Kongtiao.getPrice()[i];
            chargevalley += Reshuiqi.getPrice()[i];
            chargevalley += Dianshi.getPrice()[i];
            chargevalley += Xiyiji.getPrice()[i];
            chargevalley += Xiwanji.getPrice()[i];
            chargevalley += Dianche.getPrice()[i];
            chargevalley += Kongjing.getPrice()[i];
        }
        return chargevalley;
    }

    public double getCharge(){

        charge += Bingxiang.getSumPower();
        charge += Kongtiao.getSumPower();
        charge += Dianshi.getSumPower();
        charge += Reshuiqi.getSumPower();
        charge += Xiyiji.getSumPower();
        charge += Xiwanji.getSumPower();
        charge += Dianche.getSumPower();
        charge += Kongjing.getSumPower();

        return charge;
    }

}
