package com.wareshopc.app.truechalk;


import android.content.Context;

import com.wareshopc.app.truechalk.sportselector.basketball.BasketballChalk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TrueChalkJSONSerializer {

    private Context mContext;
    private String mFilename;

    public TrueChalkJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<BasketballChalk> loadTrueChalks() throws IOException, JSONException {
        ArrayList<BasketballChalk> basketballChalks = new ArrayList<BasketballChalk>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of basketballChalks from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                basketballChalks.add(new BasketballChalk(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return basketballChalks;
    }

    public void saveTrueChalks(ArrayList<BasketballChalk> basketballChalks) throws JSONException, IOException {
        // Build an array in JSON
        JSONArray array = new JSONArray();
        for (BasketballChalk c : basketballChalks)
            array.put(c.toJSON());

        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}