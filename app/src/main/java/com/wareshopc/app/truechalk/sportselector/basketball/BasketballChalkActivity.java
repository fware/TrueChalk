package com.wareshopc.app.truechalk.sportselector.basketball;

import android.support.v4.app.Fragment;

import com.wareshopc.app.truechalk.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by MacAttack on 5/30/16.
 */
public class BasketballChalkActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID chalkId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        return BasketballChalkFragment.newInstance(chalkId);
    }
}
