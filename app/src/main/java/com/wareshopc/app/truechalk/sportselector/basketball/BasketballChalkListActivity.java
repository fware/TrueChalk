package com.wareshopc.app.truechalk.sportselector.basketball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.SingleFragmentActivity;
import com.wareshopc.app.truechalk.sportselector.EventChalk;
import com.wareshopc.app.truechalk.sportselector.EventChalkFragment;

public class BasketballChalkListActivity extends SingleFragmentActivity
        implements BasketballChalkListFragment.Callbacks, EventChalkFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new BasketballChalkListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public void onChalkSelected(EventChalk eventChalk) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of BasketballChalkPagerActivity
            Intent i = new Intent(this, BasketballChalkPagerActivity.class);
            i.putExtra(EventChalkFragment.EXTRA_TRUECHALK_ID, eventChalk.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = EventChalkFragment.newInstance(eventChalk.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onChalkUpdated(EventChalk eventChalk) {
        FragmentManager fm = getSupportFragmentManager();
        BasketballChalkListFragment listFragment = (BasketballChalkListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
