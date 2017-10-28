package com.henote.lastfm.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.henote.lastfm.R;


/**
 * Created by Naveen on 27/10/2017.
 */

public class PG {
    public static ProgressDialog showProgressDialog(Context context) {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getResources().getString(R.string.loading_text));
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    public static void hideProgressDialog(ProgressDialog pDialog) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
