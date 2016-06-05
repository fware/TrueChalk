package com.wareshopc.app.truechalk.sportselector.basketball.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.wareshopc.app.truechalk.sportselector.basketball.BasketballChalk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MacAttack on 6/3/16.
 */
public class BasketballDatabaseHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "basketball";
    //private static final String KEY_ID = "id";
    private static final String KEY_CHALKID = BasketballChalk.JSON_CHALKID;
    private static final String KEY_EVENT = BasketballChalk.JSON_EVENTNAME;
    private static final String KEY_DATE = BasketballChalk.JSON_DATE;
    private static final String KEY_PTS = BasketballChalk.JSON_PTS;
    private static final String KEY_AST = BasketballChalk.JSON_AST;
    private static final String KEY_OREB = BasketballChalk.JSON_OREB;
    private static final String KEY_BLK = BasketballChalk.JSON_BLK;
    private static final String KEY_DREB = BasketballChalk.JSON_DREB;
    private static final String KEY_TO = BasketballChalk.JSON_TO;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                    + KEY_CHALKID + TEXT_TYPE + COMMA_SEP
                    + KEY_EVENT + TEXT_TYPE + COMMA_SEP
                    + KEY_DATE + INTEGER_TYPE + COMMA_SEP
                    + KEY_PTS + INTEGER_TYPE + COMMA_SEP
                    + KEY_AST + INTEGER_TYPE + COMMA_SEP
                    + KEY_OREB + INTEGER_TYPE + COMMA_SEP
                    + KEY_BLK + INTEGER_TYPE + COMMA_SEP
                    + KEY_DREB + INTEGER_TYPE //+ COMMA_SEP
                    //+ KEY_TO + INTEGER_TYPE
                    + " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BasketballChalk.db";

    public BasketballDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALKID, chalk.getId().toString());
        values.put(KEY_EVENT, chalk.getEventName());
        values.put(KEY_DATE, chalk.getDate().getTime());
        values.put(KEY_PTS, chalk.getPTS());
        values.put(KEY_AST, chalk.getAST());
        values.put(KEY_OREB, chalk.getOREB());
        values.put(KEY_BLK, chalk.getBLK());
        values.put(KEY_DREB, chalk.getDREB());
        //values.put(KEY_TO, chalk.getTO());

        db.insert(TABLE_NAME, "null", values);
        db.close();
    }

    public int updateBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALKID, chalk.getId().toString());
        values.put(KEY_EVENT, chalk.getEventName());
        values.put(KEY_DATE, chalk.getDate().getTime());
        values.put(KEY_PTS, chalk.getPTS());
        values.put(KEY_AST, chalk.getAST());
        values.put(KEY_OREB, chalk.getOREB());
        values.put(KEY_BLK, chalk.getBLK());
        values.put(KEY_DREB, chalk.getDREB());
        //values.put(KEY_TO, chalk.getTO());

        return db.update(TABLE_NAME, values, KEY_CHALKID + " = ?",
                new String[] { chalk.getId().toString()});
    }

    public BasketballChalk getBasketballChalk(UUID chalkId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                KEY_CHALKID,
                KEY_EVENT,
                KEY_DATE,
                KEY_PTS,
                KEY_AST,
                KEY_OREB,
                KEY_BLK,
                KEY_DREB
        };

        String selection = KEY_CHALKID + "=?";

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                new String[]{chalkId.toString()},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        BasketballChalk chalk = new BasketballChalk();
        chalk.setId(UUID.fromString(cursor.getString(0)));
        chalk.setEventName(cursor.getString(1));
        chalk.setDate(new Date(cursor.getLong(2)));
        chalk.setPTS(cursor.getInt(3));
        chalk.setAST(cursor.getInt(4));
        chalk.setOREB(cursor.getInt(5));
        chalk.setBLK(cursor.getInt(6));
        chalk.setDREB(cursor.getInt(7));
        //chalk.setTO(cursor.getInt(8));

        return chalk;
    }

    public List<BasketballChalk> getAllBasketballChalks() {
        List<BasketballChalk> basketballChalkList = new ArrayList<BasketballChalk>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BasketballChalk chalk = new BasketballChalk();
                int temp0 = cursor.getInt(0);      //NOTE: a rawQuery call will pull the BaseColumns._ID at 0 index.
                chalk.setId(UUID.fromString(cursor.getString(1)));
                chalk.setEventName(cursor.getString(2));
                chalk.setDate(new Date(cursor.getLong(3)));
                chalk.setPTS(cursor.getInt(4));
                chalk.setOREB(cursor.getInt(5));
                chalk.setAST(cursor.getInt(6));
                chalk.setBLK(cursor.getInt(7));
                chalk.setDREB(cursor.getInt(8));
                //chalk.setTO(cursor.getInt(9));

                basketballChalkList.add(chalk);
            } while (cursor.moveToNext());
        }

        return basketballChalkList;
    }

    public void deleteBasketballChalk(BasketballChalk chalk) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_CHALKID + " = ?", new String[] { chalk.getId().toString() });
        db.close();
    }

    public int getBasketballChalkCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}