package com.test.evaluationtestandroidjava;

import com.test.evaluationtestandroidjava.loaders.LoaderAlbums;
import com.test.evaluationtestandroidjava.loaders.LoaderSongs;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ConnectionTest {

    @Test
    public void searchAlbums() {
        LoaderAlbums loaderAlbums = new LoaderAlbums();

        try {
            loaderAlbums.searchAlbums("Eminem");
            Assert.assertNotEquals(0, loaderAlbums.getProperties().size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchSongs() {
        LoaderSongs loaderSongs = new LoaderSongs();

        try {
            loaderSongs.searchSongs("1268028559");
            Assert.assertNotEquals(0, loaderSongs.getSongs().size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
