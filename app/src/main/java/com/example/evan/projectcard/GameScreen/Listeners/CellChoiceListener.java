package com.example.evan.projectcard.GameScreen.Listeners;

import android.view.View;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Utilities.YesNoDialog;

/**
 * Created by Evan on 8/30/2016.
 */
public class CellChoiceListener implements View.OnClickListener {
    BasicListener listener;

    public CellChoiceListener(BasicListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        final CellView cell = (CellView) v;
        YesNoDialog dialog = new YesNoDialog(v.getContext(), "Choose cell (" + cell.getPoint().x + ", " + cell.getPoint().y + ")?");
        dialog.setOnYesListener(new BasicListener() {
            @Override
            public void onEvent() {
                ((CarrierListener) listener).setObject(cell.getCell());
                listener.onEvent();
            }
        });
        dialog.show();
    }
}
