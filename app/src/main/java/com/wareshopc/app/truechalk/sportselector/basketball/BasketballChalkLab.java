package com.wareshopc.app.truechalk.sportselector.basketball;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class BasketballChalkLab {

    private static final String TAG = "BasketballChalkLab";
    private static final String FILENAME = "basketballchalks.json";
    private static BasketballChalkLab sBasketballChalkLab;
    private ArrayList<BasketballChalk> mBasketballChalks;
    private BasketballChalkJSONSerializer mSerializer;
    private Context mAppContext;

    private BasketballChalkLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new BasketballChalkJSONSerializer(mAppContext, FILENAME);

        try {
            mBasketballChalks = mSerializer.loadBasketballChalks();
        } catch (Exception e) {
            mBasketballChalks = new ArrayList<BasketballChalk>();
            Log.e(TAG, "Error loading basketball chalks: ", e);
        }
    }

    public static BasketballChalkLab get(Context c) {
        if (sBasketballChalkLab == null) {
            sBasketballChalkLab = new BasketballChalkLab(c.getApplicationContext());
        }
        return sBasketballChalkLab;
    }

    public void addBasketballChalk(BasketballChalk c) {
        mBasketballChalks.add(c);
    }

    public void deleteBasketballChalk(BasketballChalk c) {
        mBasketballChalks.remove(c);
    }

    public boolean saveBasketballChalks() {
        try {
            mSerializer.saveBasketballChalks(mBasketballChalks);
            Log.d(TAG, "basketball chalks saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving basketball chalks: ", e);
            return false;
        }
    }

    public ArrayList<BasketballChalk> getBasketballChalks() {
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
