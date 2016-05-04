package cn.edu.sjtu.ysy.hems.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/10.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment1, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        //在fragment视图中的按钮
        Button chartbtn = (Button) view.findViewById(R.id.btn_chart);
        Button bullbtn = (Button) view.findViewById(R.id.btn_bullish);
        Button surveybtn = (Button) view.findViewById(R.id.btn_survey);
        Button powerbtn = (Button) view.findViewById(R.id.btn_power);

        class ButtonListener implements View.OnClickListener {

            public void onClick(View v) {
                int id = v.getId();
                switch (id){
                    case R.id.btn_chart:
                        Intent intent = new Intent(getActivity(), Electric.class);
                        intent.putExtra("eletric",0);
                        startActivity(intent);
                        break;
                    case R.id.btn_bullish:
                        Intent intent1 = new Intent(getActivity(), Generator.class);
                        intent1.putExtra("eletric",1);
                        startActivity(intent1);
                        break;
                    case R.id.btn_survey:
                        Intent intent2 = new Intent(getActivity(), Elecharge.class);
                        startActivity(intent2);
                        break;
                    case R.id.btn_power:
                        Intent intent3 = new Intent(getActivity(),PowerActivity.class);
                        startActivity(intent3);
                        break;


                }

            }
        }

        ButtonListener buttonListener = new ButtonListener();
        chartbtn.setOnClickListener(buttonListener);
        bullbtn.setOnClickListener(buttonListener);
        surveybtn.setOnClickListener(buttonListener);
        powerbtn.setOnClickListener(buttonListener);

        return view;
    }
}