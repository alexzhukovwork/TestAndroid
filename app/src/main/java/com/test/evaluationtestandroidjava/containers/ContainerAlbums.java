package com.test.evaluationtestandroidjava.containers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Алексей on 23.08.2018.
 */

public class ContainerAlbums {
    private ArrayList<JSONObject> albums;

    public ContainerAlbums() {

    }

    public void setAlbums(ArrayList<JSONObject> albums) {
        this.albums = albums;
    }

    public JSONObject getAt(int i) throws JSONException {
        return albums.get(i);
    }
}
