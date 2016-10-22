package com.example.evan.projectcard.GameScreen.Listeners;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;

/**
 * Created by Evan on 8/16/2016.
 */
public class DragOntoCellListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        Log.d("DragOntoCellListener", "onDrag");
        if(event.getLocalState() instanceof CardView){
            new DragOntoCellFromHandListener().onDrag(v, event);
        }
        if(event.getLocalState() instanceof CellView){
            new DragOntoCellFromCellListener().onDrag(v, event);
        }
        return true;
    }
}
