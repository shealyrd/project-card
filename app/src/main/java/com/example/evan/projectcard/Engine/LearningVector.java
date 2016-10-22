package com.example.evan.projectcard.Engine;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class LearningVector implements Iterable, Serializable{
	    double[] entries;
	    
	    public LearningVector(int dim){
	        entries = new double[dim];
	    }
	    
	    public LearningVector(double[] f){
	        entries = f;
	    }
	    
	    public void add(LearningVector arg){
	        if(arg.getDim() != getDim()){
	            System.out.println("Addition aborted: The vector and the input have different dimensions.");
	        }
	        else{
	            for(int i = 0; i < entries.length; i++){
	                entries[i] = entries[i] + arg.get(i);
	            }
	        }
	    }
	    
	    public double get(int index){
	        try{
	            return entries[index];
	        }
	        catch(ArrayIndexOutOfBoundsException e){
	            System.out.println("Array out of bounds exception. Dim:" + getDim() + " Attempted:" + index);
	            return 0;
	        }
	    }
	    
	    public double magnitude(){
	        double result = 0;
	        for(double f: entries){
	            result = result + Math.pow(f, 2);
	        }
	        result = Math.pow(result, 0.5);
	        return result;
	    }
	    
	    public void scale(double scalar){
	        for(int i = 0; i < entries.length; i++){
	            entries[i] = entries[i] * scalar;
	        }
	    }
	    
	    public void normalize(){
	        scale(1/(magnitude()));
	    }
	    
	    public double[] asArray(){
	        return entries;
	    }
	    
	    public int getDim(){
	        return entries.length;
	    }

		@Override
		public Iterator<double[]> iterator() {
			return Arrays.asList(entries).iterator();
		}
	    
	}
