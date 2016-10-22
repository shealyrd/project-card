package com.example.evan.projectcard.DeckBuilder;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evan.projectcard.R;

/**
 * Created by Evan on 9/6/2016.
 */
public class AvailableListItemView extends FrameLayout {
    ImageView cardImg;
    TextView cardName;
    PercentRelativeLayout base;

    public AvailableListItemView(Context context) {
        super(context);
        init();
    }

    public AvailableListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvailableListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ImageView getCardImg() {
        return cardImg;
    }

    public void setCardImg(ImageView cardImg) {
        this.cardImg = cardImg;
    }

    public TextView getCardName() {
        return cardName;
    }

    public void setCardName(TextView cardName) {
        this.cardName = cardName;
    }

    private void init() {
        View view = inflate(getContext(), R.layout.available_list_item, null);
        addView(view);
        setVariables();
    }

    private void setVariables(){
        cardImg = (ImageView) findViewById(R.id.card_img);
        cardName = (TextView) findViewById(R.id.card_name);
        base = (PercentRelativeLayout) findViewById(R.id.base);
    }


}
