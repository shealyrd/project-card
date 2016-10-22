package com.example.evan.projectcard.Engine;
import java.util.Random;
import java.util.Scanner;

public class GlobalScanner {
	static Scanner scan = new Scanner(System.in);
	static Game game;

	public static String nextLine(){
		return scan.nextLine();
	}
	
	public static Direction nextDirection(){
		if(game instanceof ComVsComGame){
			Random rn = new Random();
			return Direction.values()[rn.nextInt(100) % 4];
		}
		String result = scan.nextLine();
		while(true){
				if(result.equals("UP")){
					return Direction.UP;
				}
				if(result.equals("RIGHT")){
					return Direction.RIGHT;
				}
				if(result.equals("DOWN")){
					return Direction.DOWN;
				}
				if(result.equals("LEFT")){
					return Direction.LEFT;
				}
				Log.add("Not a valid direction. Please try again.");
				result = scan.nextLine();
			}
		}
	
	public static int getInt(){
		if(game instanceof ComVsComGame){
			Random rn = new Random();
			return rn.nextInt(18);
		}
		String result = scan.nextLine();
		while(true){
			if(Algorithmics.isInteger(result)){
				int verifiedInt = Integer.parseInt(result);
					return verifiedInt;
			}
			Log.add("Not a valid index. Please try again.");
			result = scan.nextLine();
		}
	}
	
	public static void setGame(Game thisGame){
		game = thisGame;
	}
	
	public static int getInt(int max){
		if(game instanceof ComVsComGame){
			Random rn = new Random();
			int i = rn.nextInt(max);
			return i;
		}
		String result = scan.nextLine();
		while(true){
			if(Algorithmics.isInteger(result)){
				int verifiedInt = Integer.parseInt(result);
					return verifiedInt;
			}
			Log.add("Not a valid index. Please try again.");
			result = scan.nextLine();
		}
	}
	

}
