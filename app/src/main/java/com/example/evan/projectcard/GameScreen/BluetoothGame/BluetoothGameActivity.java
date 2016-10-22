package com.example.evan.projectcard.GameScreen.BluetoothGame;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Toast;

import com.example.evan.projectcard.Engine.ActivateEffectFrame;
import com.example.evan.projectcard.Engine.ActivateEffectMove;
import com.example.evan.projectcard.Engine.AttackFrame;
import com.example.evan.projectcard.Engine.AttackMove;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.MoveCardFrame;
import com.example.evan.projectcard.Engine.MoveCardMove;
import com.example.evan.projectcard.Engine.PlayCardFrame;
import com.example.evan.projectcard.Engine.PlayCardMove;
import com.example.evan.projectcard.Engine.RotateUnitFrame;
import com.example.evan.projectcard.Engine.RotateUnitMove;
import com.example.evan.projectcard.Engine.SeizeFrame;
import com.example.evan.projectcard.Engine.SeizeMove;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.MoveInterpreter;
import com.example.evan.projectcard.Network.Bluetooth.BluetoothMoveSync;
import com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth.EZBluetoothCallback;
import com.example.evan.projectcard.Network.Bluetooth.EasyBluetooth.EZBluetoothConnection;
import com.example.evan.projectcard.Network.NetworkGameActivity;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.AnonymousContainer;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by Evan on 9/23/2016.
 */
public class BluetoothGameActivity extends GameActivity implements NetworkGameActivity {
    ProgressDialog progress;
    EZBluetoothConnection connection;
    boolean playerOrderChosen;
    public boolean isFirstPlayer;
    final AnonymousContainer turnToken = new AnonymousContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        turnToken.setValue(new Integer(new Random().nextInt()));
        setContentView(R.layout.game_screen_layout);
        Bundle bundle = getIntent().getExtras();
        progress = ProgressDialog.show(this, "Connecting", "Wait while connecting...", true, false);
        String address = bundle.getString("CHOSEN_DEVICE_MAC_ADDRESS");
        connection = new EZBluetoothConnection();
        final BluetoothGameActivity context = this;
        EZBluetoothCallback callback = new EZBluetoothCallback() {
            @Override
            public void onConnect() {
                Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
              /* new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(context,"Trying to write...",Toast.LENGTH_SHORT).show();
                        connection.writeObject(new RotateUnitMove(context.game.currentPlayer, new Card_RankAndFile(context.game.currentPlayer), Direction.DOWN));
                    }
                }, 4000);*/
                context.dismissProgressDialog();
                byte[] bytes = ByteBuffer.allocate(4).putInt((Integer) turnToken.getValue()).array();
                connection.write(bytes);
            }

            @Override
            public void onDisconnect() {
                Toast.makeText(context,"Disconnected",Toast.LENGTH_LONG).show();
                context.finish();
            }

            @Override
            public void onWrite() {
                Toast.makeText(context,"Writing...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRead(byte[] payload) {
                if(!playerOrderChosen){
                    decidePlayerOrder(payload);
                }
            }

            @Override
            public void onWriteObject() {
                Toast.makeText(context,"Writing object...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadObject(Object payload) {
               // Toast.makeText(context, payload.toString(),Toast.LENGTH_LONG).show();
                if(payload instanceof Move){
                    Move syncMove = BluetoothMoveSync.sync(context, (Move) payload);
                    MoveInterpreter.launch(syncMove);
                }
            }
        };
        connection.setCallback(callback);
        connection.connect(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address));

    }

    public void sendMove(Move move) {
        connection.writeObject(move);
    }

    public void recieveMove(Move move){
        Toast.makeText(this, "Move recieved", Toast.LENGTH_SHORT).show();
        Move newMove = BluetoothMoveSync.sync(this, move);
        move = newMove;
        if(move instanceof PlayCardMove){
            new PlayCardFrame(move.getGame(), (PlayCardMove) move).launch();
        }
        if(move instanceof RotateUnitMove){
            new RotateUnitFrame(move.getGame(), (RotateUnitMove) move).launch();
        }
        if(move instanceof MoveCardMove){
            new MoveCardFrame(move.getGame(), (MoveCardMove) move).launch();
        }
        if(move instanceof AttackMove){
            new AttackFrame(move.getGame(), (AttackMove) move).launch();
        }
        if(move instanceof ActivateEffectMove){
            new ActivateEffectFrame(move.getGame(), (ActivateEffectMove) move).launch();
        }
        if(move instanceof SeizeMove){
            new SeizeFrame(move.getGame(), (SeizeMove) move).launch();
        }
    }

    @Override
    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public void dismissProgressDialog() {
        progress.dismiss();
        startGame();
    }

    @Override
    public void onDestroy(){
        connection.close();
        super.onDestroy();
    }

    public void decidePlayerOrder(byte[] in){
        ByteBuffer wrapped = ByteBuffer.wrap(in); // big-endian by default
        int num = wrapped.getInt(); // 1
        if(num < (Integer) turnToken.getValue()){
            isFirstPlayer = true;
            playerOrderChosen = true;
        }
        if(num > (Integer) turnToken.getValue()){
            playerOrderChosen = true;
        }
        if(num == (Integer) turnToken.getValue()){
            turnToken.setValue(new Integer(new Random().nextInt()));
            byte[] bytes = ByteBuffer.allocate(4).putInt((Integer) turnToken.getValue()).array();
            connection.write(bytes);
        }
    }
}
