package com.wareshopc.app.truechalk;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class TrueChalkListActivity extends SingleFragmentActivity
        implements TrueChalkListFragment.Callbacks, TrueChalkFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new TrueChalkListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public void onChalkSelected(TrueChalk trueChalk) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of TrueChalkPagerActivity
            Intent i = new Intent(this, TrueChalkPagerActivity.class);
            i.putExtra(TrueChalkFragment.EXTRA_TRUECHALK_ID, trueChalk.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = TrueChalkFragment.newInstance(trueChalk.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onChalkUpdated(TrueChalk trueChalk) {
        FragmentManager fm = getSupportFragmentManager();
        TrueChalkListFragment listFragment = (TrueChalkListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
