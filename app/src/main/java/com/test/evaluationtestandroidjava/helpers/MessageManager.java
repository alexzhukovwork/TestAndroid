package com.test.evaluationtestandroidjava.helpers;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Алексей on 27.08.2018.
 */

public class MessageManager {

    public static void showToast(Context context, String str) {
        Toast toast = Toast.makeText(context,
                str,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
