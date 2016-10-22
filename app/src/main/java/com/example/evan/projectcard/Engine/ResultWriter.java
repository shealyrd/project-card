package com.example.evan.projectcard.Engine;
import java.io.IOException;

public class ResultWriter {
	/*
	static final String filename = "output.xml";
	
	public static void writeResult(FeatureCollection collection, double reward){
		String result = writeXMLEntry(collection, reward);
		try {
			Files.write(Paths.get(filename), result.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String writeXMLEntry(FeatureCollection collection, double reward){
		StringBuilder builder = new StringBuilder();
		builder.append("<Entry>");
		builder.append("\n\t<Features>");
		int count = 0;
		for(Feature feature: collection){
			builder.append("\n\t\t<Feature ID = \"" + count + "\" Weight = \"" +  feature.weight + "\" />");
			count++;
		}
		builder.append("\n\t</Features>");
		builder.append("\n\t<Reward Value = \"" + reward+ "\" />");
		builder.append("\n</Entry>\n");
		return builder.toString();
	}
	
	*/
}
