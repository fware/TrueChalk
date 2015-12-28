package com.wareshopc.app.truechalk.sportselector;

import com.wareshopc.app.truechalk.Photo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class EventChalk {

    private static final String JSON_ID = "id";
    private static final String JSON_EVENTNAME = "eventname";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_SUSPECT = "suspect";

    private UUID mId;
    private String mEventName;
    private Date mDate;
    private boolean mSolved;
    private Photo mPhoto;
    private String mSuspect;

    public EventChalk() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

   // enum String {Basketball, Baseball, Football, Soccer, Vollyball};

    public EventChalk(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_EVENTNAME)) {
            mEventName = json.getString(JSON_EVENTNAME);
            if (json.has(JSON_PHOTO))
                mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        }
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

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

}
