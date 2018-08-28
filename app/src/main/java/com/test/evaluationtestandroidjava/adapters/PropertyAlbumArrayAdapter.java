package com.test.evaluationtestandroidjava.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.evaluationtestandroidjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 22.08.2018.
 */

public class PropertyAlbumArrayAdapter extends ArrayAdapter<PropertyAlbum> {

    private Context context;
    private List<PropertyAlbum> rentalProperties;
    private int id;

    public PropertyAlbumArrayAdapter(Context context, int resource, ArrayList<PropertyAlbum> objects, int id) {
        super(context, resource, objects);
        this.id = id;
        this.context = context;
        this.rentalProperties = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PropertyAlbum propertyAlbum = rentalProperties.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(id, null);

        TextView textViewItem = view.findViewById(R.id.textViewItem);
        ImageView image = view.findViewById(R.id.iconItem);

        textViewItem.setText(propertyAlbum.getLabel() == null ? "" : propertyAlbum.getLabel());


        if (propertyAlbum.getDrawable() != null) {
            image.setImageDrawable(propertyAlbum.getDrawable());
        }

        return view;
    }

    public void addProperties(ArrayList<PropertyAlbum> properties) {
        for (PropertyAlbum p : properties) {
            rentalProperties.add(p);
        }
    }

    public PropertyAlbum getAt(int i) {
        return rentalProperties.get(i);
    }

    public void clear() {
        rentalProperties.clear();
    }
}