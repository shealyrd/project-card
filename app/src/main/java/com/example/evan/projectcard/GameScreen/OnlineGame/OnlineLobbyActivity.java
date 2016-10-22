package com.example.evan.projectcard.GameScreen.OnlineGame;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.Network.Http.HttpExecutor;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.YesNoDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Evan on 9/23/2016.
 */
public class OnlineLobbyActivity extends Activity {
    Button button;
    Set<String> connectionChoices;
    String result;
    private final String URL = "http://projectcard-91355.onmodulus.net/api/getgames";
    ListView listV;
    ProgressDialog progress;
    int chosenGameID;
    int numberOfGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
        //populateList();
        Button button = (Button) findViewById(R.id.button1);
        button.setText("Make New Game");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMakeGame();
            }
        });
        populateList();
        //progress = ProgressDialog.show(this, "Loading", "Wait while loading...", true, false);

    }

    public void requestMakeGame(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Make Game");
        alert.setMessage("Please enter game name");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/makegame", "{\"name\": \"" + input.getText().toString() + "\"}" );
                //populateList();
                Intent k = new Intent(OnlineLobbyActivity.this, OnlineGameActivity.class);
                String count = HttpExecutor.downloadUrlText("http://projectcard-91355.onmodulus.net/api/getgamecount");
                Integer countInt = Integer.parseInt(count);
                k.putExtra("GAME_ID", countInt.intValue() - 1);
                k.putExtra("JOINING", "FALSE");
                startActivity(k);
                finish();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    public void populateList(){
        listV = (ListView) findViewById(R.id.listView);
        result = HttpExecutor.downloadUrlText(URL);
        JSONArray jsonRootObject = null;
        try {
            jsonRootObject = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<String> s = new ArrayList<String>();
        numberOfGames = jsonRootObject.length();
        for(int i = 0; i < jsonRootObject.length(); i++){
            try {
                JSONObject obj = (JSONObject) jsonRootObject.get(i);
                s.add(obj.get("name").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(OnlineLobbyActivity.this, jsonRootObject.length() + "", Toast.LENGTH_LONG).show();
        listV = (ListView) findViewById(R.id.listView);
        listV.setAdapter(new ArrayAdapter<String>(OnlineLobbyActivity.this, R.layout.lobby_list_item, R.id.device_name, s));
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView tView = (TextView) view.findViewById(R.id.device_name);
                String gameName = (String) tView.getText();
                final YesNoDialog dialog = new YesNoDialog(OnlineLobbyActivity.this, "Join " + gameName + "?");
                dialog.setOnYesListener(new BasicListener() {
                    @Override
                    public void onEvent() {
                        //HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/joingame", "{\"id\":\"0\"}");
                        //String token = HttpExecutor.downloadUrlText("http://projectcard-91355.onmodulus.net/api/gettoken");
                        //HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/postrelaydata", "{\"token\":\"" + token +"\", \"id\": " + "\"0" + "\", \"data\": \"" + "TEST DATA" + "\"}");
                        //Log.d("POST",  "{\"token\":\"" + token +"\", \"id\": " + "\"0" + "\", \"data\": \"" + "TEST DATA" + "\"}");
                        Intent k = new Intent(OnlineLobbyActivity.this, OnlineGameActivity.class);
                        k.putExtra("GAME_ID", position);
                        k.putExtra("JOINING", "TRUE");
                        HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/joingame", "{\"id\":\"" + position + "\"}");
                        startActivity(k);
                        finish();
                    }
                });
                dialog.setOnNoListener(new BasicListener() {
                    @Override
                    public void onEvent() {
                    }
                });
                dialog.show();

            }
        });
    }

}
