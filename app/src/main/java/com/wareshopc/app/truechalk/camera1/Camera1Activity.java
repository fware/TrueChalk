package com.wareshopc.app.truechalk.camera1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.wareshopc.app.truechalk.SingleFragmentActivity;

public class Camera1Activity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Hide the window title.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();
    }

    @Override
    protected Fragment createFragment() {
        return new Camera1Fragment();
    }
}
