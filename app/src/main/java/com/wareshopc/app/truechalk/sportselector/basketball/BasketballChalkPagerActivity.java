package com.wareshopc.app.truechalk.sportselector.basketball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.TrueChalkLab;
import com.wareshopc.app.truechalk.sportselector.EventChalk;
import com.wareshopc.app.truechalk.sportselector.EventChalkFragment;

import java.util.ArrayList;
import java.util.UUID;

public class BasketballChalkPagerActivity extends ActionBarActivity implements EventChalkFragment.Callbacks {

    private ViewPager mViewPager;
    private ArrayList<EventChalk> mEventChalks;

    public void onChalkUpdated(EventChalk eventChalk) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mEventChalks = TrueChalkLab.get(this).getTrueChalks();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public int getCount() {
                return mEventChalks.size();
            }

            @Override
            public Fragment getItem(int pos) {
                EventChalk eventChalk = mEventChalks.get(pos);
                return EventChalkFragment.newInstance(eventChalk.getId());
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
                        EventChalk eventChalk = mEventChalks.get(pos);
                        if (eventChalk.getTitle() != null) {
                            setTitle(eventChalk.getTitle());
                        }
                    }
                });

        UUID chalkId = (UUID) getIntent().getSerializableExtra(
                EventChalkFragment.EXTRA_TRUECHALK_ID);
        for (int i = 0; i < mEventChalks.size(); i++) {
            if (mEventChalks.get(i).getId().equals(chalkId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
