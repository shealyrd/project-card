package com.example.evan.projectcard.GameScreen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class KeywordCollection implements Iterable<String>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6658906075932448006L;
	ArrayList<String> keywords = new ArrayList<String>();
	
	public KeywordCollection(String...strings){
		for(String str: strings){
			add(str);
		}
	}

	public void add(String arg){
			keywords.add(arg);
	}
	
	public boolean contains(String arg){
		for(String str: keywords){
			if(str.equals(arg)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Iterator<String> iterator() {
		return keywords.iterator();
	}

	public void remove(String arg) {
		if(keywords.contains(arg)){
			keywords.remove(arg);
		}
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(String word: new HashSet<String>(keywords)){
			builder.append(word + ", ");
		}
		return builder.toString();
	}
	
	
}
