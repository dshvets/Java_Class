package toXML;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class Conversion {

	
	public static HashMap<String, Integer> createNodes (String inputFile) throws Exception{
		String line = null; //line of input file to be read
		
		HashMap<String, Integer> uniqueNodes = new HashMap<>();
		
		int ID = 1; //set ID counter, 1 reserved for root node
		String [] dataArray; //array of samples from file
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String firstLine = reader.readLine(); //first line includes header
		String [] headers = firstLine.split("\t"); //save headers for adding to node description
		
		while((line = reader.readLine())!= null){
			dataArray = line.split("\t");
			for(int x=0;x<5;x++){
				String nodeName = dataArray[x];//array containing the 5 node names in the current string
				String dictKey = nodeName + "\t"+headers[x]; //create key for dictionary
				if(!nodeName.equals("") && !uniqueNodes.containsKey(dictKey)){ //skip empty entries in file
					ID++;
					uniqueNodes.put(dictKey, ID);
				}
			}
		}
		reader.close();
		
		//System.out.println(uniqueNodes.size()); check dictionary size
		return uniqueNodes;
	}
	
	public static HashMap<String,Integer> edges (String givenFile, HashMap<String,Integer> map) throws Exception{
		
		HashMap<String,Integer> edgesMap = new HashMap<>(); // for storing edge results
		
		String newLine = null;
		BufferedReader lineReader = new BufferedReader(new FileReader(givenFile));
		String lineOne = lineReader.readLine(); //read first line
		String [] header = lineOne.split("\t"); //save headers
		String [] nodeArray;
		while((newLine = lineReader.readLine()) != null){
			nodeArray = newLine.split("\t");
			
			int source = 1;//set first source as 1 for the root node, this will get updated throughout the loop

			for(int y=0;y<5;y++){
				String sampleName = nodeArray[y];
				if(!sampleName.equals("")){
					String sampleType = header[y];
					String sampleKey = sampleName +"\t"+sampleType;
					int target = map.get(sampleKey); //set target using node dictionary to get the right ID
					String edgeRelation = source +"\t" + target;
					edgesMap.put(edgeRelation,1);
					source = target; //update the source for the next edge relationship
				}
			}
		}
		
		lineReader.close();
		//System.out.println(edgesMap.size()); //check that size of edges dictionary matches size of node dictionary
		return edgesMap;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String inputFile = "/Users/dahlia-shvets/Desktop/Fall.2015/Java/someRdpOut.tsv";
		String outputFile = "/Users/dahlia-shvets/Desktop/Fall.2015/Java/finalOutput.xml";
		HashMap<String, Integer> testing =  createNodes(inputFile);	
		HashMap<String,Integer>finalEdges = edges(inputFile,testing);
		
		BufferedWriter writeToFile = new BufferedWriter(new FileWriter(outputFile));
		
		//add necessary XML information to file
		writeToFile.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!-- taxonomic data  -->\n<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">\n<graph edgedefault=\"undirected\">\n<!-- data schema -->\n<key id=\"name\" for=\"node\" attr.name=\"name\" attr.type=\"string\"/>\n<key id=\"level\" for=\"node\" attr.name=\"level\" attr.type=\"string\"/>\n<!-- nodes -->\n");
		//add root node
		writeToFile.write("<node id=\"1\">\n<data key=\"name\">root</data>\n<data key=\"level\">root</data>\n</node>\n");
		
		
		for(Entry<String, Integer> entry: testing.entrySet()){
			String nodeKey = entry.getKey();
			String [] arrayData = nodeKey.split("\t");
			String finalName = arrayData[0];
			String finalType = arrayData[1];
			
			Integer finalID = entry.getValue();
			//add all the nodes
			writeToFile.write("<node id=\""+ finalID+"\">\n<data key=\"name\">"+finalName+"</data>\n<data key=\"level\">"+finalType+"</data>\n</node>\n");

		}

		
		for(Entry<String, Integer> entry: finalEdges.entrySet()){
			String finalKey = entry.getKey();
			String [] edgesArrayFinal = finalKey.split("\t");
			String finalSource = edgesArrayFinal[0];
			String finalTarget = edgesArrayFinal[1];

			writeToFile.write("<edge source=\""+ finalSource + "\" target=\""+ finalTarget + "\"></edge>\n");

		}
		//add last two lines
		writeToFile.write("</graph>\n</graphml>");
		writeToFile.close();
	}
}
