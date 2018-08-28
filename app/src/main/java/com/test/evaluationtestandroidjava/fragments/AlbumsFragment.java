package com.test.evaluationtestandroidjava.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.test.evaluationtestandroidjava.R;
import com.test.evaluationtestandroidjava.adapters.PropertyAlbum;
import com.test.evaluationtestandroidjava.adapters.PropertyAlbumArrayAdapter;
import com.test.evaluationtestandroidjava.containers.ContainerAlbums;
import com.test.evaluationtestandroidjava.helpers.Decoder;
import com.test.evaluationtestandroidjava.helpers.async_tasks.SearchAlbumsTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AlbumsFragment extends Fragment {

    private ListView listViewAlbums;
    private EditText editTextAlbumName;
    private ImageView buttonSearch;

    private OnFragmentAlbumsInteractionListener mListener;

    private PropertyAlbumArrayAdapter propertyAlbumArrayAdapter;
    private ContainerAlbums containerAlbums;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AlbumsFragment newInstance() {
        AlbumsFragment fragment = new AlbumsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_albums, container, false);
        initViews(root);

        if (propertyAlbumArrayAdapter == null) {
            propertyAlbumArrayAdapter = new PropertyAlbumArrayAdapter(getContext(), 0, new ArrayList<PropertyAlbum>(), R.layout.rowlayout_albums);
        }

        if (containerAlbums == null) {
            containerAlbums = new ContainerAlbums();
        }

        listViewAlbums.setAdapter(propertyAlbumArrayAdapter);
        propertyAlbumArrayAdapter.notifyDataSetChanged();
        buttonSearch.setOnClickListener(new OnSearchListener());

        return root;
    }

    private void initViews(View root) {
        listViewAlbums = root.findViewById(R.id.listViewCategory);
        listViewAlbums.setOnItemClickListener(new OnAlbumClickListener());
        editTextAlbumName = root.findViewById(R.id.editTextAlbumName);
        buttonSearch = root.findViewById(R.id.buttonSearch);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAlbumsInteractionListener) {
            mListener = (OnFragmentAlbumsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentAlbumsInteractionListener {
        void onFragmentAlbumsInteraction(JSONObject jsonObject, byte[] byteArray);
    }

    private class OnSearchListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final String nameAlbum = editTextAlbumName.getText().toString();
            propertyAlbumArrayAdapter.clear();
            new SearchAlbumsTask(getActivity(), propertyAlbumArrayAdapter, containerAlbums).execute(nameAlbum);
        }
    }

    private class OnAlbumClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            try {
                mListener.onFragmentAlbumsInteraction(containerAlbums.getAt(i), Decoder.getByteArray(propertyAlbumArrayAdapter.getAt(i).getDrawable()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
