package com.test.evaluationtestandroidjava.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.evaluationtestandroidjava.R;
import com.test.evaluationtestandroidjava.fragments.AlbumFragment;
import com.test.evaluationtestandroidjava.fragments.AlbumsFragment;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AlbumsFragment.OnFragmentAlbumsInteractionListener,
        AlbumFragment.OnFragmentAlbumInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, AlbumsFragment.newInstance()).commit();
    }

    @Override
    public void onFragmentAlbumsInteraction(JSONObject jsonObject, byte [] data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, AlbumFragment.newInstance(jsonObject, data)).addToBackStack("stack").commit();
    }

    @Override
    public void onFragmentAlbumInteraction() {
        back();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    public void back() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
}
