package com.example.evan.projectcard.GameScreen.Listeners;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.evan.projectcard.Engine.ActivateEffectMove;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.SeizeMove;
import com.example.evan.projectcard.GameScreen.Dialogs.ChooseDirectionDialog;
import com.example.evan.projectcard.GameScreen.Dialogs.ViewConstructionsDialog;
import com.example.evan.projectcard.GameScreen.Dialogs.ViewDiscardPileDialog;
import com.example.evan.projectcard.GameScreen.Dialogs.ViewUnitDialog;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Utilities.R2Point;

import java.util.ArrayList;


/**
 * Created by Evan on 8/18/2016.
 */
public class CellClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        final CellView cell = (CellView) v;
        final String[] options = getOptions(cell);
        if(options.length > 0){
            R2Point point = cell.getPoint();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Cell (" + point.x + "," + point.y + ")")
                    .setItems(options, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch(options[which]){
                                case "Seize":
                                    Move seizeMove = new SeizeMove(cell.getCell().getIdentity().getOwner(), cell.getCell().getIdentity(), cell.getCell());
                                    seizeMove.setGame(cell.getCell().getIdentity().getGame());
                                    MoveInterpreter.launch(seizeMove);
                                    break;
                                case "Rotate Unit":
                                    ChooseDirectionDialog newDialog = new ChooseDirectionDialog(new CardView(cell.getContext(), cell.getCell().getIdentity()), cell);
                                    newDialog.forRotation = true;
                                    newDialog.init();
                                    newDialog.show();
                                    break;
                                case "View Unit":
                                    ViewUnitDialog viewUnitDialog = new ViewUnitDialog(cell.getContext(), cell.getCell().getIdentity());
                                    viewUnitDialog.activity = (GameActivity) cell.getContext();
                                    viewUnitDialog.init();
                                    viewUnitDialog.show();
                                    viewUnitDialog.fixDim();
                                    break;
                                case "View Constructions":
                                    ViewConstructionsDialog viewConstructionsDialog = new ViewConstructionsDialog(cell.getContext(), cell);
                                    viewConstructionsDialog.init();
                                    viewConstructionsDialog.show();
                                    viewConstructionsDialog.fixDim();
                                    break;
                                case "View Discard Pile":
                                    ViewDiscardPileDialog viewDiscardPileDialog = new ViewDiscardPileDialog(cell.getContext(), cell);
                                    viewDiscardPileDialog.init();
                                    viewDiscardPileDialog.show();
                                    viewDiscardPileDialog.fixDim();
                                    break;
                                case "Activate Effect":
                                    Move move = new ActivateEffectMove(cell.getCell().getIdentity().getOwner(), cell.getCell().getIdentity());
                                    move.setGame(cell.getCell().getOwner().getGame());
                                    MoveInterpreter.launch(move);
                                    break;
                            }
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private String[] getOptions(CellView cell){
        ArrayList<String> options = new ArrayList<String>();
        if(cell.getCell().hasUnit()){
            if(cell.getCell().getIdentity().getOwner() == cell.getCell().getIdentity().getOwner().getGame().players[0]){
                if(cell.getCell().canBeSeizedBy(cell.getCell().getIdentity())){
                    options.add("Seize");
                }
                if(cell.getCell().getIdentity().canActivate()){
                    options.add("Activate effect");
                }
                options.add("Rotate Unit");
            }
            options.add("View Unit");
        }
        if(cell.getCell().getConstructions().size() > 0){
            options.add("View Constructions");
        }
        if(cell.getCell().getDiscardPile().size() > 0){
            options.add("View Discard Pile");
        }
        String[] strings = new String[options.size()];
        options.toArray(strings);
        return strings;
    }
}
