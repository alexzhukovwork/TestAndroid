package com.test.evaluationtestandroidjava.helpers.async_tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.test.evaluationtestandroidjava.adapters.PropertyAlbum;
import com.test.evaluationtestandroidjava.adapters.PropertyAlbumArrayAdapter;
import com.test.evaluationtestandroidjava.containers.ContainerAlbums;
import com.test.evaluationtestandroidjava.helpers.MessageManager;
import com.test.evaluationtestandroidjava.loaders.LoaderAlbums;

import java.util.ArrayList;

/**
 * Created by Алексей on 25.08.2018.
 */

public class SearchAlbumsTask extends AsyncTask {
    private ProgressDialog statusDialog;
    private Activity activity;
    private PropertyAlbumArrayAdapter propertyAlbumArrayAdapter;
    private ArrayList<PropertyAlbum> properties;
    private ContainerAlbums containerAlbums;

    public SearchAlbumsTask(Activity activity, PropertyAlbumArrayAdapter propertyAlbumArrayAdapter, ContainerAlbums containerAlbums) {
        this.activity = activity;
        this.propertyAlbumArrayAdapter = propertyAlbumArrayAdapter;
        this.containerAlbums = containerAlbums;
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

            LoaderAlbums loaderAlbums = new LoaderAlbums();
            loaderAlbums.searchAlbums((String) args[0]);
            loaderAlbums.sort();
            properties = loaderAlbums.getProperties();
            containerAlbums.setAlbums(loaderAlbums.getAlbums());

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
            propertyAlbumArrayAdapter.addProperties(properties);
            propertyAlbumArrayAdapter.notifyDataSetChanged();
        } else {
            MessageManager.showToast(activity.getApplicationContext(), "Please, check your internet connection.");
        }
    }
}
