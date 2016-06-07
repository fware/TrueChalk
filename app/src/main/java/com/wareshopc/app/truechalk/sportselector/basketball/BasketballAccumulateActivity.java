package com.wareshopc.app.truechalk.sportselector.basketball;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.SingleFragmentActivity;

import java.util.UUID;

public class BasketballAccumulateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID chalkId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        return BasketballAccumulateFragment.newInstance(chalkId);
    }

}
