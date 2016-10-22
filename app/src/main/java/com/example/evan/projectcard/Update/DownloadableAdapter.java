package com.example.evan.projectcard.Update;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Network;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.evan.projectcard.DeckBuilder.AvailableListItemView;
import com.example.evan.projectcard.Network.Http.HttpExecutor;
import com.example.evan.projectcard.Network.NetworkConstants;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Update.Structs.Entry;
import com.example.evan.projectcard.Utilities.ImageDragShadowBuilder;
import com.example.evan.projectcard.Utilities.YesNoDialog;

import java.util.ArrayList;

/**
 * Created by Evan on 10/7/2016.
 */
public class DownloadableAdapter extends BaseAdapter {
    ArrayList<Entry> entries;
    Context context;

    public DownloadableAdapter(ArrayList<Entry> entries, Context context){
        this.entries = entries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return entries.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = new DownloadablesListItem(context);
            convertView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    YesNoDialog dialog = new YesNoDialog(context, "Download this card?");
                    dialog.show();
                }
            });
        }
        DownloadablesListItem view = (DownloadablesListItem) convertView;
        Entry currentEntry = entries.get(position);
        String imgURL = NetworkConstants.ROOT_URL + currentEntry.imguri;
        String nameURL = NetworkConstants.ROOT_URL + currentEntry.nameuri;
        String name = HttpExecutor.downloadUrlText(nameURL);
        Bitmap img = HttpExecutor.downloadUrlImage(imgURL);
        view.getCardName().setText(name);
        view.getCardImg().setImageBitmap(img);
        //convertView.setVisibility(View.VISIBLE);
        return convertView;

    }
}
