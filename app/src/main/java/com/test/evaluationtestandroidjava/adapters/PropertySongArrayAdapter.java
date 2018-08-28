package com.test.evaluationtestandroidjava.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.evaluationtestandroidjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 26.08.2018.
 */

public class PropertySongArrayAdapter extends ArrayAdapter<PropertySong> {
    private Context context;
    private List<PropertySong> rentalProperties;
    private int id;

    public PropertySongArrayAdapter(Context context, int resource, ArrayList<PropertySong> objects, int id) {
        super(context, resource, objects);
        this.id = id;
        this.context = context;
        this.rentalProperties = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PropertySong propertySong = rentalProperties.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(id, null);

        TextView textViewNumber = view.findViewById(R.id.textViewNumber);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTime = view.findViewById(R.id.textViewTime);

        textViewNumber.setText(propertySong.getNumber());
        textViewName.setText(propertySong.getName());
        textViewTime.setText(propertySong.getTime());

        return view;
    }

    public void addProperties(ArrayList<PropertySong> properties) {
        for (PropertySong p : properties) {
            rentalProperties.add(p);
        }
    }

    public void clear() {
        rentalProperties.clear();
    }
}
