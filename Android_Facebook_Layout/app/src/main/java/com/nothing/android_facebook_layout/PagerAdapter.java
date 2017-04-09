package com.nothing.android_facebook_layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by THM on 4/5/2017.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<FacebookFragment> mFragments;
    public PagerAdapter(FragmentManager fm, List<FacebookFragment> list) {
        super(fm);
        mFragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
