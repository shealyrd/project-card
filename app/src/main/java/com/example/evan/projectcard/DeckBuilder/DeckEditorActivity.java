package com.example.evan.projectcard.DeckBuilder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evan.projectcard.Utilities.DeckFileHandler;
import com.example.evan.projectcard.Utilities.DeckRepresentation;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.InvertColorTouchListener;

/**
 * Created by Evan on 9/5/2016.
 */
public class DeckEditorActivity extends Activity {
    ListView available_list;
    PercentRelativeLayout available_area;
    PercentRelativeLayout bottom_area;
    PercentRelativeLayout top_area;
    PercentRelativeLayout current_area;
    ListView current_list;
    ImageView available_text;
    ImageView bottom_text;
    ImageView current_text;
    PercentRelativeLayout base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_editor_activity);
        setVariables();
        AvailableAdapter leftAdapter = new AvailableAdapter(this);
        final CurrentAdapter rightAdapter = new CurrentAdapter(this);
        available_list.setAdapter(leftAdapter);
        current_list.setAdapter(rightAdapter);
        current_text.setImageResource(R.raw.current_text_bg);
        available_text.setImageResource(R.raw.available_text_bg);
        bottom_text.setImageResource(R.raw.save_bg);
        available_text.setScaleType(ImageView.ScaleType.FIT_XY);
        current_text.setScaleType(ImageView.ScaleType.FIT_XY);
        bottom_text.setScaleType(ImageView.ScaleType.FIT_XY);
        current_list.setOnDragListener(new CurrentDragListener());
        available_list.setOnDragListener(new AvailableDragListener());
        final Context c = this;
        bottom_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeckFileHandler(c).writeDeckFile(new DeckRepresentation(rightAdapter.counts));
                Toast.makeText(c, "Deck saved!", Toast.LENGTH_SHORT).show();
            }
        });
        bottom_text.setOnTouchListener(new InvertColorTouchListener(bottom_text));
    }

    private void setVariables(){
        available_list = (ListView) findViewById(R.id.available_list);
        available_area = (PercentRelativeLayout) findViewById(R.id.available_area);
        bottom_area = (PercentRelativeLayout) findViewById(R.id.bottom_area);
        top_area = (PercentRelativeLayout) findViewById(R.id.top_area);
        current_area = (PercentRelativeLayout) findViewById(R.id.current_area);
        current_list = (ListView) findViewById(R.id.current_list);
        available_text = (ImageView) findViewById(R.id.available_text);
        bottom_text = (ImageView) findViewById(R.id.bottom_text);
        current_text = (ImageView) findViewById(R.id.current_text);
        base = (PercentRelativeLayout) findViewById(R.id.base);
    }


/*
    @Override
    public void onStart(){
        super.onStart();
        current_list.setOnDragListener(new CurrentDragListener(this));
    }
*/
}
