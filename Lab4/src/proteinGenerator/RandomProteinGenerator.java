package proteinGenerator;

import java.util.Random;

public class RandomProteinGenerator 
{
	float [] frequency;
	float [] NOTUNIF = {0.072658f, 0.024692f, 0.050007f, 0.061087f, 
			0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
			0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
			0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f, 0.032955f};
	float [] UNIF = {.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f,
			.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f,.05f};
	String protSeq;
	int length;
	boolean uniformFreq;
	char [] NUCLEOTIDES= { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'Y' };
	//nukeString is the conversion of the NUCLEOTIDES array into a string in order to easily get the index of the array of characters
	String nukeString = new String(this.NUCLEOTIDES);
	int numIterations;
	
	
	public RandomProteinGenerator(boolean uniformFreq)
	{
		this.uniformFreq = uniformFreq; //set global
		//Using the correct array of frequency values based on true or false
		if(this.uniformFreq == true){
			this.frequency = UNIF;
		}else{
			this.frequency = NOTUNIF; 
		}
	}
	
	public String getRandomProtein(int length)
	{
		this.length = length;
		Random RANDOM = new Random();
		String randoProtein = "";
		for(int a=0; a < length; a++){
			float f = RANDOM.nextFloat();
			float sum = 0.0f;
			for(int x=0; x < NUCLEOTIDES.length; x++){
				sum += this.frequency[x];
				if(f <= sum){
					randoProtein += Character.toString(NUCLEOTIDES[x]);
					break;
				}
			}
		}
		return randoProtein;
	}
	
	/*Returns the probability of seeing the given sequence given the underlying residue frequencies represented by
	 * this class.  For example, if useUniformFrequencies==false in constructor, the probability of "AC" would be 0.072658 *  0.024692*/
	public double getExpectedFrequency(String protSeq)
	{
		float protProb=1.0f;
		this.protSeq = protSeq;
		//For each letter in the protein Sequence, we get its index in the Nucleotide array, then use
		//that index to obtain the correct frequency and multiply all of the frequencies together
		for(char x: protSeq.toCharArray()){			
			int index = this.nukeString.indexOf(x);
			float prob = this.frequency[index];
			protProb = protProb * prob;
		}
		return protProb;
	}
	/*calls getRandomProtein() numIterations times generating a protein with length equal to protSeq.length().
	 Returns the number of time protSeq was observed / numIterations*/
	
	public double getFrequencyFromSimulation( String protSeq, int numIterations )
	{
		this.protSeq = protSeq;
		this.numIterations = numIterations;
		int lengthProtein = protSeq.length();
		float countProtein=0;
		for(int x=0;x < numIterations; x++){
			String generateProtein = getRandomProtein(lengthProtein);
			if(generateProtein.equals(protSeq)){
				countProtein++;
			}
			
		}
		float result = countProtein / numIterations;
		return result;
	}
	
	public static void main(String[] args) throws Exception
	{
		String testProtein = "ACD";
		int numIterations =  10000000;
		RandomProteinGenerator realisticGen = new RandomProteinGenerator(false);
		System.out.println(realisticGen.getExpectedFrequency(testProtein));
		System.out.println(realisticGen.getFrequencyFromSimulation(testProtein,numIterations));	
		
		RandomProteinGenerator uniformGen = new RandomProteinGenerator(true);
		System.out.println(uniformGen.getExpectedFrequency(testProtein));  
		System.out.println(uniformGen.getFrequencyFromSimulation(testProtein,numIterations));
	}
}