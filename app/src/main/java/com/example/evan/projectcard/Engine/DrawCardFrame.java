package com.example.evan.projectcard.Engine;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.evan.projectcard.Audio.Sounds;
import com.example.evan.projectcard.GameScreen.CardDatabase;
import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;
import com.example.evan.projectcard.GameScreen.GameActivity;
import com.example.evan.projectcard.GameScreen.Views.CardView;
import com.example.evan.projectcard.R;
import com.example.evan.projectcard.Utilities.AnonymousContainer;

import java.io.Serializable;

public class DrawCardFrame extends Frame implements Serializable {
	private static final long serialVersionUID = -840329903230361181L;
	Player player;
	int numToDraw = 1;

	public DrawCardFrame(Game game, Player player){
		super(game,
				"DrawCard");
		this.player = player;
	}
	
	public DrawCardFrame(Player player){
		super(player.getGame(),
				"DrawCard");
		this.player = player;
	}

	public DrawCardFrame(Player player, int i) {
		super(player.getGame(),
				"DrawCard");
		this.player = player;
		this.numToDraw = i;
	}

	@Override
	public void execute() {
		final BasicListener argListener = listener;
		final Player player1 = player;
		if(game.bothDecksEmpty()){
			new DeckOutFrame(game).launch();
		}
		else{
			if(player1 == game.players[0]){
				final Handler handler = new Handler();
				final AnonymousContainer val = new AnonymousContainer();
				val.setValue(new Integer(numToDraw));
				Runnable enclosedRunnable = new Thread();
				final AnonymousContainer runnable = new AnonymousContainer();
				final ImageView drawnView = (ImageView) game.getActivity().findViewById(R.id.drawn_card);
				final LinearLayout handView = (LinearLayout) game.activity.findViewById(R.id.hand_container);
				drawnView.setImageResource(CardDatabase.getDrawableID(1));
				drawnView.setVisibility(View.INVISIBLE);
				float handScrollWidth = game.getActivity().getWindow().getDecorView().getWidth()/5;
				float difference = handScrollWidth - drawnView.getWidth();
				float position = game.getActivity().getWindow().getDecorView().getWidth() - handScrollWidth + difference/2;
				drawnView.setX(position);
				enclosedRunnable = new Runnable(){
					@Override
					public void run() {
						int i2 = (int) val.getValue();
						final int c = player1.deck.draw();
						final Card drawn = CardEngineLookup.getCardByID(c, player1, player1.getGame());
						final GameActivity activity = (GameActivity) game.getActivity();
						ScrollView handScrollView = (ScrollView )activity.findViewById(R.id.hand_view);
						drawnView.setImageResource(CardDatabase.getDrawableID(c));
						drawnView.setVisibility(View.VISIBLE);
						float handScrollWidth = game.getActivity().getWindow().getDecorView().getWidth()/5;
						float difference = handScrollWidth - drawnView.getWidth();
						float position = game.getActivity().getWindow().getDecorView().getWidth() - handScrollWidth + difference/2;
						drawnView.setX(position);
						Animation.AnimationListener listener = new Animation.AnimationListener() {


							@Override
							public void onAnimationStart(Animation animation) {
								((GameActivity) game.getActivity()).playSound(Sounds.DRAW);
							}

							@Override
							public void onAnimationEnd(Animation animation) {
								CardView card = new CardView(activity, drawn);
								handView.addView(card);
								player1.hand.add(drawn);
								player1.game.log(player1.name + " drew " + drawn.getName());
								if((int) val.getValue() > 1){
									val.setValue((int) val.getValue() - 1);
									handler.post((Runnable) runnable.getValue());
								}
								else{
									if(argListener != null){
										argListener.onEvent();
									}
								}
								drawnView.clearAnimation();
								drawnView.setVisibility(View.GONE);
							}

							@Override
							public void onAnimationRepeat(Animation animation) {

							}
						};
						//float distance = activity.getWindow().getDecorView().getHeight()/2;
						float scrollTop = activity.getWindow().getDecorView().getHeight()/10;
						float deckTop = activity.getWindow().getDecorView().getHeight() - activity.getWindow().getDecorView().getHeight()/5;;
						float distance =  scrollTop + activity.findViewById(R.id.hand_container).getBottom() - deckTop;
						int duration = 500 - (50 * player1.hand.size());
						if(distance > 0){
							distance = - activity.getWindow().getDecorView().getHeight()/2;
							duration = 500;
						}
						TranslateAnimation anim = new TranslateAnimation(0f,0f,0f,distance);

						anim.setDuration(duration);
						anim.setAnimationListener(listener);
						drawnView.startAnimation(anim);

					}
				};
				runnable.setValue(enclosedRunnable);
				handler.post(enclosedRunnable);
			}
			else{
				while(numToDraw > 0){
					player1.draw();
					numToDraw--;
				}
			}
		}
	}

	@Override
	public Frame getNextFrame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
