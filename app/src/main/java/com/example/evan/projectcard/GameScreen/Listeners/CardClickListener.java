package com.example.evan.projectcard.GameScreen.Listeners;

import android.view.View;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.CardDefinition;
import com.example.evan.projectcard.GameScreen.CardType;
import com.example.evan.projectcard.GameScreen.Dialogs.CardInfoDialog;
import com.example.evan.projectcard.GameScreen.Dialogs.IdentityCardInfoDialog;
import com.example.evan.projectcard.GameScreen.Views.CardView;

/**
 * Created by Evan on 8/18/2016.
 */
public class CardClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        CardDefinition def = CardDatabase.getCardDefinition(((CardView) v).cardId);
        if(def.type == CardType.IDENTITY){
            IdentityCardInfoDialog dialog = new IdentityCardInfoDialog(v.getContext(), (CardView) v);
            dialog.init();
            dialog.show();
        }
        else{
            CardInfoDialog dialog = new CardInfoDialog(v.getContext(), (CardView) v);
            dialog.init();
            dialog.show();
        }

    }
}
