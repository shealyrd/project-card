package com.example.evan.projectcard.Update;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evan.projectcard.Network.Http.HttpExecutor;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Update.Structs.Entry;
import com.example.evan.projectcard.Utilities.XML.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Evan on 10/6/2016.
 */
public class DownloadablesActivity extends Activity {
    Button button;
    Set<String> connectionChoices;
    String result;
    private final String URL = "http://projectcard-91355.onmodulus.net/api/updates";
    ListView listV;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
        //populateList();
        Button button = (Button) findViewById(R.id.button1);
        button.setText("Available Downloads");
        progress = ProgressDialog.show(this, "Loading", "Wait while loading...", true, false);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                listV = (ListView) findViewById(R.id.listView);
                result = HttpExecutor.downloadUrlText(URL);
                result = result.substring(result.indexOf('\n') + 1);
                result = result.trim();
                Document doc = XMLParser.getXMLDocument(result);
                Node root = doc.getDocumentElement();
                ArrayList<String> list = new ArrayList<>();
                NodeList entries = root.getChildNodes();
                //Node node = entries.item(1);
                //Toast.makeText(this, node.getChildNodes().getLength() + "", Toast.LENGTH_LONG).show();
                ArrayList<Entry> entryList = new ArrayList<Entry>();
                for (int i = 0; i < entries.getLength(); i++) {
                    if (i % 2 != 0) {
                        entryList.add(new Entry(entries.item(i)));
                    }
                }
                listV.setAdapter(new DownloadableAdapter(entryList, DownloadablesActivity.this));
                progress.dismiss();
            }
        }, 1000);
    }


}
