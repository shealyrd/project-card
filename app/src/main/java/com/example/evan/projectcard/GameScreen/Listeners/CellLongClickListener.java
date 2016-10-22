package com.example.evan.projectcard.GameScreen.Listeners;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.Engine.IdentityCard;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Utilities.ImageDragShadowBuilder;

/**
 * Created by Evan on 8/18/2016.
 */
public class CellLongClickListener implements View.OnLongClickListener {

    @Override
    public boolean onLongClick(View v) {
        CellView clickedCell = (CellView) v;
        if(clickedCell.getCell().hasUnit() || clickedCell.getCell().getConstructions().size() != 0){
            if(CardEngineLookup.getCardByID(clickedCell.visibleCardId) instanceof IdentityCard){
            if(clickedCell.getCell().getIdentity().getOwner() == clickedCell.getCell().getOwner().getGame().players[0]){
                    clickedCell.removeUnitImage();
                    Log.d("Tag", "onTouch");
                    Drawable shadow = clickedCell.getCardDrawable();
                    Activity activity = (Activity) v.getContext();
                    View.DragShadowBuilder shadowBuilder = ImageDragShadowBuilder.fromResource(activity, shadow);
                    v.startDrag(null, shadowBuilder, clickedCell, 0);
                    return true;
                }
            }
        }
        return false;
    }
}
