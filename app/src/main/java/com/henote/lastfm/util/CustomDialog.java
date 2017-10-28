package com.henote.lastfm.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.TextView;

import com.henote.lastfm.R;


/**
 * Created by Naveen on 27/10/2017.
 */

public class CustomDialog {
    public CustomDialog(Context context, String Text) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder((Activity) context);
        alertDialog.setTitle(context.getResources().getString(R.string.alert_message));
        alertDialog.setMessage(Html.fromHtml("<small>" + Text + "</small>"));
        alertDialog.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
