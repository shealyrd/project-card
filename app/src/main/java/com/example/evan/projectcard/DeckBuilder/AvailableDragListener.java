package com.example.evan.projectcard.DeckBuilder;

import android.app.Activity;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.R;

import java.util.List;

/**
 * Created by Evan on 9/10/2016.
 */
public class AvailableDragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        Activity act = (Activity) v.getContext();
        if(act.findViewById(R.id.current_list) == ((AvailableListItemView) event.getLocalState()).getParent()) {
            AvailableListItemView view = (AvailableListItemView) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    ListView list = (ListView) view.getParent();
                    CurrentAdapter adapter = (CurrentAdapter) list.getAdapter();
                    int id = adapter.getViewPosition(view);
                    adapter.decrementCount(id);
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
