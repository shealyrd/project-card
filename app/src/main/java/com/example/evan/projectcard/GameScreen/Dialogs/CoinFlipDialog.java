package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Movie;
import android.os.Handler;
import android.support.percent.PercentRelativeLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.Listeners.CoinFlipAnimationFactory;
import com.example.evan.projectcard.GameScreen.Views.CoinFlipView;
import com.example.evan.projectcard.GameScreen.Views.HeadsTailsButtonView;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.SimpleGestureFilter;

import java.util.Random;

/**
 * Created by Evan on 8/22/2016.
 */
public class CoinFlipDialog extends Dialog {
    private SimpleGestureFilter detector;
    public HeadsTailsButtonView.HeadsOrTails choice;
    public CoinFlipListener flipListener;
    public BasicListener onDismissListener;

    public CoinFlipDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coin_flip_dialog);
        setGestureListener();
    }

    public void setCoinFlipListener(CoinFlipListener flipListener){
        this.flipListener = flipListener;
    }

    public void setOnDismissListener(BasicListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }

    private void setGestureListener() {
        PercentRelativeLayout coinArea = (PercentRelativeLayout) findViewById(R.id.flip_container);
        final CoinFlipView coinView = (CoinFlipView) findViewById(R.id.coin_flip);
        detector = new SimpleGestureFilter(getContext(), new SimpleGestureFilter.SimpleGestureListener() {
            @Override
            public void onSwipe(int direction) {
                switch (direction) {

                    case SimpleGestureFilter.SWIPE_RIGHT :
                        break;
                    case SimpleGestureFilter.SWIPE_LEFT :
                        break;
                    case SimpleGestureFilter.SWIPE_DOWN :
                        break;
                    case SimpleGestureFilter.SWIPE_UP :
                        if (coinView.canFlip){
                            coinView.startAnimation(CoinFlipAnimationFactory.create(coinView));
                            ImageView arrowImg = (ImageView) findViewById(R.id.coin_flip_arrow);
                            arrowImg.clearAnimation();
                            arrowImg.setVisibility(View.GONE);
                        }
                        break;

                }
            }

            @Override
            public void onDoubleTap() {
                Toast.makeText(getContext(), "Double Tap", Toast.LENGTH_SHORT).show();
            }
        });
        coinArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
    }


    @Override
    public void dismiss(){
        onDismissListener.onEvent();
        super.dismiss();
    }

    public CoinFlipDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.coin_flip_dialog);
    }

    protected CoinFlipDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setContentView(R.layout.coin_flip_dialog);
    }


    public void makeDecision(){
        Random random = new Random();
        boolean facingHeads = (random.nextInt() % 2 == 0) ? true : false;
        HeadsTailsButtonView.HeadsOrTails result;
        CoinFlipView flipView = (CoinFlipView) findViewById(R.id.coin_flip);
        ImageView resultTxt = (ImageView) findViewById(R.id.result_text);
        if(facingHeads){
            flipView.movie= Movie.decodeStream(getContext().getResources().openRawResource(R.raw.heads_gif));
            result = HeadsTailsButtonView.HeadsOrTails.Heads;
        }
        else{
            flipView.movie= Movie.decodeStream(getContext().getResources().openRawResource(R.raw.tails_gif));
            result = HeadsTailsButtonView.HeadsOrTails.Tails;
        }
        flipView.canFlip = false;
        if(choice == result){
            resultTxt.setImageResource(R.raw.go_first_text);
            flipListener.flipEvent(true);
        }
        else{
            resultTxt.setImageResource(R.raw.go_second_text);
            flipListener.flipEvent(false);
        }
        resultTxt.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 2000);
    }

    public void initialize(){
        CoinFlipView flipView = (CoinFlipView) findViewById(R.id.coin_flip);

        HeadsTailsButtonView headsView = (HeadsTailsButtonView) findViewById(R.id.heads_button);
        headsView.type = HeadsTailsButtonView.HeadsOrTails.Heads;
        headsView.setDialog(this);

        HeadsTailsButtonView tailsView = (HeadsTailsButtonView) findViewById(R.id.tails_button);
        tailsView.type = HeadsTailsButtonView.HeadsOrTails.Tails;
        tailsView.setDialog(this);
        flipView.dialog = this;
    }

}
