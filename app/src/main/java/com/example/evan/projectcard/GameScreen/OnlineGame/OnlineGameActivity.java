package com.example.evan.projectcard.GameScreen.OnlineGame;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.evan.projectcard.Engine.Game;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothMove;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.Network.Bluetooth.BluetoothMoveSync;
import com.example.evan.projectcard.Network.Http.HttpConnectionCallback;
import com.example.evan.projectcard.Network.Http.HttpGameConnection;
import com.example.evan.projectcard.Network.NetworkGameActivity;

import java.io.IOException;

/**
 * Created by Evan on 10/10/2016.
 */
public class OnlineGameActivity extends GameActivity implements NetworkGameActivity {
    int ID;
    HttpGameConnection connection = new HttpGameConnection();
    ProgressDialog progress;
    boolean isFirstPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getInt("GAME_ID");
        connection.setGameID(ID + "");
        Log.d("BUNDLE", bundle.getString("JOINING"));
        startConnection();
        if(bundle.getString("JOINING").equals("FALSE")){
            Log.d("BUNDLE", "Inside if statement");
            isFirstPlayer = true;
            progress = ProgressDialog.show(this, "Connecting", "Wait while connecting...", true, false);
            progress.show();
        }
        else{
            startGame();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(this, ID + "", Toast.LENGTH_LONG).show();
    }

    public void startConnection(){
        connection.setCallback(new HttpConnectionCallback() {
            @Override
            public void onRead(final String http) {
                OnlineGameActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(OnlineGameActivity.this, http, Toast.LENGTH_SHORT).show();
                        //String data = obj.get("data").toString();
                        byte[] unhexedData = new byte[1];
                        try {
                            unhexedData = Base64.decode(http, Base64.DEFAULT);
                        }
                        catch(Exception e){

                        }
                        Move move = null;
                        try {
                            move = BluetoothMove.deserialize(unhexedData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if(move != null){
                            Toast.makeText(OnlineGameActivity.this, move.toString(), Toast.LENGTH_SHORT).show();
                            recieveMove(move);
                        }
                        else{
                            Toast.makeText(OnlineGameActivity.this, "It's null", Toast.LENGTH_SHORT).show();
                        }
                        /*JSONObject obj = null;
                        try {
                            obj = new JSONObject(http);
                            if(obj.get("type").toString().equals("Object")){
                                String data = obj.get("data").toString();
                                byte[] unhexedData = HexUtils.toByteArray(data);
                                Move move = BluetoothMove.deserialize(unhexedData);
                                recieveMove(move);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
            }

            @Override
            public void on404() {
                onDisconnect();
            }

            @Override
            public void onConnect() {
                dismissProgressDialog();
                //sendMove(new Move());
            }

            @Override
            public void onDisconnect() {
                /*JSONObject leaveObj = new JSONObject();
                try {
                    leaveObj.put("id", ID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpExecutor.postJson("http://projectcard-91355.onmodulus.net/api/leavegame", leaveObj.toString());*/
                Toast.makeText(OnlineGameActivity.this,"Disconnected",Toast.LENGTH_LONG).show();
                OnlineGameActivity.this.finish();
            }});

        connection.start();
    }


    public void recieveMove(Move move) {
        Move newMove = BluetoothMoveSync.sync(this, move);
        MoveInterpreter.launch(newMove);
    }


    @Override
    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public void sendMove(Move move){
        BluetoothMove bMove = new BluetoothMove(move);
        try {
            // Toast.makeText(context, "Move written", Toast.LENGTH_SHORT).show();
            byte[] payload = bMove.serialize();
            Toast.makeText(this, payload.length + "", Toast.LENGTH_SHORT).show();
            //Log.d("Http", HexUtils.toHex(payload));
            //String jsonPosting = "{\"type\": \"Object\", \"data\": \"" + HexUtils.toHex(payload) + "\"}";
            String jsonPosting =  Base64.encodeToString(payload, Base64.DEFAULT);
            connection.sendRelayData(jsonPosting);
        } catch (IOException e) {
            Toast.makeText(this, "Move writing error", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void dismissProgressDialog() {
        progress.dismiss();
        startGame();
    }

    @Override
    public void onDestroy(){
        connection.running = false;
        connection.close();
        //connection.sendRelayData("DISCONNECTED");
        super.onDestroy();
    }


}
