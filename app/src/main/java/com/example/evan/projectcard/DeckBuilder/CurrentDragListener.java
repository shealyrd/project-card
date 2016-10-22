package com.example.evan.projectcard.DeckBuilder;

import android.app.Activity;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 9/8/2016.
 */
public class CurrentDragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        ListView list = (ListView) v;
        Activity act = (Activity) v.getContext();
        if(act.findViewById(R.id.current_list) == list) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    TextView txt = (TextView) ((View) event.getLocalState()).findViewById(R.id.card_name);
                    String name = txt.getText().toString();
                    int id = CardDatabase.getIDFromName(name);
                    ((CurrentAdapter) list.getAdapter()).incrementCount(id - 1);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
