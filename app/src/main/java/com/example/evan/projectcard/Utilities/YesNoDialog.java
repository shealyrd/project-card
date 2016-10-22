package com.example.evan.projectcard.Utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;

/**
 * Created by Evan on 8/28/2016.
 */
public class YesNoDialog {
    BasicListener onYes;
    BasicListener onNo;
    String text;
    Context context;

    public YesNoDialog(Context context, String text){
        this.context = context;
        this.text = text;
    }

    public void setOnYesListener(BasicListener listener){
        onYes = listener;
    }

    public void setOnNoListener(BasicListener listener){
        onNo = listener;
    }

    public void show(){
        final BasicListener onYesF = onYes;
        final BasicListener onNoF = onNo;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        if(onYesF != null){
                            onYesF.onEvent();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        if(onNoF != null){
                            onNoF.onEvent();
                        }
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
