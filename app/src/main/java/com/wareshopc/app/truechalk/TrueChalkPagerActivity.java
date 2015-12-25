package com.wareshopc.app.truechalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.UUID;

public class TrueChalkPagerActivity extends ActionBarActivity
        implements TrueChalkFragment.Callbacks {

    private ViewPager mViewPager;
    private ArrayList<TrueChalk> mTrueChalks;

    public void onChalkUpdated(TrueChalk trueChalk) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mTrueChalks = TrueChalkLab.get(this).getTrueChalks();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public int getCount() {
                return mTrueChalks.size();
            }

            @Override
            public Fragment getItem(int pos) {
                TrueChalk trueChalk = mTrueChalks.get(pos);
                return TrueChalkFragment.newInstance(trueChalk.getId());
            }
        });

        mViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    public void onPageScrollStateChanged(int state) {
                    }

                    public void onPageScrolled(int pos, float posOffset,
                                               int posOffsetPixels) {
                    }

                    public void onPageSelected(int pos) {
                        TrueChalk trueChalk = mTrueChalks.get(pos);
                        if (trueChalk.getTitle() != null) {
                            setTitle(trueChalk.getTitle());
                        }
                    }
                });

        UUID chalkId = (UUID) getIntent().getSerializableExtra(
                TrueChalkFragment.EXTRA_TRUECHALK_ID);
        for (int i = 0; i < mTrueChalks.size(); i++) {
            if (mTrueChalks.get(i).getId().equals(chalkId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
