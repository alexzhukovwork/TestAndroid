package com.test.evaluationtestandroidjava.adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by Алексей on 22.08.2018.
 */

public class PropertyAlbum {

    private String label;
    private Drawable drawable;

    public PropertyAlbum(String label, Drawable drawable) {
        this.label = label;
        this.drawable = drawable;
    }

    public String getLabel() { return label; }

    public Drawable getDrawable() { return drawable; }
}
