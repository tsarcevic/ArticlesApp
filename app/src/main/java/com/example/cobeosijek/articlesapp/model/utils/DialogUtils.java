package com.example.cobeosijek.articlesapp.model.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.listeners.DeleteListener;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class DialogUtils {

    public static void showDialog(Context from, final int articleId, final DeleteListener listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(from);

        alertDialog.setMessage(R.string.alert_dialog_text)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDeleteClicked(articleId);
                    }
                });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertShowDialog = alertDialog.create();
        alertShowDialog.show();
    }
}
