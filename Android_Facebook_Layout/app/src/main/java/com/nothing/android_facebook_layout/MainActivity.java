package com.nothing.android_facebook_layout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<StatusItem> mStatusItems;
    private List<FacebookFragment> mFragments;
    private TabLayout mTabLayout;
    private int[] mGreyIcon = {R.drawable.icon_feed_grey, R.drawable.icon_friend_grey, R.drawable
        .icon_globe_grey, R.drawable.icon_more_grey};
    private int[] mBlueIcon = {R.drawable.icon_feed_blue, R.drawable.icon_friend_blue, R.drawable
        .icon_globe_blue, R.drawable.icon_more_blue};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStatusItems = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_mine);
        mStatusItems.add(new StatusItem("Thạch Nguyễn", "Bây giờ là 19 giờ", "Check out ma body",
            R.drawable.ava_random1, R.drawable.image_random1));
        mStatusItems.add(new StatusItem("Some cool name", "February 30 at 25:61", "Thug for " +
            "life", R.drawable.ava_random2, R.drawable.image_random2));
        for (int i = 0; i < 4; i++) {
            mFragments.add(new FacebookFragment().newInstance(mStatusItems));
        }
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(mBlueIcon[0]);
        for (int i = 1; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(mGreyIcon[i]);
        }
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.setIcon(mBlueIcon[tab.getPosition()]);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.setIcon(mGreyIcon[tab.getPosition()]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
