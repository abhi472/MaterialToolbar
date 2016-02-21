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
    LinearLayout LL;
    RelativeLayout RL;
    int a;

    // LayoutInflater layoutInflater;
    AppBarLayout abr, abr2;

    View customNav = null, customNav2 = null, customNav3 = null,customNav4=null;
    int page;


    public static final int NUM = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        // layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customNav = LayoutInflater.from(this).inflate(R.layout.toolbar_2, null);
        customNav2 = LayoutInflater.from(this).inflate(R.layout.toolbar_1, null);
        customNav3 = LayoutInflater.from(this).inflate(R.layout.toolbar_3, null);
        customNav4 = LayoutInflater.from(this).inflate(R.layout.toolbar_4, null);
        abr = (AppBarLayout) findViewById(R.id.header);
        abr2 = (AppBarLayout) findViewById(R.id.footer);
        abr2.addView(customNav3);
        abr.addView(customNav);

        /*the lower block implements the custom toolbar integration using getSupportActionbar*/
        {

//        getSupportActionBar().getCustomView(R.id.toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//
//
//
//        getSupportActionBar().setCustomView(R.layout.toolbar_2);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        Toolbar parent =(Toolbar)getSupportActionBar().getCustomView() .getParent();//first get parent toolbar of current action bar
//        parent.setContentInsetsRelative(0,0);
//
//        ViewGroup.LayoutParams lp = getSupportActionBar().getCustomView().getLayoutParams();
//        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        getSupportActionBar().getCustomView().setLayoutParams(lp);// set padding programmatically to 0dp

//        layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
//        View customNav = layoutInflater.from(this).inflate(R.layout.toolbar_2, null);
//        //toolbar1=(Toolbar)customNav.findViewById(R.id.mToolbar);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(customNav);
            // setSupportActionBar(toolbar);
        }


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
                page = viewPager.getCurrentItem();
                if (page == 1) {
                    abr.removeView(customNav);
                    abr.addView(customNav2);
                    abr2.removeView(customNav3);
                    abr2.addView(customNav4);
                } else {
                    abr.removeView(customNav2);

                    abr.addView(customNav);
                    abr2.removeView(customNav4);
                    abr2.addView(customNav3);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_t, menu);


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
