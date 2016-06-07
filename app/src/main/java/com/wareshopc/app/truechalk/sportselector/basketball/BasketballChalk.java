package com.wareshopc.app.truechalk.sportselector.basketball;

import com.wareshopc.app.truechalk.Photo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class BasketballChalk {

    public static final String JSON_CHALKID = "chalkid";              //chalk id
    public static final String JSON_EVENTNAME = "eventname";//chalk event name
    public static final String JSON_DATE = "date";          //date
    public static final String JSON_PHOTO = "photo";        //photo
    public static final String JSON_PTS = "pts";           //points
    public static final String JSON_OREB = "oreb";         //offensive rebounds
    public static final String JSON_AST = "asts";         //assists
    public static final String JSON_BLK = "blk";           //blocks
    public static final String JSON_DREB = "dreb";         //defensive rebounds
    public static final String JSON_TO = "turnover";             //turnovers

    private UUID mId;
    private String mEventName;
    private Date mDate;
    private Photo mPhoto;
    private int mPTS;
    private int mOREB;
    private int mAST;
    private int mBLK;
    private int mDREB;
    private int mTO;

    public BasketballChalk() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        //mEventName = "Event X";
        mDate = new Date();
        mPTS = 0;
        mOREB = 0;
        mAST = 0;
        mBLK = 0;
        mDREB = 0;
        mTO = 0;
    }


    public BasketballChalk(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_CHALKID));
        if (json.has(JSON_EVENTNAME)) {
            mEventName = json.getString(JSON_EVENTNAME);
            if (json.has(JSON_PHOTO))
                mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        }
        mPTS = json.getInt(JSON_PTS);
        mOREB = json.getInt(JSON_OREB);
        mAST = json.getInt(JSON_AST);
        mBLK = json.getInt(JSON_BLK);
        mDREB = json.getInt(JSON_DREB);
        mTO = json.getInt(JSON_TO);
        mDate = new Date(json.getLong(JSON_DATE));
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_CHALKID, mId.toString());
        json.put(JSON_EVENTNAME, mEventName);
        json.put(JSON_DATE, mDate.getTime());
        json.put(JSON_PTS, mPTS);
        json.put(JSON_OREB, mOREB);
        json.put(JSON_AST, mAST);
        json.put(JSON_BLK, mBLK);
        json.put(JSON_DREB, mDREB);
        json.put(JSON_TO, mTO);
        if (mPhoto != null)
            json.put(JSON_PHOTO, mPhoto.toJSON());
        return json;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo p) {
        mPhoto = p;
    }

    //@Override
    //public String toString() {
    //    return mEventName;
    //}

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String title) {
        mEventName = title;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID chalkid) {
        mId = chalkid;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getPTS() {
        return mPTS;
    }

    public void setPTS(int pts) {
        mPTS = pts;
    }

    public int getOREB() {
        return mOREB;
    }

    public void setOREB(int oreb) {
        mOREB = oreb;
    }

    public int getAST() {
        return mAST;
    }

    public void setAST(int asts) {
        mAST = asts;
    }

    public int getBLK() { return mBLK; }

    public void setBLK(int blk) {
        mBLK = blk;
    }

    public int getTO() { return mTO; }

    public void setTO(int to) {
        mTO = to;
    }

    public int getDREB() {
        return mDREB;
    }

    public void setDREB(int dreb) {
        mDREB = dreb;
    }

}
