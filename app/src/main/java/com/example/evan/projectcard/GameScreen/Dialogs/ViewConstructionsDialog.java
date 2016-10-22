package com.example.evan.projectcard.GameScreen.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;

import com.example.evan.projectcard.Engine.Card;
import com.example.evan.projectcard.Engine.CardCollection;
import com.example.evan.projectcard.Engine.Cell;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.R;

/**
 * Created by Evan on 8/31/2016.
 */
public class ViewConstructionsDialog extends Dialog {
    CellView cellView;

    public ViewConstructionsDialog(Context context, CellView cellView) {
        super(context);
        this.cellView = cellView;
    }

    public ViewConstructionsDialog(Context context) {
        super(context);
    }

    public ViewConstructionsDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ViewConstructionsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(){
        setContentView(R.layout.construction_dialog);
        setTitle("Constructions");
        Cell cell = cellView.getCell();
        CardCollection construct = cell.getConstructions();
        LinearLayout container = (LinearLayout) findViewById(R.id.constructionContainer);
        for(Card c: construct){
            CardView temp = new CardView(cellView.getContext(), c);
            container.addView(temp);
        }
    }

    public void fixDim(){
        GameActivity activity = (GameActivity) cellView.getCell().getOwner().getGame().getActivity();
        float height = activity.getWindowManager().getDefaultDisplay().getHeight()/1.7f;
        float width = activity.getWindowManager().getDefaultDisplay().getWidth()/1.7f;
        getWindow().setLayout((int) height, (int) width);
    }
}
