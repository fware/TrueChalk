package com.wareshopc.app.truechalk.sportselector.basketball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.wareshopc.app.truechalk.R;

import java.util.ArrayList;
import java.util.UUID;

public class BasketballChalkPagerActivity extends ActionBarActivity implements BasketballChalkFragment.Callbacks {

    private ViewPager mViewPager;
    private ArrayList<BasketballChalk> mBasketballChalks;

    public void onBasketballChalkUpdated(BasketballChalk basketballChalk) {/* A default impl of this callback.*/}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mBasketballChalks = BasketballChalkLab.get(this).getBasketballChalks();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public int getCount() {
                return mBasketballChalks.size();
            }

            @Override
            public Fragment getItem(int pos) {
                BasketballChalk basketballChalk = mBasketballChalks.get(pos);
                return BasketballChalkFragment.newInstance(basketballChalk.getId());
            }
        });

        /*mViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    public void onPageScrollStateChanged(int state) {
                    }

                    public void onPageScrolled(int pos, float posOffset,
                                               int posOffsetPixels) {
                    }

                    public void onPageSelected(int pos) {
                        BasketballChalk basketballChalk = mBasketballChalks.get(pos);
                        if (basketballChalk.getTitle() != null) {
                            setTitle(basketballChalk.getTitle());
                        }
                    }
                });*/

        UUID chalkId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        for (int i = 0; i < mBasketballChalks.size(); i++) {
            if (mBasketballChalks.get(i).getId().equals(chalkId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
