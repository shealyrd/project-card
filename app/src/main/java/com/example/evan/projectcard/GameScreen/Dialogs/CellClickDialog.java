package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Evan on 8/18/2016.
 */
public class CellClickDialog extends AlertDialog {
    public CellClickDialog(Context context) {
        super(context);
    }

    public CellClickDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CellClickDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
