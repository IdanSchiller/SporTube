package com.example.yuotubesearch;
import android.app.FragmentManager;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;


public class TabLayoutAdapter extends FragmentPagerAdapter {

        private MainActivity context;
        int totalTabs;

//        public TabLayoutAdapter(Context context, FragmentManager fm, int totalTabs) {
//            super(fm);
//            myContext = context;
//            this.totalTabs = totalTabs;
//        }

    public TabLayoutAdapter(MainActivity context, androidx.fragment.app.FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        totalTabs=tabCount;
        this.context=context;
    }

    // this is for fragment tabs
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    SearchFragment homeFragment = new SearchFragment(context);
                    return homeFragment;
                case 1:
                    SportFragment sportFragment = new SportFragment();
                    return sportFragment;
                case 2:
                    MovieFragment movieFragment = new MovieFragment();
                    return movieFragment;
                default:
                    return null;
            }
        }
        // this counts total number of tabs
        @Override
        public int getCount() {
            return totalTabs;
        }
    }

