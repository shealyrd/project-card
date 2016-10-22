package com.example.evan.projectcard.GameScreen.Listeners;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import com.example.evan.projectcard.Audio.Sounds;
import com.example.evan.projectcard.Engine.AttackMove;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.GameScreen.Dialogs.ChooseDirectionDialog;
import com.example.evan.projectcard.Utilities.YesNoDialog;

/**
 * Created by Evan on 8/18/2016.
 */
public class DragOntoCellFromCellListener implements View.OnDragListener{

    @Override
    public boolean onDrag(View v, DragEvent event) {
        final CellView cellFrom = (CellView) event.getLocalState();
        if(cellFrom == null){
            Log.d("DragOntoCellListener", "null");
        }
        final CellView cellTo = (CellView) v;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                if(cellTo.equals(cellFrom)){
                    cellFrom.drawVisibleCard();
                }
                else{
                    Log.d("DragOntoCellListener", "onDrag.OnDrop");
                    if(!cellTo.getCell().hasUnit()){
                        if(cellFrom.getCell().getIdentity().getCurrentAP() > 0){
                            ChooseDirectionDialog dialog = new ChooseDirectionDialog(cellFrom, cellTo);
                            dialog.init();
                            dialog.show();
                        }
                        else{
                            Toast.makeText(cellTo.getContext(), "Not enough AP to move.", Toast.LENGTH_LONG).show();
                            cellFrom.drawVisibleCard();
                        }
                    }
                    else{
                        cellFrom.drawVisibleCard();
                        if(!(cellFrom.getCell().getIdentity().getCurrentAP() > 0)){
                            Toast.makeText(cellTo.getContext(), "Not enough AP to attack.", Toast.LENGTH_LONG).show();
                        }
                            else{
                            if(cellTo.getCell().getIdentity().getOwner() == cellFrom.getCell().getIdentity().getOwner()){
                                Toast.makeText(cellTo.getContext(), "You can't attack your own units.", Toast.LENGTH_LONG).show();
                                cellFrom.drawVisibleCard();
                            }
                            else{
                                String oppName = cellTo.getCell().getIdentity().getName();
                                String yourName = cellFrom.getCell().getIdentity().getName();
                                YesNoDialog dialog = new YesNoDialog(cellTo.getContext(), "Attack "+ oppName +" with "+ yourName+"?");
                                dialog.setOnYesListener(new BasicListener() {
                                    @Override
                                    public void onEvent() {
                                        AttackMove move = new AttackMove(cellFrom.getCell().getIdentity().getOwner(), cellFrom.getCell().getIdentity(), cellTo.getCell().getIdentity());
                                        move.setGame(cellFrom.getCell().getIdentity().getGame());
                                        MoveInterpreter.launch(move);
                                    }
                                });
                                dialog.setOnNoListener(new BasicListener() {
                                    @Override
                                    public void onEvent() {
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(dropEventNotHandled(event)) {
                    ((GameActivity) v.getContext()).playSound(Sounds.ERROR);
                    cellFrom.drawVisibleCard();
                }
                break;
            default:
                break;
        }
        return true;
    }
    private boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();

    }
}

