package com.example.evan.projectcard.GameScreen.Listeners;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.Dialogs.CoinFlipDialog;
import com.example.evan.projectcard.GameScreen.Views.CoinFlipView;
import com.example.evan.projectcard.GameScreen.Views.HeadsTailsButtonView;
import com.example.evan.projectcard.MainScreen.FadeOutAnimation;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/23/2016.
 */
public class HeadsTailsClickListener implements View.OnClickListener {
    CoinFlipDialog dialog;

    public HeadsTailsClickListener(CoinFlipDialog dialog){
        if(dialog == null){
            Log.d("asdjsda", "DIALOG IS SOMEHOW NULL");
        }
        this.dialog = dialog;
    }

    @Override
    public void onClick(View v) {
        FadeOutAnimation animButton;
        FadeOutAnimation animText;
        switch(((HeadsTailsButtonView) v).type){
            case Heads:
                HeadsTailsButtonView tailsView = (HeadsTailsButtonView) dialog.findViewById(R.id.tails_button);
                animButton = new FadeOutAnimation(1.0f, 0.0f, tailsView);
                tailsView.startAnimation(animButton);
                ((HeadsTailsButtonView) v).moveToCenter();
                dialog.choice = HeadsTailsButtonView.HeadsOrTails.Heads;
                break;
            case Tails:
                   HeadsTailsButtonView headsView = (HeadsTailsButtonView) dialog.findViewById(R.id.heads_button);
                    animButton = new FadeOutAnimation(1.0f, 0.0f, headsView);
                    headsView.startAnimation(animButton);
                    ((HeadsTailsButtonView) v).moveToCenter();
                    dialog.choice = HeadsTailsButtonView.HeadsOrTails.Tails;
                break;
            default:
                break;
        }
        ((HeadsTailsButtonView) v).removeListeners();
        ImageView topImg = (ImageView) dialog.findViewById(R.id.top_text);
        animText = new FadeOutAnimation(1.0f, 0.0f, topImg);
        topImg.startAnimation(animText);
        CoinFlipView coinView = (CoinFlipView) dialog.findViewById(R.id.coin_flip);
        coinView.canFlip = true;
        ImageView arrowImg = (ImageView) dialog.findViewById(R.id.coin_flip_arrow);
        arrowImg.setVisibility(View.VISIBLE);
        Animation blinkingAnim = new AlphaAnimation(0, 1);
        blinkingAnim.setRepeatMode(Animation.REVERSE);
        blinkingAnim.setRepeatCount(Animation.INFINITE);
        blinkingAnim.setDuration(1000);
        arrowImg.startAnimation(blinkingAnim);
    }
}
