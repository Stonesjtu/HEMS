package cn.edu.sjtu.ysy.hems.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import cn.edu.sjtu.ysy.hems.R;

/**
 * Created by YSY on 2016/4/10.
 */
public class Fragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment1, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3, container, false);
        //在fragment视图中的按钮
        TableRow tb1 = (TableRow) view.findViewById(R.id.tabrow1);
        TableRow tb2 = (TableRow) view.findViewById(R.id.tabrow2);
        TableRow tb3 = (TableRow) view.findViewById(R.id.tabrow3);
        TableRow tb4 = (TableRow) view.findViewById(R.id.tabrow4);

        class ButtonListener implements View.OnClickListener {

            public void onClick(View v) {
                int id = v.getId();
                switch (id){
                    case R.id.tabrow1:
                        Intent intent = new Intent(getActivity(), Setting.class);
                        startActivity(intent);
                        break;
                    case R.id.tabrow2:
                        Intent intent1 = new Intent(getActivity(), ResultActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.tabrow3:
                        Intent intent2 = new Intent(getActivity(), CompareActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.tabrow4:
                        Intent intent3 = new Intent(getActivity(), SettingsActivity.class);
                        startActivity(intent3);
                        break;

                }

            }
        }

        ButtonListener buttonListener = new ButtonListener();
        tb1.setOnClickListener(buttonListener);
        tb2.setOnClickListener(buttonListener);
        tb3.setOnClickListener(buttonListener);
        tb4.setOnClickListener(buttonListener);
        return view;
    }
}

