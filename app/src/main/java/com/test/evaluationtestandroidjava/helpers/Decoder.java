package com.test.evaluationtestandroidjava.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by Алексей on 25.08.2018.
 */

public class Decoder {

    public static byte[] getByteArray(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Drawable getDrawable(byte [] data) {
        return new BitmapDrawable(BitmapFactory.decodeByteArray(data, 0, data.length));
    }
}
