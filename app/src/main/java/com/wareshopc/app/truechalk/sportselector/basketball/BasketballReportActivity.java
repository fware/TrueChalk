package com.wareshopc.app.truechalk.sportselector.basketball;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.SingleFragmentActivity;
import com.wareshopc.app.truechalk.sportselector.basketball.db.BasketballDatabaseHandler;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BasketballReportActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID chalkdId = (UUID) getIntent().getSerializableExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        return BasketballReportFragment.newInstance(chalkdId);
    }

}
