package com.wareshopc.app.truechalk;

import android.content.Context;
import android.util.Log;

import com.wareshopc.app.truechalk.sportselector.EventChalk;

import java.util.ArrayList;
import java.util.UUID;

public class TrueChalkLab {

    private static final String TAG = "TrueChalkLab";
    private static final String FILENAME = "truechalks.json";
    private static TrueChalkLab sTrueChalkLab;
    private ArrayList<EventChalk> mEventChalks;
    private TrueChalkJSONSerializer mSerializer;
    private Context mAppContext;

    private TrueChalkLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new TrueChalkJSONSerializer(mAppContext, FILENAME);

        try {
            mEventChalks = mSerializer.loadTrueChalks();
        } catch (Exception e) {
            mEventChalks = new ArrayList<EventChalk>();
            Log.e(TAG, "Error loading truechalks: ", e);
        }
    }

    public static TrueChalkLab get(Context c) {
        if (sTrueChalkLab == null) {
            sTrueChalkLab = new TrueChalkLab(c.getApplicationContext());
        }
        return sTrueChalkLab;
    }

    public void addChalk(EventChalk c) {
        mEventChalks.add(c);
    }

    public void deleteChalk(EventChalk c) {
        mEventChalks.remove(c);
    }

    public boolean saveTrueChalks() {
        try {
            mSerializer.saveTrueChalks(mEventChalks);
            Log.d(TAG, "true chalks saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving true chalks: ", e);
            return false;
        }
    }

    public ArrayList<EventChalk> getTrueChalks() {
        return mEventChalks;
    }

    public EventChalk getChalk(UUID id) {
        for (EventChalk c : mEventChalks) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

}
