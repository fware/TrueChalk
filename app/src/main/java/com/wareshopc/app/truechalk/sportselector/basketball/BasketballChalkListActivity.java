package com.wareshopc.app.truechalk.sportselector.basketball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.SingleFragmentActivity;

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

    public void onBasketballChalkSelected(BasketballChalk basketballChalk) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of BasketballChalkPagerActivity
            Intent i = new Intent(this, BasketballChalkPagerActivity.class);
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

    public void onBasketballChalkUpdated(BasketballChalk basketballChalk) {
        FragmentManager fm = getSupportFragmentManager();
        BasketballChalkListFragment listFragment = (BasketballChalkListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
