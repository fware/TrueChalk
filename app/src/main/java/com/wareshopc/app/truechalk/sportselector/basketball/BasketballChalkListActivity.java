package com.wareshopc.app.truechalk.sportselector.basketball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.SingleFragmentActivity;
import com.wareshopc.app.truechalk.TrueChalkPagerActivity;

public class BasketballChalkListActivity extends SingleFragmentActivity
        implements BasketballChalkListFragment.Callbacks, BasketballChalkFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new BasketballChalkListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public void onChalkSelected(BasketballChalk basketballChalk) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of TrueChalkPagerActivity
            Intent i = new Intent(this, TrueChalkPagerActivity.class);
            i.putExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID, basketballChalk.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = BasketballChalkFragment.newInstance(basketballChalk.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onChalkUpdated(BasketballChalk basketballChalk) {
        FragmentManager fm = getSupportFragmentManager();
        BasketballChalkListFragment listFragment = (BasketballChalkListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
