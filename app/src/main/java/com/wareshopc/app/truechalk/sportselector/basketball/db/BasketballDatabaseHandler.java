package com.wareshopc.app.truechalk.sportselector.basketball.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wareshopc.app.truechalk.sportselector.basketball.BasketballChalk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MacAttack on 6/3/16.
 */
public class BasketballDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BasketballChalk.db";
    private static final String TABLE_BASKETBALL = "basketball";
    private static final String KEY_ID = "id";
    private static final String KEY_CHALKID = BasketballChalk.JSON_ID;
    private static final String KEY_EVENT = BasketballChalk.JSON_EVENTNAME;
    private static final String KEY_DATE = BasketballChalk.JSON_DATE;
    private static final String KEY_PTS = BasketballChalk.JSON_PTS;
    private static final String KEY_AST = BasketballChalk.JSON_AST;
    private static final String KEY_OREB = BasketballChalk.JSON_OREB;
    private static final String KEY_BLK = BasketballChalk.JSON_BLK;
    private static final String KEY_DREB = BasketballChalk.JSON_DREB;
    private static final String KEY_TO = BasketballChalk.JSON_TO;

    public BasketballDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BASKETBALL_TABLE = "CREATE TABLE " + TABLE_BASKETBALL +  "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CHALKID + " TEXT,"
                + KEY_EVENT + " TEXT," + KEY_DATE + " INTEGER,"
                + KEY_PTS + " INTEGER,"    + KEY_AST + " INTEGER,"
                + KEY_OREB + " INTEGER,"   + KEY_BLK + " INTEGER,"
                + KEY_DREB + " INTEGER,"   + KEY_TO + " INTEGER" + ")";
        db.execSQL(CREATE_BASKETBALL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASKETBALL);

        onCreate(db);
    }

    void insertBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALKID, chalk.getId().toString());
        values.put(KEY_EVENT, chalk.getTitle());
        values.put(KEY_DATE, chalk.getDate().getTime());
        values.put(KEY_PTS, chalk.getPTS());
        values.put(KEY_AST, chalk.getAST());
        values.put(KEY_OREB, chalk.getOREB());
        values.put(KEY_BLK, chalk.getBLK());
        values.put(KEY_DREB, chalk.getDREB());
        values.put(KEY_TO, chalk.getTO());

        db.insert(TABLE_BASKETBALL, null, values);
        db.close();
    }

    public int updateBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALKID, chalk.getId().toString());
        values.put(KEY_EVENT, chalk.getTitle());
        values.put(KEY_DATE, chalk.getDate().getTime());
        values.put(KEY_PTS, chalk.getPTS());
        values.put(KEY_AST, chalk.getAST());
        values.put(KEY_OREB, chalk.getOREB());
        values.put(KEY_BLK, chalk.getBLK());
        values.put(KEY_DREB, chalk.getDREB());
        values.put(KEY_TO, chalk.getTO());

        return db.update(TABLE_BASKETBALL, values, KEY_CHALKID + " = ?",
                new String[] { chalk.getId().toString()});
    }

    BasketballChalk getBasketballChalk(UUID chalkId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BASKETBALL,
                new String[]{KEY_ID, KEY_CHALKID, KEY_EVENT, KEY_DATE,
                        KEY_PTS, KEY_OREB, KEY_AST, KEY_BLK, KEY_DREB, KEY_TO},
                KEY_CHALKID + "=?",
                new String[]{chalkId.toString()},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        BasketballChalk chalk = new BasketballChalk();

        chalk.setId(UUID.fromString(cursor.getString(1)));
        chalk.setTitle(cursor.getString(2));
        chalk.setDate(new Date(cursor.getLong(3)));
        chalk.setPTS(cursor.getInt(4));
        chalk.setOREB(cursor.getInt(5));
        chalk.setAST(cursor.getInt(6));
        chalk.setBLK(cursor.getInt(7));
        chalk.setDREB(cursor.getInt(8));
        chalk.setTO(cursor.getInt(9));

        return chalk;
    }

    public List<BasketballChalk> getAllBasketballChalks() {
        List<BasketballChalk> basketballChalkList = new ArrayList<BasketballChalk>();

        String selectQuery = "SELECT  * FROM " + TABLE_BASKETBALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BasketballChalk chalk = new BasketballChalk();
                chalk.setId(UUID.fromString(cursor.getString(1)));
                chalk.setTitle(cursor.getString(2));
                chalk.setDate(new Date(cursor.getLong(3)));
                chalk.setPTS(cursor.getInt(4));
                chalk.setOREB(cursor.getInt(5));
                chalk.setAST(cursor.getInt(6));
                chalk.setBLK(cursor.getInt(7));
                chalk.setDREB(cursor.getInt(8));
                chalk.setTO(cursor.getInt(9));

                basketballChalkList.add(chalk);
            } while (cursor.moveToNext());
        }

        return basketballChalkList;
    }

    public void deleteBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BASKETBALL, KEY_CHALKID + " = ?", new String[] { String.valueOf(chalk.getId()) });
        db.close();
    }

    public int getBasketballChalkCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BASKETBALL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}