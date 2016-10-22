package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.CardDefinition;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/19/2016.
 */
public class IdentityCardInfoDialog extends Dialog {
    CardView card;

    public IdentityCardInfoDialog(Context context, CardView card) {
        super(context);
        this.card = card;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.card_info_dialog_identity);
    }

    public void init(){
        ImageView img = (ImageView) findViewById(R.id.card_picture);
        img.setImageDrawable(card.getDrawable());
        CardDefinition def = CardDatabase.getCardDefinition(card.cardId);
        TextView atk_region = (TextView) findViewById(R.id.atk_region);
        TextView ap_region = (TextView) findViewById(R.id.ap_region);
        TextView hp_region = (TextView) findViewById(R.id.hp_region);
        TextView name_region = (TextView) findViewById(R.id.name_region);
        TextView info_region = (TextView) findViewById(R.id.info_region);
        TextView basic_cost =  (TextView) findViewById(R.id.basic_column_child2);
        TextView lightning_cost =  (TextView) findViewById(R.id.lightning_column_child2);
        TextView water_cost =  (TextView) findViewById(R.id.water_column_child2);
        TextView fire_cost =  (TextView) findViewById(R.id.fire_column_child2);
        basic_cost.setText("" + def.cost.getBasicCount());
        lightning_cost.setText("" + def.cost.getLightningCount());
        water_cost.setText("" + def.cost.getWaterCount());
        fire_cost.setText("" + def.cost.getFireCount());
        atk_region.setText("ATK: " + def.ATK);
        ap_region.setText("AP: " + def.AP);
        hp_region.setText("HP: " + def.HP);
        name_region.setText(def.name);
        info_region.setText(def.info);
        LinearLayout keyword_container = (LinearLayout) findViewById(R.id.keyword_container);
        for(String word: def.keywords){
            TextView newView = new TextView(getContext());
            newView.setText(word);
            newView.setPadding(10,0,10,0);
            //newView.setBackgroundColor(0xFF123456);
            newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            newView.setGravity(Gravity.CENTER);
            keyword_container.addView(newView);
        }
    }
}
