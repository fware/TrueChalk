package com.wareshopc.app.truechalk.sportselector.basketball;

import android.support.v4.app.Fragment;

import com.wareshopc.app.truechalk.SingleFragmentActivity;

import java.util.UUID;

public class BasketballAccumulateActivity extends SingleFragmentActivity {

    UUID basketballId;

    //public BasketballAccumulateActivity() {
    //    basketballId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
    //}

    @Override
    protected Fragment createFragment() {
        basketballId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        return BasketballAccumulateFragment.newInstance(basketballId);
    }

}
