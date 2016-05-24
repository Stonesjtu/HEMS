package cn.edu.sjtu.ysy.hems.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/10.
 */

public class Fragment1 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment1, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment4, container, false);
        //在fragment视图中的按钮
        TextView kongtiaobtn = (TextView) view.findViewById(R.id.kongtiaobutton);
        TextView bingxiangbtn = (TextView) view.findViewById(R.id.bingxiangbutton);
        TextView tvbtn = (TextView) view.findViewById(R.id.tvbutton);
        TextView reshuiqibtn = (TextView) view.findViewById(R.id.reshuiqibutton);
        TextView xiyijiobtn = (TextView) view.findViewById(R.id.xiyijibutton);
        TextView xiwanjiobtn = (TextView) view.findViewById(R.id.xiwanjibutton);
        TextView evbtn = (TextView) view.findViewById(R.id.evbutton);
      //  Button airbtn = (Button) view.findViewById(R.id.airbutton);


        class ButtonListener implements View.OnClickListener {

            public void onClick(View v) {
                int id = v.getId();
                switch (id){
                    case R.id.kongtiaobutton:
                        Intent intent = new Intent(getActivity(), KongtiaoActivity.class);
                        intent.putExtra("dianqi",0);
                        startActivity(intent);
                        break;
                    case R.id.bingxiangbutton:
                        Intent intent1 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent1.putExtra("dianqi",1);
                        startActivity(intent1);
                        break;
                    case R.id.tvbutton:
                        Intent intent2 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent2.putExtra("dianqi",2);
                        startActivity(intent2);
                        break;
                    case R.id.reshuiqibutton:
                        Intent intent3 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent3.putExtra("dianqi",3);
                        startActivity(intent3);
                        break;
                    case R.id.xiyijibutton:
                        Intent intent4 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent4.putExtra("dianqi",4);
                        startActivity(intent4);
                        break;
                    case R.id.xiwanjibutton:
                        Intent intent5 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent5.putExtra("dianqi",5);
                        startActivity(intent5);
                        break;
                    case R.id.evbutton:
                        Intent intent6 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent6.putExtra("dianqi",6);
                        startActivity(intent6);
                        break;
                  /*  case R.id.airbutton:
                        Intent intent7 = new Intent(getActivity(), KongtiaoActivity.class);
                        intent7.putExtra("dianqi",7);
                        startActivity(intent7);
                        break;*/
                   default:break;

                }

            }
        }

        ButtonListener buttonListener = new ButtonListener();
        kongtiaobtn.setOnClickListener(buttonListener);
        bingxiangbtn.setOnClickListener(buttonListener);
        tvbtn.setOnClickListener(buttonListener);
        reshuiqibtn.setOnClickListener(buttonListener);
        xiyijiobtn.setOnClickListener(buttonListener);
        xiwanjiobtn.setOnClickListener(buttonListener);
        evbtn.setOnClickListener(buttonListener);
       // airbtn.setOnClickListener(buttonListener);

        return view;
    }
    //return inflater.inflate(R.layout.fragment1, container, false);

}
