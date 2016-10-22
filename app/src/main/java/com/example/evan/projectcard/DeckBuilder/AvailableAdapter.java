package com.example.evan.projectcard.DeckBuilder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.Network.Http.HttpExecutor;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.ImageDragShadowBuilder;

/**
 * Created by Evan on 9/6/2016.
 */
public class AvailableAdapter extends BaseAdapter{
    Context context;
    int[] ids;
    String[] names;

    public AvailableAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        ids = CardDatabase.getDrawableIDs();
        names = CardDatabase.getNames();
    }

    @Override
    public int getCount() {
        return CardDatabase.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = new AvailableListItemView(context);
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    View.DragShadowBuilder shadowBuilder = ImageDragShadowBuilder.fromResource(context, ((ImageView) v.findViewById(R.id.card_img)).getDrawable());
                    v.startDrag(null, shadowBuilder, v, 0);
                    return true;
                }
            });
        }
        ((AvailableListItemView) convertView).getCardImg().setImageResource(ids[position]);
        ((AvailableListItemView) convertView).getCardName().setText(names[position]);
        return convertView;
    }
}
