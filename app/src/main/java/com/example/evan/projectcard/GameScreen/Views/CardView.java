package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.Listeners.CardClickListener;
import com.example.evan.projectcard.GameScreen.Listeners.CardLongClickListener;

/**
 * Created by Evan on 8/16/2016.
 */
public class CardView extends ImageView {
    public int cardId;
    public Card constituent;

    public CardView(Context context, Card constituent) {
        super(context);
        this.constituent = constituent;
        this.cardId = CardEngineLookup.getIDFromCard(constituent);
        setDrawableByID();
    }


    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawableByID(){
        setImageDrawable(getResources().getDrawable(CardDatabase.getDrawableID(cardId)));
    }

    public void setListeners() {
        setOnLongClickListener(new CardLongClickListener());
        setOnClickListener(new CardClickListener());
    }

    public void removeListeners() {
        setOnLongClickListener(null);
        setOnClickListener(null);
    }

    public Card getCard(){
        return constituent;
    }
}
