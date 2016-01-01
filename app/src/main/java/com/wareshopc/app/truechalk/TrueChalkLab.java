package com.wareshopc.app.truechalk;

import android.content.Context;
import android.util.Log;

import com.wareshopc.app.truechalk.sportselector.basketball.BasketballChalk;

import java.util.ArrayList;
import java.util.UUID;

public class TrueChalkLab {

    private static final String TAG = "TrueChalkLab";
    private static final String FILENAME = "truechalks.json";
    private static TrueChalkLab sTrueChalkLab;
    private ArrayList<BasketballChalk> mBasketballChalks;
    private TrueChalkJSONSerializer mSerializer;
    private Context mAppContext;

    private TrueChalkLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new TrueChalkJSONSerializer(mAppContext, FILENAME);

        try {
            mBasketballChalks = mSerializer.loadTrueChalks();
        } catch (Exception e) {
            mBasketballChalks = new ArrayList<BasketballChalk>();
            Log.e(TAG, "Error loading truechalks: ", e);
        }
    }

    public static TrueChalkLab get(Context c) {
        if (sTrueChalkLab == null) {
            sTrueChalkLab = new TrueChalkLab(c.getApplicationContext());
        }
        return sTrueChalkLab;
    }

    public void addChalk(BasketballChalk c) {
        mBasketballChalks.add(c);
    }

    public void deleteChalk(BasketballChalk c) {
        mBasketballChalks.remove(c);
    }

    public boolean saveTrueChalks() {
        try {
            mSerializer.saveTrueChalks(mBasketballChalks);
            Log.d(TAG, "true chalks saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving true chalks: ", e);
            return false;
        }
    }

    public ArrayList<BasketballChalk> getTrueChalks() {
        return mBasketballChalks;
    }

    public BasketballChalk getChalk(UUID id) {
        for (BasketballChalk c : mBasketballChalks) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

}
