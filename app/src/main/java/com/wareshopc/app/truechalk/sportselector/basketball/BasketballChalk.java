package com.wareshopc.app.truechalk.sportselector.basketball;

import com.wareshopc.app.truechalk.Photo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class BasketballChalk {

    private static final String JSON_ID = "id";
    private static final String JSON_EVENTNAME = "eventname";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_PTS = "pts";           //points
    private static final String JSON_RBS = "rbs";           //rebounds
    private static final String JSON_ASTS = "asts";         //assists
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_SUSPECT = "suspect";

    private UUID mId;
    private String mEventName;
    private Date mDate;
    private boolean mSolved;
    private Photo mPhoto;
    private int mPts;
    private int mRBs;
    private int mAsts;
    private String mSuspect;

    public BasketballChalk() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
        mPts = 0;
        mRBs = 0;
        mAsts = 0;
    }



    public BasketballChalk(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_EVENTNAME)) {
            mEventName = json.getString(JSON_EVENTNAME);
            if (json.has(JSON_PHOTO))
                mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        }
        mPts = json.getInt(JSON_PTS);
        mRBs = json.getInt(JSON_RBS);
        mAsts = json.getInt(JSON_ASTS);
        //if (json.has(JSON_SUSPECT))
        //    mSuspect = json.getString(JSON_SUSPECT);
        //mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_EVENTNAME, mEventName);
        json.put(JSON_DATE, mDate.getTime());
        json.put(JSON_PTS, mPts);
        json.put(JSON_RBS, mRBs);
        json.put(JSON_ASTS, mAsts);
        //json.put(JSON_SOLVED, mSolved);
        if (mPhoto != null)
            json.put(JSON_PHOTO, mPhoto.toJSON());
        //json.put(JSON_SUSPECT, mSuspect);
        return json;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo p) {
        mPhoto = p;
    }

    public String getEvent() {
        return mSuspect;
    }
    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    @Override
    public String toString() {
        return mEventName;
    }

    public String getTitle() {
        return mEventName;
    }

    public void setTitle(String title) {
        mEventName = title;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getPts() {
        return mPts;
    }

    public void setPts(int pts) {
        mPts = pts;
    }

    public int getRBs() {
        return mRBs;
    }

    public void setRBs(int rbs) {
        mRBs = rbs;
    }

    public int getAsts() {
        return mAsts;
    }

    public void setAsts(int asts) {
        mAsts = asts;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

}
