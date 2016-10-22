package com.example.evan.projectcard.Engine;

public class Log {
	static boolean testing = false;

	public static void add(String string) {
		if(!testing){
			//System.out.println(string);
		}
	}

	public static void error(String string){
		//Log.supAdd("[ERROR]: " + string);
		//GlobalScanner.nextLine();
	}

	public static void setTestingMode() {
		testing = true;
	}

	public static void supAdd(String string) {
		//System.out.println(string);
	}
	
	public static void supAdd(int i) {
		//System.out.println(i);
	}
	
}
