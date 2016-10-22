package com.example.evan.projectcard.GameScreen.Listeners;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.evan.projectcard.Audio.Sounds;
import com.example.evan.projectcard.Engine.CraftCard;
import com.example.evan.projectcard.Engine.ItemCard;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.PersistentCard;
import com.example.evan.projectcard.Engine.PlayCraftMove;
import com.example.evan.projectcard.Engine.PlayItemMove;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.GameScreen.Dialogs.ChooseDirectionDialog;
import com.example.evan.projectcard.Utilities.YesNoDialog;

/**
 * Created by Evan on 8/18/2016.
 */
public class DragOntoCellFromHandListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        final CardView card = (CardView) event.getLocalState();
        if(card == null){
            Log.d("DragOntoCellListener", "null");
        }
        final CellView cell = (CellView) v;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                Log.d("DragOntoCellListener", cell.index + "");
                if(card.getCard() instanceof PersistentCard){
                    if(cell.getCell().getOwner() == cell.getCell().getOwner().getGame().currentPlayer){
                        ChooseDirectionDialog dialog = new ChooseDirectionDialog(card, cell);
                        dialog.init();
                        dialog.show();
                    }
                    else{
                        Toast.makeText(cell.getContext(), "You do not own this cell.", Toast.LENGTH_LONG).show();
                        card.setVisibility(View.VISIBLE);
                    }
                }
                if(card.getCard() instanceof CraftCard){
                    if(cell.getCell().hasUnit()) {
                        String text = "Use " + card.getCard().getName() + " with " + cell.getCell().getIdentity().getName() + "?";
                        YesNoDialog dialog = new YesNoDialog(cell.getContext(), text);
                        dialog.setOnYesListener(new BasicListener() {
                            @Override
                            public void onEvent() {
                                Move move = new PlayCraftMove(((GameActivity) card.getContext()).game.currentPlayer, card.getCard(), cell.getCell().getIdentity());
                                move.setGame(((GameActivity) card.getContext()).game);
                                MoveInterpreter.launch(move);
                                ((LinearLayout) card.getParent()).removeView(card);
                            }
                        });
                        dialog.setOnNoListener(new BasicListener() {
                            @Override
                            public void onEvent() {
                                card.setVisibility(View.VISIBLE);
                            }
                        });
                        dialog.show();
                    }
                    else{
                        card.setVisibility(View.VISIBLE);
                    }
                }
                if(card.getCard() instanceof ItemCard){
                    if(cell.getCell().hasUnit()) {
                        String text = "Attach " + card.getCard().getName() + " to " + cell.getCell().getIdentity().getName() + "?";
                        YesNoDialog dialog = new YesNoDialog(cell.getContext(), text);
                        dialog.setOnYesListener(new BasicListener() {
                            @Override
                            public void onEvent() {
                                Move move = new PlayItemMove(((GameActivity) card.getContext()).game.currentPlayer, card.getCard(), cell.getCell().getIdentity());
                                move.setGame(((GameActivity) card.getContext()).game);
                                MoveInterpreter.launch(move);
                                ((LinearLayout) card.getParent()).removeView(card);
                            }
                        });
                        dialog.setOnNoListener(new BasicListener() {
                            @Override
                            public void onEvent() {
                                card.setVisibility(View.VISIBLE);
                            }
                        });
                        dialog.show();
                    }
                    else{
                        card.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(dropEventNotHandled(event)) {
                    ((GameActivity) v.getContext()).playSound(Sounds.ERROR);
                    card.setVisibility(View.VISIBLE);
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
