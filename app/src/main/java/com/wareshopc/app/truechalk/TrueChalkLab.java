package com.wareshopc.app.truechalk;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class TrueChalkLab {

    private static final String TAG = "TrueChalkLab";
    private static final String FILENAME = "truechalks.json";
    private static TrueChalkLab sTrueChalkLab;
    private ArrayList<TrueChalk> mTrueChalks;
    private TrueChalkJSONSerializer mSerializer;
    private Context mAppContext;

    private TrueChalkLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new TrueChalkJSONSerializer(mAppContext, FILENAME);

        try {
            mTrueChalks = mSerializer.loadTrueChalks();
        } catch (Exception e) {
            mTrueChalks = new ArrayList<TrueChalk>();
            Log.e(TAG, "Error loading truechalks: ", e);
        }
    }

    public static TrueChalkLab get(Context c) {
        if (sTrueChalkLab == null) {
            sTrueChalkLab = new TrueChalkLab(c.getApplicationContext());
        }
        return sTrueChalkLab;
    }

    public void addChalk(TrueChalk c) {
        mTrueChalks.add(c);
    }

    public void deleteChalk(TrueChalk c) {
        mTrueChalks.remove(c);
    }

    public boolean saveTrueChalks() {
        try {
            mSerializer.saveTrueChalks(mTrueChalks);
            Log.d(TAG, "true chalks saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving true chalks: ", e);
            return false;
        }
    }

    public ArrayList<TrueChalk> getTrueChalks() {
        return mTrueChalks;
    }

    public TrueChalk getChalk(UUID id) {
        for (TrueChalk c : mTrueChalks) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

}
