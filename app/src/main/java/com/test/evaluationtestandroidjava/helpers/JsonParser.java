package com.test.evaluationtestandroidjava.helpers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Алексей on 24.08.2018.
 */

public class JsonParser {

    public static ArrayList<JSONObject> getJSONObects(String jsonText) throws JSONException {
        JSONObject jObject = new JSONObject(jsonText);
        JSONArray jsonArray = jObject.getJSONArray("results");
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObjects.add(jsonArray.getJSONObject(i));
        }

        return jsonObjects;
    }
}
