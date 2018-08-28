package com.test.evaluationtestandroidjava.loaders;

import com.test.evaluationtestandroidjava.adapters.PropertyAlbum;
import com.test.evaluationtestandroidjava.helpers.APIConnector;
import com.test.evaluationtestandroidjava.helpers.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Алексей on 23.08.2018.
 */

public class LoaderAlbums {

    private ArrayList<JSONObject> albums;

    public LoaderAlbums() {
        albums = new ArrayList<>();
    }

    public void searchAlbums(final String str) throws IOException, JSONException {
        albums = JsonParser.getJSONObects(APIConnector.executeAlbumsQuery(str));
    }

    public void sort() {
        Collections.sort(albums, new JSONComparator());
    }

    public ArrayList<PropertyAlbum> getProperties() throws JSONException {
        ArrayList<PropertyAlbum> properties = new ArrayList<>();

        for (int i = 0; i < albums.size(); i++) {
            properties.add(new PropertyAlbum(albums.get(i).getString("collectionName"),
                    APIConnector.LoadImageFromWebOperations(albums.get(i).getString("artworkUrl100"))));
        }

        return properties;
    }

    public ArrayList<JSONObject> getAlbums() {
        return albums;
    }

    public class JSONComparator implements Comparator<JSONObject> {
        public int compare(JSONObject a, JSONObject b) {
            String valA = null;
            String valB = null;
            try {
                valA = a.getString("collectionName");
                valB = b.getString("collectionName");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return valA.compareToIgnoreCase(valB);
        }
    }

}
