package com.test.evaluationtestandroidjava.helpers.async_tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.test.evaluationtestandroidjava.adapters.PropertySong;
import com.test.evaluationtestandroidjava.adapters.PropertySongArrayAdapter;
import com.test.evaluationtestandroidjava.helpers.MessageManager;
import com.test.evaluationtestandroidjava.loaders.LoaderSongs;

import java.util.ArrayList;

/**
 * Created by Алексей on 26.08.2018.
 */

public class SearchSongsTask extends AsyncTask {
    private ProgressDialog statusDialog;
    private Activity activity;
    private PropertySongArrayAdapter propertySongArrayAdapter;
    private ArrayList<PropertySong> properties;

    public SearchSongsTask(Activity activity, PropertySongArrayAdapter propertySongArrayAdapter) {
        this.activity = activity;
        this.propertySongArrayAdapter = propertySongArrayAdapter;
    }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(activity);
        statusDialog.setMessage("Connect...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            publishProgress("Wait...");
            publishProgress("Wait...");

            LoaderSongs loaderSongs = new LoaderSongs();
            loaderSongs.searchSongs((String) args[0]);
            properties = loaderSongs.getSongs();

        } catch (Exception e) {
            publishProgress(e.getMessage());
        }

        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {
        statusDialog.setMessage(values[0].toString());
    }

    @Override
    public void onPostExecute(Object result) {
        statusDialog.dismiss();

        if (properties != null) {
            propertySongArrayAdapter.addProperties(properties);
            propertySongArrayAdapter.notifyDataSetChanged();
        } else {
            MessageManager.showToast(activity.getApplicationContext(), "Please, check your internet connection.");
        }
    }
}
