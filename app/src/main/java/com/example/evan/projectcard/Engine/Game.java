package com.example.evan.projectcard.Engine;
import android.app.Activity;

import com.example.evan.projectcard.Utilities.DeckFileHandler;
import com.example.evan.projectcard.GameScreen.Views.GameLogView;
import com.example.evan.projectcard.R;

import java.io.Serializable;

public class Game implements Serializable{
	private static final long serialVersionUID = -4115523514758907238L;
	public Player[] players = new Player[]{new Player(this), new Player(this)};
	public Player currentPlayer;
	public Player waitingPlayer;
	public transient Frame currentFrame;
	public Board board = new Board(this);
	public Player winner;
	public transient Activity activity;

	public Activity getActivity(){
		return activity;
	}

	public void setActivity(Activity act){
		activity = act;
	}

	public void log(String input){
		GameLogView view = (GameLogView) getActivity().findViewById(R.id.logview);
		view.appendToLog(input);
	}
	
	boolean ignoreResourceMode = false;
	boolean gameOver = false;
	boolean draw = false;
	int turnNum = 0;
	

	public void start(){
		GlobalScanner.setGame(this);
		registerPlayers();
		syncPlayersAndBoard();
		initializeDecks();
		startFrameLoop();
	}
	
	protected void initializeDecks() {
		board.p1Deck.fillDeck(new DeckFileHandler(getActivity()).readStoredDeckToIntArray());
		android.util.Log.d("GAME DECK", board.p1Deck.toString());
		board.p2Deck.fillRandomly(30);
	}

	public void startGame(){
		registerPlayers();
		syncPlayersAndBoard();
		initializeDecks();
		currentFrame = new StartGameFrame(this);
		currentFrame.launch();
	}

	public void startNextFrame(){
		if(currentFrame.getNextFrame() == null){
			android.util.Log.d("zfa", currentFrame.frameName);
		}
		currentFrame = currentFrame.getNextFrame();
		if(currentFrame != null){
			currentFrame.launch();
		}
	}
	//this is okay for now but it will need to be replaced
	//with a stack in later implementations
	protected void startFrameLoop() {
		currentFrame = new StartGameFrame(this);
		while(isRunning()){
			currentFrame.launch();
			currentFrame = currentFrame.getNextFrame();
			if(currentFrame instanceof StartTurnFrame){
				turnNum++;
				Log.add("Turn " + turnNum + "\n");
				Log.add(board.toString() + "\n");
			}
		}
		
	}

	protected void syncPlayersAndBoard() {
		players[0].hand = board.p1Hand = new Hand(players[0]);
		players[1].hand = board.p2Hand = new Hand(players[1]);
		players[0].deck = board.p1Deck = new Deck(players[0]);
		players[1].deck = board.p2Deck = new Deck(players[1]);
	}

	public Player getPlayerByName(String name){
		for(Player p: players){
			if(p.name == name){
				return p;
			}
		}
		return null;
	}

	protected void registerPlayers() {
		Log.add("Please enter name for p1.");
		players[0].setName("p1");
		Log.add("Please enter name for p2.");
		players[1].setName("p2");
	}

	public void playUnit(IdentityCard playedCard, Cell playedIn, Direction direction) {
		playedCard.setDirection(direction);
		playedIn.setUnit(playedCard);
		playedCard.setFieldStatus(FieldStatus.Field);
		android.util.Log.d("Bluetooth", "Unit played");
	}
/*
	public void playCraft(CraftCard craft, IdentityCard caster) {
		if(caster.canUseCraft(craft)){
			craft.activate();
		}
	}
*/
	public void playConstruction(ConstructionCard playedCard, Cell playedIn, Direction direction) {
		playedCard.setDirection(direction);
		playedIn.addConstruction(playedCard);
		playedCard.setCell(playedIn);
		playedCard.setFieldStatus(FieldStatus.Field);
	}


	public void moveUnit(IdentityCard moved, Cell toMoveTo, Direction direction) {
		moved.reduceAPBy(1);
		Cell oldCell = moved.getCell();
		oldCell.removeUnit();
		moved.setDirection(direction);
		toMoveTo.setUnit(moved);
		moved.refreshItems();
	}


	public void rotateUnit(IdentityCard toRotate, Direction direction) {
		toRotate.setDirection(direction);
	}

	public void battle(IdentityCard attacker, IdentityCard attacked) {
		attacked.inflictDamage(attacker.currentATK);
	}

	public void activateEffect(Card activator) {
			activator.activate().launch();
	}

	public Player[] getPlayers() {
		return players;
	}

	public void swapPlayers() {
		Player temp = currentPlayer;
		currentPlayer = waitingPlayer;
		waitingPlayer = temp;
	}
	/*
	public void draw(Player player, int i, BasicListener listener){
		final BasicListener argListener = listener;
		final Player player1 = player;
		if(bothDecksEmpty()){
			new DeckOutFrame(this).launch();
		}
		else{
			if(player1 == players[0]){
				final Handler handler = new Handler();
				final AnonymousContainer val = new AnonymousContainer();
				val.setValue(new Integer(i));
				Runnable enclosedRunnable = new Thread();
				final AnonymousContainer runnable = new AnonymousContainer();
				final ImageView drawnView = (ImageView) getActivity().findViewById(R.id.drawn_card);
				final LinearLayout handView = (LinearLayout) activity.findViewById(R.id.hand_container);
				drawnView.setImageResource(CardDatabase.getDrawableID(1));
				drawnView.setVisibility(View.INVISIBLE);
				float handScrollWidth = getActivity().getWindow().getDecorView().getWidth()/5;
				float difference = handScrollWidth - drawnView.getWidth();
				float position = getActivity().getWindow().getDecorView().getWidth() - handScrollWidth + difference/2;
				drawnView.setX(position);
				enclosedRunnable = new Runnable(){
					@Override
					public void run() {
						int i2 = (int) val.getValue();
						final int c = player1.deck.draw();
						final Card drawn = CardEngineLookup.getCardByID(c, player1, player1.getGame());
						final GameActivity activity = (GameActivity) getActivity();
						ScrollView handScrollView = (ScrollView )activity.findViewById(R.id.hand_view);
						drawnView.setImageResource(CardDatabase.getDrawableID(c));
						drawnView.setVisibility(View.VISIBLE);
						float handScrollWidth = getActivity().getWindow().getDecorView().getWidth()/5;
						float difference = handScrollWidth - drawnView.getWidth();
						float position = getActivity().getWindow().getDecorView().getWidth() - handScrollWidth + difference/2;
						drawnView.setX(position);
						Animation.AnimationListener listener = new Animation.AnimationListener() {


							@Override
							public void onAnimationStart(Animation animation) {

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
				while(i > 0){
					player1.draw();
					i--;
				}
			}
		}
	}
*/
	public boolean bothDecksEmpty() {
		if((currentPlayer.getDeckCount() == 0) && (waitingPlayer.getDeckCount() == 0)){
			return true;
		}
		return false;
	}

	public Board getBoard() {
		return board;
	}

	public void addCardToHand(Card chosen, Hand hand) {
		hand.add(chosen);
		chosen.setFieldStatus(FieldStatus.Hand);
		
	}

	public void setIgnoreResourceMode() {
		ignoreResourceMode = true;
	}
	
	public boolean ignoreResourceModeOn() {
		return ignoreResourceMode;
	}

	public void end(){
		gameOver = true;
	}

	public boolean isRunning() {
		return !gameOver;
	}

	public void endByDraw() {
		draw = true;
		end();
	}
	




	
	
	
	
	
}
