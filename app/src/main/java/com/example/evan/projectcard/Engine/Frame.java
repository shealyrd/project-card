package com.example.evan.projectcard.Engine;

import com.example.evan.projectcard.GameScreen.Listeners.BasicListener;

public abstract class Frame {
	String frameName;
	Game game;
	BasicListener listener;
	
	public Frame(Game game, String frameName){
		this.game = game;
		this.frameName = frameName;
	}
	
	public void launch(){
		if(game.getActivity() != null){
			game.log(frameName);
		}
		if(game.getActivity() != null){
			android.util.Log.d("frame", frameName);
		}
		//Log.add(frameName);
		execute();
	}

	public void setListener(BasicListener listener){
		this.listener = listener;
	}
	
	abstract public void execute();
	abstract public Frame getNextFrame();
}

