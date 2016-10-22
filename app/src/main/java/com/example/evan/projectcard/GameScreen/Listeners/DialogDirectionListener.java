package com.example.evan.projectcard.GameScreen.Listeners;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.projectcard.Engine.ConstructionCard;
import com.example.evan.projectcard.Engine.IdentityCard;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.MoveCardMove;
import com.example.evan.projectcard.Engine.PlayConstructionMove;
import com.example.evan.projectcard.Engine.PlayUnitMove;
import com.example.evan.projectcard.Engine.RotateUnitMove;
import com.example.evan.projectcard.GameScreen.FrontEndDirection;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.GameScreen.Dialogs.ChooseDirectionDialog;
/**
 * Created by Evan on 8/17/2016.
 */
public class DialogDirectionListener implements View.OnClickListener{
    FrontEndDirection toSet;
    ImageView img;
    CellView cell;
    ChooseDirectionDialog dialog;
    CellView cellFrom;


    public DialogDirectionListener(FrontEndDirection toSet, CellView cell, CellView cellFrom, ChooseDirectionDialog dialog){
        this.cell = cell;
        this.dialog = dialog;
        this.toSet = toSet;
        this.cellFrom = cellFrom;
    }

    @Override
    public void onClick(View v) {
        Log.d("DialogClick", "" + dialog.assignmentId);
        img = (ImageView) v;
        Drawable draw = img.getDrawable();
        cell.drawVisibleCard(draw, toSet);
        cell.visibleCardId = dialog.assignmentId;
        cell.direction = toSet;
        GameActivity activity = (GameActivity) cell.getContext();
        if(dialog.forRotation){
            Move move = new RotateUnitMove(cell.getCell().getIdentity().getOwner(), cell.getCell().getIdentity(), toSet.toBackEndDirection());
            move.setGame(activity.game);
            MoveInterpreter.launch(move);
        }
        else{
            if(cellFrom != null){
                //activity.game.log("is null");
                cellFrom.visibleCardId = 0;
                cellFrom.direction = FrontEndDirection.None;
                Move move = null;
                move = new MoveCardMove(activity.game.currentPlayer, cellFrom.getCell().getIdentity(), cell.getCell(), toSet.toBackEndDirection());
                move.setGame(activity.game);
                MoveInterpreter.launch(move);
            }
            else{
                Move move = null;
                if(dialog.cardInHand instanceof IdentityCard){
                    move = new PlayUnitMove(activity.game.currentPlayer, dialog.cardInHand, cell.getCell(), toSet.toBackEndDirection());
                }
                if(dialog.cardInHand instanceof ConstructionCard){
                    move = new PlayConstructionMove(activity.game.currentPlayer, dialog.cardInHand, cell.getCell(), toSet.toBackEndDirection());
                }
                move.setGame(activity.game);
                MoveInterpreter.launch(move);
            }
        }
        dialog.choiceMade = true;
        dialog.dismiss();
    }


}
