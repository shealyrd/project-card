package com.example.evan.projectcard.GameScreen;

import android.util.Log;

import com.example.evan.projectcard.Engine.ActivateEffectFrame;
import com.example.evan.projectcard.Engine.ActivateEffectMove;
import com.example.evan.projectcard.Engine.AttackFrame;
import com.example.evan.projectcard.Engine.AttackMove;
import com.example.evan.projectcard.Engine.EndMainPhaseFrame;
import com.example.evan.projectcard.Engine.EndMainPhaseMove;
import com.example.evan.projectcard.Engine.Move;
import com.example.evan.projectcard.Engine.MoveCardFrame;
import com.example.evan.projectcard.Engine.MoveCardMove;
import com.example.evan.projectcard.Engine.PlayCardFrame;
import com.example.evan.projectcard.Engine.PlayCardMove;
import com.example.evan.projectcard.Engine.RotateUnitFrame;
import com.example.evan.projectcard.Engine.RotateUnitMove;
import com.example.evan.projectcard.Engine.SeizeFrame;
import com.example.evan.projectcard.Engine.SeizeMove;
import com.example.evan.projectcard.GameScreen.BluetoothGame.BluetoothGameActivity;
import com.example.evan.projectcard.GameScreen.OnlineGame.OnlineGameActivity;

/**
 * Created by Evan on 8/28/2016.
 */
public class MoveInterpreter {


    public static void launch(Move move){

        if(move instanceof PlayCardMove){
            new PlayCardFrame(move.getGame(), (PlayCardMove) move).launch();
            sendMove(move);
        }
        if(move instanceof RotateUnitMove){
            sendMove(move);
            new RotateUnitFrame(move.getGame(), (RotateUnitMove) move).launch();
        }
        if(move instanceof MoveCardMove){
            sendMove(move);
            Log.d("MoveInterpreter", ((MoveCardMove) move).getMovedCard().currentCell.getIndex() + "");
            new MoveCardFrame(move.getGame(), (MoveCardMove) move).launch();
        }
        if(move instanceof AttackMove){
            sendMove(move);
            new AttackFrame(move.getGame(), (AttackMove) move).launch();
        }
        if(move instanceof ActivateEffectMove){
            new ActivateEffectFrame(move.getGame(), (ActivateEffectMove) move).launch();
        }
        if(move instanceof SeizeMove){
            sendMove(move);
            new SeizeFrame(move.getGame(), (SeizeMove) move).launch();
        }
        if(move instanceof EndMainPhaseMove){
            sendMove(move);
            new EndMainPhaseFrame(move.getGame()).launch();
        }
    }

    private static void sendMove(Move move){
        if(move.getGame().getActivity() instanceof BluetoothGameActivity){
            synchronized(move) {
                Log.d("Bluetooth", move.getClass().getName());
                if (!move.overNetwork) {
                    ((BluetoothGameActivity) move.getGame().getActivity()).sendMove(move);
                    //Toast.makeText(move.getGame().getActivity(), "Move sent", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(move.getGame().getActivity() instanceof OnlineGameActivity){
            synchronized(move) {
                Log.d("Online", move.getClass().getName());
                if (!move.overNetwork) {
                    ((OnlineGameActivity) move.getGame().getActivity()).sendMove(move);
                    //Toast.makeText(move.getGame().getActivity(), "Move sent", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
