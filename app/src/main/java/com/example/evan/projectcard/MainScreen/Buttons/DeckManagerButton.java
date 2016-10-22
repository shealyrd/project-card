package com.example.evan.projectcard.MainScreen.Buttons;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.evan.projectcard.DeckBuilder.DeckEditorActivity;

/**
 * Created by Evan on 8/4/2016.
 */
public class DeckManagerButton extends MainScreenButton {
    String displayText = "Deck Manager";

    public DeckManagerButton(Context context) {
        super(context);
    }

    public DeckManagerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeckManagerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValues(){
        setText("Deck Manager");
        setOnClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dffd", "OnClicked");
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG);
                Intent intent = new Intent(getContext(), DeckEditorActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
