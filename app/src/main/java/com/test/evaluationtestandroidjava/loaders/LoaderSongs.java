package com.test.evaluationtestandroidjava.loaders;

import com.test.evaluationtestandroidjava.adapters.PropertySong;
import com.test.evaluationtestandroidjava.helpers.APIConnector;
import com.test.evaluationtestandroidjava.helpers.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Алексей on 26.08.2018.
 */

public class LoaderSongs {

    private ArrayList<JSONObject> songs;

    public LoaderSongs() {
        songs = new ArrayList<>();
    }

    public void searchSongs(final String str) throws IOException, JSONException {
        songs = JsonParser.getJSONObects(APIConnector.executeSongsQuery(str));
    }

    public ArrayList<PropertySong> getSongs() throws JSONException {
        ArrayList<PropertySong> properties = new ArrayList<>();

        for (int i = 1; i < songs.size(); i++) {
            properties.add(new PropertySong(i + ".",
                    songs.get(i).getString("trackName"),
                    millisToTime(songs.get(i).getLong("trackTimeMillis"))));
        }

        return properties;
    }

    private String millisToTime(long millis) {
        Date date = new Date(millis);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }
}
