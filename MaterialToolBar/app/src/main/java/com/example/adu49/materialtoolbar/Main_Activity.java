package com.example.adu49.materialtoolbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.adu49.materialtoolbar.Fragments.Tab_1;
import com.example.adu49.materialtoolbar.Fragments.Tab_2;

import java.util.zip.Inflater;

public class Main_Activity extends AppCompatActivity implements Tab_1.OnFragmentInteractionListener, Tab_2.OnFragmentInteractionListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    View customNav = null, customNav2 = null, customNav3 = null,customNav4=null;
    public static final int NUM = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        customNav = LayoutInflater.from(this).inflate(R.layout.toolbar_2, null);
        customNav2 = LayoutInflater.from(this).inflate(R.layout.toolbar_1, null);
        customNav3 = LayoutInflater.from(this).inflate(R.layout.toolbar_3, null);
        customNav4 = LayoutInflater.from(this).inflate(R.layout.toolbar_4, null);



        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.toolbar_4, null);
        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        Toolbar parent =(Toolbar) mCustomView.getParent();//first get parent toolbar of current action bar
        parent.setContentInsetsAbsolute(0,0);
        ViewGroup.LayoutParams lp = getSupportActionBar().getCustomView().getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getSupportActionBar().getCustomView().setLayoutParams(lp);


        View mCustomView2=mInflater.inflate(R.layout.toolbar_2,null);
        LinearLayout l=(LinearLayout)findViewById(R.id.LL);
        l.addView(mCustomView2);
        ViewGroup.LayoutParams lp2=mCustomView2.getLayoutParams();
        lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp2.height = ViewGroup.LayoutParams.MATCH_PARENT;







        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if (viewPager != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                // invalidateOptionsMenu();
//                page = viewPager.getCurrentItem();
//                if (page == 1) {                  //comment these lines if u want ot use getSupportActionBAr
//                    abr.removeView(customNav);    //comment these lines if u want ot use getSupportActionBAr
//                    abr.addView(customNav2);      //comment these lines if u want ot use getSupportActionBAr
//                    abr2.removeView(customNav3);  //comment these lines if u want ot use getSupportActionBAr
//                    abr2.addView(customNav4);     //comment these lines if u want ot use getSupportActionBAr
//                } else {                          //comment these lines if u want ot use getSupportActionBAr
//                    abr.removeView(customNav2);   //comment these lines if u want ot use getSupportActionBAr
//                                                  //comment these lines if u want ot use getSupportActionBAr
//                    abr.addView(customNav);       //comment these lines if u want ot use getSupportActionBAr
//                    abr2.removeView(customNav4);  //comment these lines if u want ot use getSupportActionBAr
//                    abr2.addView(customNav3);     //comment these lines if u want ot use getSupportActionBAr
//                }                                 //comment these lines if u want ot use getSupportActionBAr
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new Tab_1();
                    break;
                case 1:
                    fragment = new Tab_2();
                    break;
                default:
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return NUM;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page " + position;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_t, menu);
//
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
