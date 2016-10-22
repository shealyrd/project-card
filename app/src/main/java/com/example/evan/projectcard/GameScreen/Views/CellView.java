package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.evan.projectcard.Engine.Algorithmics;
import com.example.evan.projectcard.Engine.CardCollection;
import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.Engine.Cell;
import com.example.evan.projectcard.Engine.ConstructionCard;
import com.example.evan.projectcard.Engine.Player;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.CellAlignment;
import com.example.evan.projectcard.GameScreen.FrontEndDirection;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.Listeners.CellChoiceListener;
import com.example.evan.projectcard.GameScreen.Listeners.CellClickListener;
import com.example.evan.projectcard.GameScreen.Listeners.CellLongClickListener;
import com.example.evan.projectcard.GameScreen.Listeners.DragOntoCellListener;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.R2Point;
import com.example.evan.projectcard.Utilities.ViewUtils;

/**
 * Created by Evan on 8/16/2016.
 */
public class CellView extends ImageView {
    public CellAlignment alignment;
    public FrontEndDirection direction = FrontEndDirection.None;
    public Drawable cellDraw = getDrawable();
    public int visibleCardId = 0;
    public int index = 0;

    public CellView(Context context) {
        super(context);
    }

    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Drawable getCardDrawable(){
        Log.d("getCardDrawable", "ID: " + visibleCardId);
        return getResources().getDrawable(CardDatabase.getDrawableID(visibleCardId));
    }

    public void setListeners(){
        setOnDragListener(new DragOntoCellListener());
        setOnClickListener(new CellClickListener());
        setOnLongClickListener(new CellLongClickListener());
    }

    public void drawEmpty() {
        setImageDrawable(cellDraw);
    }

    public void drawVisibleCard() {
        drawVisibleCard(getCardDrawable(), direction);
    }

    public void drawVisibleCard(Drawable card, FrontEndDirection dir) {
        Resources r = getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = cellDraw;
        Bitmap bmResult = ViewUtils.drawableToBitmap(card.getConstantState().newDrawable());
        Drawable drawable = ViewUtils.getRotateDrawable(getContext(), bmResult, dir.getDegree());
        layers[1] = drawable;
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        int[] offsets = dir.offsets;
        layerDrawable.setLayerInset(1, offsets[0], offsets[1], offsets[2], offsets[3]);
        setImageDrawable(layerDrawable);
    }

    public void removeUnitImage(){
        CardCollection constructions = getCell().getConstructions();
        if(constructions.size() == 0){
            drawEmpty();
        }
        else{
            ConstructionCard card = (ConstructionCard) constructions.getAtIndex(0);
            int id = CardDatabase.getDrawableFromCard(card);
            drawVisibleCard(getResources().getDrawable(id), card.getDirection().toFrontEndDirection());
        }
    }

    public void removeListeners() {
        setOnDragListener(null);
        setOnClickListener(null);
        setOnLongClickListener(null);
    }

    public R2Point getPoint(){
        int x = Algorithmics.getRow(index);
        int y = Algorithmics.getColumn(index);
        R2Point result = new R2Point();
        result.x = x;
        result.y = y;
        return result;
    }

    public Cell getCell() {
        return ((GameActivity) getContext()).game.board.getCellAtIndex(index);
    }

    public void changeAlignment() {
        if(alignment == CellAlignment.Green){
            alignment = CellAlignment.Red;
            cellDraw = getResources().getDrawable(R.raw.red_cell);
        }
        else{
            //Log.d("CellView", alignment.toString());
            alignment = CellAlignment.Green;
            cellDraw = getResources().getDrawable(R.raw.green_cell);
        }
        drawVisibleCard();
    }

    public void refreshView() {
        Player owner = getCell().getOwner();
        if(owner == getCell().getOwner().getGame().players[0]){
            alignment = CellAlignment.Green;
            cellDraw = getResources().getDrawable(R.raw.green_cell);
        }
        else{
            alignment = CellAlignment.Red;
            cellDraw = getResources().getDrawable(R.raw.red_cell);
        }
        if(getCell().getConstructions().size() > 0){
            direction = ((ConstructionCard) getCell().getConstructions().getAtIndex(0)).getDirection().toFrontEndDirection();
        }
        if(getCell().hasUnit()){
            direction = getCell().getIdentity().getDirection().toFrontEndDirection();
            Log.d("hasUnit", "ID: " + visibleCardId);
            visibleCardId = CardEngineLookup.getIDFromCard(getCell().getIdentity());
            Log.d("hasUnit", "ID: " + visibleCardId);
        }
        else if(getCell().getConstructions().size() > 0){
            Log.d("getConstructions", "ID: " + visibleCardId);
            visibleCardId = CardEngineLookup.getIDFromCard(getCell().getConstructions().getAtIndex(0));
        }
        if(getCell().hasUnit() || getCell().getConstructions().size() != 0) {
            drawVisibleCard();
        }
        else{
            removeUnitImage();
        }
    }

    public void setChoiceListener(BasicListener listener) {
        setOnClickListener(new CellChoiceListener(listener));
    }

}
