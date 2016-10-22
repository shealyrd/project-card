package com.example.evan.projectcard.DeckBuilder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.evan.projectcard.Utilities.DeckFileHandler;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.ImageDragShadowBuilder;

/**
 * Created by Evan on 9/6/2016.
 */
public class CurrentAdapter extends BaseAdapter {
    Context context;
    int[] ids;
    Integer[] counts;
    AvailableListItemView[] views;

    public CurrentAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        ids = CardDatabase.getDrawableIDs();
        counts = new Integer[CardDatabase.size()];
        views = new AvailableListItemView[CardDatabase.size()];
        initializeCounts();
        for(int i = 0; i < CardDatabase.size(); i++){
            AvailableListItemView convertView = new AvailableListItemView(context);
            convertView.getCardImg().setImageResource(ids[i]);
            convertView.getCardName().setText(counts[i] + "");
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    View.DragShadowBuilder shadowBuilder = ImageDragShadowBuilder.fromResource(v.getContext(), ((ImageView) v.findViewById(R.id.card_img)).getDrawable());
                    v.startDrag(null, shadowBuilder, v, 0);
                    return true;
                }
            });
            views[i] = convertView;
        }
    }

    public void initializeCounts(){
        counts = new DeckFileHandler(context).readStoredDeckToIntArray();
    }

    @Override
    public int getCount() {
        return CardDatabase.size();
    }

    @Override
    public Object getItem(int position) {
        //Store all views beforehand
        return views[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return views[position];
    }

    public void incrementCount(int index){
        counts[index] = counts[index] + 1;
        ((AvailableListItemView) getItem(index)).getCardName().setText(counts[index] + "");
    }

    public void decrementCount(int index){
        counts[index] = counts[index] - 1;
        ((AvailableListItemView) getItem(index)).getCardName().setText(counts[index] + "");
    }

    public int getViewPosition(AvailableListItemView view){
        int result = 0;
        for(int i = 0; i < views.length; i++){
            if(views[i].equals(view)){
                result = i;
            }
        }
        return result;
    }
}

