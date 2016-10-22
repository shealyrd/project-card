package com.example.evan.projectcard.GameScreen.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.evan.projectcard.GameScreen.Dialogs.CoinFlipDialog;
import com.example.evan.projectcard.GameScreen.Listeners.HeadsTailsClickListener;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.InvertColorTouchListener;

/**
 * Created by Evan on 8/23/2016.
 */
public class HeadsTailsButtonView extends ImageView{
    public CoinFlipDialog dialog;

    public HeadsTailsButtonView(Context context) {
        super(context);
    }

    public HeadsTailsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadsTailsButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListener(){
        setOnClickListener(new HeadsTailsClickListener(dialog));
        setOnTouchListener(new InvertColorTouchListener(this));
    }

    public void removeListeners(){
        setOnClickListener(null);
        setOnTouchListener(null);
    }

    public void moveToCenter(){
        if(type == HeadsOrTails.Heads){
            float parentCenterX = ((ViewGroup)getParent()).getWidth()/2;
            animate().translationX(parentCenterX - getWidth()/2).setDuration(2000);
        }
        else{

            /*

            IT CAN BE DONE. EDIT THE LAYOUT PARAMS

             */
            float parentCenterX = ((ViewGroup)getParent()).getX() - ((ViewGroup)getParent()).getWidth()/2;
            RelativeLayout.LayoutParams headsButtonParams = (RelativeLayout.LayoutParams) ((ViewGroup) getParent()).findViewById(R.id.heads_button).getLayoutParams();
            RelativeLayout.LayoutParams tailsButtonParams = (RelativeLayout.LayoutParams) getLayoutParams();
            //headsButtonParams.addRule(RelativeLayout.LEFT_OF, R.id.tails_button);
            ((ViewGroup) getParent()).findViewById(R.id.heads_button).setLayoutParams(headsButtonParams);
            //tailsButtonParams.addRule(RelativeLayout.RIGHT_OF, 0);
            RelativeLayout.LayoutParams parentParams = (RelativeLayout.LayoutParams) ((ViewGroup) getParent()).getLayoutParams();
            parentParams.addRule(RelativeLayout.LAYOUT_DIRECTION_RTL, 1);
            ((ViewGroup) getParent()).setLayoutParams(parentParams);
            setLayoutParams(tailsButtonParams);
            animate().translationX(parentCenterX + getWidth()/2).setDuration(2000);
            /*Animation anim = new TranslateAnimation(0, getWidth(), 0, 0);
            anim.setDuration(2000);
            startAnimation(anim);*/
        }
    }

    public enum HeadsOrTails{
        Heads,
        Tails
    }

    public void setDialog(CoinFlipDialog dialog){
        this.dialog = dialog;
        setListener();
    }

    public HeadsOrTails type;
}
