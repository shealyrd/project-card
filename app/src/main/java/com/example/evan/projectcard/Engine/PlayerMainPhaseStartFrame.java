package com.example.evan.projectcard.Engine;

import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/28/2016.
 */
public class PlayerMainPhaseStartFrame extends Frame {
    public PlayerMainPhaseStartFrame(Game game) {
        super(game,
                "PlayerMainPhaseStart");
    }

    @Override
    public void execute() {
        final GameActivity act = (GameActivity) game.getActivity();
        act.growText(R.raw.main_phase_text, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((ImageView) act.findViewById(R.id.deck)).setImageResource(R.raw.end_main_phase_button);
                game.startNextFrame();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public Frame getNextFrame() {
        return new PlayerMainPhaseFrame(game);
    }
}
