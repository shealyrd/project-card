package com.example.evan.projectcard.GameScreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evan.projectcard.Audio.BackgroundMusic;
import com.example.evan.projectcard.Audio.GameSoundFX;
import com.example.evan.projectcard.Engine.EndMainPhaseMove;
import com.example.evan.projectcard.Engine.EndPhaseFrame;
import com.example.evan.projectcard.Engine.Game;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.GameScreen.Views.CellView;
import com.example.evan.projectcard.Network.PerceptionFlipper;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.YesNoDialog;

public class GameActivity extends Activity{
    public boolean playerGoesFirst;
    public final Game game = new Game();
    GameSoundFX soundFX;
    BackgroundMusic bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_layout);
        soundFX = new GameSoundFX(this);
        bgMusic = new BackgroundMusic(this, R.raw.bgmusic);
        game.setActivity(this);
        //overridePendingTransition(R.anim.fadein, R.anim.black_out);
        TextView playerName = (TextView) findViewById(R.id.p1ResourceBar_child1);
        playerName.setText("PLAYER");
        TextView comName = (TextView) findViewById(R.id.p2ResourceBar_child1);
        comName.setText("COM");
        /*LinearLayout hand = (LinearLayout) findViewById(R.id.game_screen_child4_child1);
        for(int i = 1; i <= CardEngineLookup.size(); i++){
            CardView card = new CardView(this, i);
            hand.addView(card);
        }
        /*final Handler h = new Handler();
        final int delay = 1000; //milliseconds
        final GameLogView view = (GameLogView) findViewById(R.id.logview);
        h.postDelayed(new Runnable(){
            public void run(){
                Time now = new Time();
                now.setToNow();
                view.appendToLog(now.toString());
                h.postDelayed(this, delay);
            }
        }, delay);*/
    }

    public void startGame(){
        game.startGame();
        tagCells();
    }

    public void activateCells(){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.setListeners();
        }
    }


    public void deactivateCells(){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.removeListeners();
        }
    }

    public void activateCellChoice(BasicListener listener){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.removeListeners();
            cell.setChoiceListener(listener);
        }
    }

    public void activateHand(){
        ViewGroup root = (ViewGroup) findViewById(R.id.hand_container);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CardView card = (CardView) root.getChildAt(i);
            card.setListeners();
        }
    }

    public void deactivateHand(){
        ViewGroup root = (ViewGroup) findViewById(R.id.hand_container);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CardView card = (CardView) root.getChildAt(i);
            card.removeListeners();
        }
    }

    public void tagCells(){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.index = i + 1;
            if(i+1 < 10){
                cell.alignment = CellAlignment.Red;
            }
            else{
                cell.alignment = CellAlignment.Green;
            }
        }
    }

    public void tagCellsInverse(){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.index = PerceptionFlipper.flipIndex(i + 1);
        }
    }

    public CellView getCellAtIndex(int index){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            if(cell.index == index){
                return cell;
            }
        }
        return null;
    }


    public void activateEndPhaseButton(){
        final ImageView endPhaseView = (ImageView) findViewById(R.id.deck);
        endPhaseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endPhaseView.setImageResource(R.raw.deck);
                endPhaseView.setOnClickListener(null);
                EndMainPhaseMove endMove = new EndMainPhaseMove(game.currentPlayer);
                MoveInterpreter.launch(endMove);
            }
        });
    }

    public void playSound(int id){
        soundFX.playShortResource(id);
    }

    public void deactivateEndPhaseButton(){
        ImageView endPhaseView = (ImageView) findViewById(R.id.deck);
        endPhaseView.setOnClickListener(null);
    }

    public void growText(int id, Animation.AnimationListener listener){
        ImageView testView = (ImageView) findViewById(R.id.test_spin);
        testView.setImageResource(id);
        testView.bringToFront();
        Animation anim = OpeningSpinAnimationFactory.createNoRotate();
        anim.setAnimationListener(listener);
        testView.startAnimation(anim);
    }

    public void spinText(int id, Animation.AnimationListener listener){
        ImageView testView = (ImageView) findViewById(R.id.test_spin);
        testView.setImageResource(id);
        testView.bringToFront();
        Animation anim = OpeningSpinAnimationFactory.create(testView);
        anim.setAnimationListener(listener);
        testView.startAnimation(anim);
    }

    public void refreshCells(){
        ViewGroup root = (ViewGroup) findViewById(R.id.cell_area);
        int count = root.getChildCount();
        for(int i = 0; i < count; i++){
            CellView cell = (CellView) root.getChildAt(i);
            cell.refreshView();
        }
        refreshResources();
    }

    public void refreshResources(){
        PercentRelativeLayout playerColumn1 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child2);
        PercentRelativeLayout playerColumn2 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child3);
        PercentRelativeLayout playerColumn3 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child4);
        PercentRelativeLayout playerColumn4 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child5);
        PercentRelativeLayout playerColumn5 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child6);
        PercentRelativeLayout playerColumn6 = (PercentRelativeLayout) findViewById(R.id.p1ResourceBar_child7);
        TextView playerText1 = (TextView) playerColumn1.getChildAt(1);
        TextView playerText2 = (TextView) playerColumn2.getChildAt(1);
        TextView playerText3 = (TextView) playerColumn3.getChildAt(1);
        TextView playerText4 = (TextView) playerColumn4.getChildAt(1);
        TextView playerText5 = (TextView) playerColumn5.getChildAt(1);
        TextView playerText6 = (TextView) playerColumn6.getChildAt(1);
        playerText1.setText(game.players[0].getResourcePool().getBasicCount() + "");
        playerText2.setText(game.players[0].getResourcePool().getFireCount() + "");
        playerText3.setText(game.players[0].getResourcePool().getWaterCount() + "");
        playerText4.setText(game.players[0].getResourcePool().getLightningCount() + "");
        playerText5.setText(game.players[0].getHand().size() + "");
        playerText6.setText(game.players[0].getDeckCount() + "");

        PercentRelativeLayout comColumn1 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child2);
        PercentRelativeLayout comColumn2 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child3);
        PercentRelativeLayout comColumn3 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child4);
        PercentRelativeLayout comColumn4 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child5);
        PercentRelativeLayout comColumn5 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child6);
        PercentRelativeLayout comColumn6 = (PercentRelativeLayout) findViewById(R.id.p2ResourceBar_child7);
        TextView comText1 = (TextView) comColumn1.getChildAt(1);
        TextView comText2 = (TextView) comColumn2.getChildAt(1);
        TextView comText3 = (TextView) comColumn3.getChildAt(1);
        TextView comText4 = (TextView) comColumn4.getChildAt(1);
        TextView comText5 = (TextView) comColumn5.getChildAt(1);
        TextView comText6 = (TextView) comColumn6.getChildAt(1);
        comText1.setText(game.players[1].getResourcePool().getBasicCount() + "");
        comText2.setText(game.players[1].getResourcePool().getFireCount() + "");
        comText3.setText(game.players[1].getResourcePool().getWaterCount() + "");
        comText4.setText(game.players[1].getResourcePool().getLightningCount() + "");
        comText5.setText(game.players[1].getHand().size() + "");
        comText6.setText(game.players[1].getDeckCount() + "");
    }

    @Override
    public void onBackPressed()
    {
        YesNoDialog dialog = new YesNoDialog(this, "Do you really want to quit?");
        dialog.setOnYesListener(new BasicListener() {
            @Override
            public void onEvent() {
                GameActivity.super.onBackPressed();
            }
        });
        dialog.setOnNoListener(new BasicListener() {
            @Override
            public void onEvent() {

            }
        });
        dialog.show();

    }

    @Override
    public void onResume(){
        super.onResume();
        if(!bgMusic.hasBeenActivated()){
            bgMusic.execute();
        }
        else if(!bgMusic.isRunning()){
            bgMusic.start();
        }
    }

    @Override
    public void onPause(){
        if(bgMusic.isRunning()){
            bgMusic.stop();
        }
        //bgMusic.cancel(true);
        super.onPause();
    }

    public Game getGame(){
        return game;
    }
}
