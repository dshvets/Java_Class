package fastaFilter;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FastaSequence{
	
	private String header;
	private String sequence;

	public FastaSequence(String header,String sequence){
		this.header=header;
		this.sequence=sequence;
	}
	
	public static List <FastaSequence> readFastaFile(String location) throws Exception{
		//Create list of Fasta Sequence objects for storing the objects once created
		List<FastaSequence> returnList = new ArrayList<FastaSequence>();
		
		BufferedReader reader = new BufferedReader(new FileReader(location));

		String thisLine = "";
		String sampleHeader = "";
		String sampleSequence = "";
		
		while ((thisLine = reader.readLine()) != null){
			
			if (thisLine.startsWith(">")){
				//Removes caret from start of each header
				sampleHeader = thisLine.substring(1);

			} else {
				sampleSequence = thisLine;
				//Instantiates new FastaSequence object using the header and sequence, and adds it to the list
				returnList.add(new FastaSequence(sampleHeader,sampleSequence));
				
			}
		}
		
		reader.close();
		
		return returnList;
	}

	public String getHeader() {
		return this.header;
	}

	public String getSequence(){
		return this.sequence;
	}
	
	//Method returns GC content of each sequence
	public float getGCRatio(){
		float total=this.sequence.length();
		float gc =0.0f;
		for(char x : this.sequence.toCharArray()){
			if(x == 'C' || x == 'G'){
				gc++;
			}
		}
		float ratio = gc / total;
		return ratio;
	}
	
	public float getLength(){
		return this.sequence.length();
	}

	public static void main(String[] args) throws Exception{
		
		List <FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/dahlia-shvets/Desktop/Fall.2015/Java/shortElegans.fasta");
		
		//Implements comparator class to order the FastaSequence objects based on the length or GC ratio
		Comparator<FastaSequence> sample = new Comparator<FastaSequence>(){
	
			public int compare(FastaSequence o1, FastaSequence o2) {
				return Float.compare(o1.getLength(), o2.getLength());
				//If want to sort by GC content do:
				//return Float.compare(o1.getGCRatio(),o2.getGCRatio());
			}
			
		};
		Collections.sort(fastaList,sample);
		for (FastaSequence seq : fastaList) {
		System.out.println(seq.getHeader());
		System.out.println(seq.getSequence());
		System.out.println(seq.getGCRatio());
		System.out.println(seq.getLength());
		}
	}
}