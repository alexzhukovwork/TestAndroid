package com.test.evaluationtestandroidjava.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.test.evaluationtestandroidjava.R;
import com.test.evaluationtestandroidjava.adapters.PropertySong;
import com.test.evaluationtestandroidjava.adapters.PropertySongArrayAdapter;
import com.test.evaluationtestandroidjava.helpers.Decoder;
import com.test.evaluationtestandroidjava.helpers.async_tasks.SearchSongsTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumFragment extends Fragment {

    private static final String ARG_JSON = "ARG_JSON";
    private static final String ARG_IMG = "ARG_IMG";

    private ImageView imageViewIcon;
    private TextView textViewName;
    private TextView textViewAuthor;
    private TextView textViewType;
    private TextView textViewCount;
    private ListView listViewSongs;
    private LinearLayout linearLayoutBack;

    private JSONObject jsonObject;
    private Drawable drawable;

    private PropertySongArrayAdapter propertySongArrayAdapter;

    private OnFragmentAlbumInteractionListener mListener;

    public AlbumFragment() {

    }

    public static AlbumFragment newInstance(JSONObject jsonObject, byte [] img) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_JSON, jsonObject.toString());
        args.putByteArray(ARG_IMG, img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                jsonObject = new JSONObject(getArguments().getString(ARG_JSON));
                drawable = Decoder.getDrawable(getArguments().getByteArray(ARG_IMG));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_album, container, false);
        initViews(root);

        imageViewIcon.setImageDrawable(drawable);

        try {
            textViewName.setText(jsonObject.getString("collectionName"));
            textViewAuthor.setText(jsonObject.getString("artistName"));
            textViewType.setText(jsonObject.getString("primaryGenreName")
                    + " "
                    + jsonObject.getString("releaseDate").substring(0, 4));
            textViewCount.setText("Count: " + jsonObject.getString("trackCount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        linearLayoutBack.setOnClickListener(new OnBackListener());

        propertySongArrayAdapter = new PropertySongArrayAdapter(getContext(), 0, new ArrayList<PropertySong>(), R.layout.rowlayout_songs);
        listViewSongs.setAdapter(propertySongArrayAdapter);

        try {
            new SearchSongsTask(getActivity(), propertySongArrayAdapter).execute(jsonObject.getString("collectionId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void initViews(View root) {
        imageViewIcon = root.findViewById(R.id.imageViewIcon);
        textViewName = root.findViewById(R.id.textViewName);
        textViewAuthor = root.findViewById(R.id.textViewAuthor);
        textViewType = root.findViewById(R.id.textViewType);
        textViewCount = root.findViewById(R.id.textViewCount);
        listViewSongs = root.findViewById(R.id.listViewSongs);
        linearLayoutBack = root.findViewById(R.id.linearLayoutBack);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAlbumInteractionListener) {
            mListener = (OnFragmentAlbumInteractionListener) context;
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

    public interface OnFragmentAlbumInteractionListener {
        void onFragmentAlbumInteraction();
    }

    public class OnBackListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mListener.onFragmentAlbumInteraction();
        }
    }
}
