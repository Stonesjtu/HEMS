package cn.edu.sjtu.ysy.hems.ui;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import cn.edu.sjtu.ysy.hems.R;
import db.Database;
import model.Appliance;
import model.ApplianceDelay;
import model.ApplianceTher;
//test <code></code>





//




public class MainActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    public static Appliance Bingxiang;
    public static Appliance Dianshi;
    public static ApplianceDelay Xiyiji;
    public static ApplianceDelay Xiwanji;
    public static ApplianceTher Kongtiao;
    public static ApplianceTher Reshuiqi;
    public static ApplianceTher Kongjing;
    public static ApplianceTher Dianche;
    public static Appliance Fengji;
    public static Appliance Guangfu;

    public static Appliance[] appliances;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database.setContext(this.getApplicationContext());
        Kongtiao=new ApplianceTher("1");
        Bingxiang=new Appliance("2");
        Dianshi=new Appliance("3");
        Reshuiqi=new ApplianceTher("4");
        Xiyiji=new ApplianceDelay("5");
        Xiwanji=new ApplianceDelay("6");
        Dianche=new ApplianceTher("7");
        Kongjing=new ApplianceTher("8");

        appliances = new Appliance[] {Kongtiao,Bingxiang,Dianshi,Reshuiqi,Xiyiji,Xiwanji,Dianche,Kongjing};

        for (int i=0;i<8;i++){
            appliances[i].loadfromDB();
        }
//
//        Kongtiao.loadfromDB();
//        Bingxiang.loadfromDB();
//        Dianshi.loadfromDB();
//        Reshuiqi.loadfromDB();
//        Xiyiji.loadfromDB();
//        Xiwanji.loadfromDB();
//        Dianche.loadfromDB();
//        Kongjing.loadfromDB();

    //test code

        int trystate[]={451,450,0,0,0,0,0,0,0,0,0,0,0,541,540,451,360,270,270,270,180,270,360,360};
        Kongtiao.setState(trystate);
        int trystatebx[]={43,45,52,37,42,41,47,49,50,38,46,31,47,56,51,47,43,49,40,50,52,51,52,53};
        Bingxiang.setState(trystatebx);
        int trystatetv[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,147,135,100,70,30,0,0};
        Dianshi.setState(trystatetv);
        int trystatersq[]={0,0,0,0,0,0,0,0,0,292,307,384,400,415,415,430,446,461,384,0,0,0,0,0};
        Reshuiqi.setState(trystatersq);
        int trstate1[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0};
        Xiyiji.setState(trstate1);
        int trstate2[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0};
        Xiwanji.setState(trstate2);
        int trystateev[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3000,3000,3000,3000,3000,3000,0,0,0};
        Dianche.setState(trystateev);
        Kongjing.setState(trystatebx);

        Fengji = new Appliance("11");
        Guangfu = new Appliance("12");
        Fengji.loadfromDB();
        Guangfu.loadfromDB();
        int[] gfstate = {0,0, 0, 0, 0, 0, 800, 1500, 2000, 2500, 2800, 3000, 3800, 3800, 3600, 3000, 2500, 1600, 500, 0, 0, 0, 0, 0};//1-24hour
        int[] fjstate = {3000,3000, 3000, 3000, 2800, 2400, 2700, 2500, 1900, 1700, 1500, 1200, 1100, 1500, 1800, 2200, 2400, 2400, 2200, 2100, 2700, 3000, 3000, 3000};
        Fengji.setState(fjstate);
        Guangfu.setState(gfstate);
        Fengji.savetoDB();
        Guangfu.savetoDB();

        for (int i=0;i<8;i++){
            appliances[i].savetoDB();
        }








        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    //test



    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0: return new Fragment1();
                case 1:  return new Fragment2();
                case 2: return new Fragment3();
                default:return new Fragment1();
            }

//            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}


