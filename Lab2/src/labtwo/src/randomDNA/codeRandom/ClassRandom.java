package labtwo.src.randomDNA.codeRandom;

import java.util.Random;

public class ClassRandom {
	public static void main(String[] args){
		Random random = new Random();
		int countAAA = 0;
		String[] arrayDNA = new String[] {"A","C","T","G"};
		for(int x=1;x<1000;x++){
			String kmerDNA="";
			for(int i=1;i<4;i++){
				int num = random.nextInt(4);
				String base = arrayDNA[num];
				kmerDNA = kmerDNA + base;
			}
			if(kmerDNA.equals("AAA")){
				countAAA++;
			}
			System.out.println(kmerDNA);
		}
		System.out.println(countAAA);
	}
}
