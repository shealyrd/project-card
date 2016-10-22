package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.CardEngineLookup;
import com.example.evan.projectcard.Engine.IdentityCard;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/30/2016.
 */
public class ViewUnitDialog extends Dialog{
    public IdentityCard card;
    public GameActivity activity;

    public ViewUnitDialog(Context context, IdentityCard card){
        super(context);
        this.card = card;
    }

    public ViewUnitDialog(Context context) {
        super(context);
    }

    public ViewUnitDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ViewUnitDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_unit_dialog);

        ImageView cardImg = (ImageView) findViewById(R.id.cardImg);
        TextView hpView = (TextView) findViewById(R.id.hpView);
        TextView apView = (TextView) findViewById(R.id.apView);
        TextView atkView = (TextView) findViewById(R.id.atkView);
        TextView infoView = (TextView) findViewById(R.id.midText);
        LinearLayout itemContainer = (LinearLayout) findViewById(R.id.itemContainer);

        cardImg.setImageResource(CardDatabase.getDrawableFromCard(card));
        hpView.setText("HP: " + card.getCurrentHP());
        apView.setText("AP: " + card.getCurrentAP());
        atkView.setText("ATK: " + card.getCurrentATK());
        infoView.setText(CardDatabase.getCardDefinition(CardEngineLookup.getIDFromCard(card)).info);
        for(Card c: card.getItems()){
            CardView newItem = new CardView(activity, c);
            newItem.setScaleType(ImageView.ScaleType.FIT_XY);
            newItem.setScaleX(0.5f);
            newItem.setScaleY(0.5f);
            itemContainer.addView(newItem);
        }
    }

    public void fixDim(){
        float height = activity.getWindowManager().getDefaultDisplay().getHeight()/1.7f;
        float width = activity.getWindowManager().getDefaultDisplay().getWidth()/1.7f;
        getWindow().setLayout((int) height, (int) width);
    }
}
