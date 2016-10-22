package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.FrontEndDirection;
import com.example.evan.projectcard.GameScreen.Listeners.DialogDirectionListener;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/17/2016.
 */
public class ChooseDirectionDialog extends Dialog{
    public Drawable cardDrawable;
    public CellView cell;
    public int assignmentId;
    public CellView cellFrom;
    public boolean choiceMade;
    public Card cardInHand;
    public boolean forRotation = false;


    public ChooseDirectionDialog(CellView cell){
        super(cell.getContext());
        Card c = cell.getCell().getIdentity();
        this.assignmentId = cell.visibleCardId;
        this.cardDrawable = cell.getCardDrawable();
        this.cell = cell;
        forRotation = true;
    }

    public ChooseDirectionDialog(CardView card, CellView cell){
        super(cell.getContext());
        this.cardDrawable = card.getDrawable();
        this.assignmentId = card.cardId;
        this.cell = cell;
        this.cardInHand = card.getCard();
    }

    public ChooseDirectionDialog(CellView cellFrom, CellView cellTo){
        super(cellFrom.getContext());
        this.cardDrawable = cellFrom.getCardDrawable();
        this.assignmentId = cellFrom.visibleCardId;
        this.cell = cellTo;
        this.cellFrom = cellFrom;
    }

    public ChooseDirectionDialog(Context context) {
        super(context);
    }

    public ChooseDirectionDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChooseDirectionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(){
        setContentView(R.layout.custom_dialog);
        ImageView image_up = (ImageView) findViewById(R.id.image_up);
        image_up.setImageDrawable(cardDrawable);
        image_up.setOnClickListener(new DialogDirectionListener(FrontEndDirection.Up, cell, cellFrom, this));

        ImageView image_left = (ImageView) findViewById(R.id.image_left);
        image_left.setImageDrawable(cardDrawable);
        image_left.setRotation(270);
        image_left.setOnClickListener(new DialogDirectionListener(FrontEndDirection.Left, cell, cellFrom, this));

        ImageView image_right = (ImageView) findViewById(R.id.image_right);
        image_right.setImageDrawable(cardDrawable);
        image_right.setRotation(90);
        image_right.setOnClickListener(new DialogDirectionListener(FrontEndDirection.Right, cell, cellFrom, this));

        ImageView image_down = (ImageView) findViewById(R.id.image_down);
        image_down.setImageDrawable(cardDrawable);
        image_down.setRotation(180);
        image_down.setOnClickListener(new DialogDirectionListener(FrontEndDirection.Down, cell, cellFrom, this));

        setTitle("Choose Direction");
    }

    @Override
    public void dismiss(){
        if(cellFrom != null) {
            if(!choiceMade){
                cellFrom.drawVisibleCard();
            }
        }
        super.dismiss();
    }
    public Drawable getCardDrawable(){
        return cardDrawable;
    }

}
