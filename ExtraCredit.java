package labtwo;

import java.util.Random;

public class ExtraCredit {
	public static void main(String[] args){
		Random random = new Random();
		int countAAA = 0;
		for(int x=1;x<1000;x++){
			String kmerDNA="";
			for(int i=1;i<4;i++){
				int randNum = random.nextInt(100);
				if(randNum <= 10){
					kmerDNA = kmerDNA + "T";
				}else if(randNum >= 11 && randNum <=22){
					kmerDNA = kmerDNA + "A";
				}else if(randNum >=23 && randNum <=60){
					kmerDNA = kmerDNA + "C";
				}else{
					kmerDNA=kmerDNA+"G";
				}
			}
			System.out.println(kmerDNA);
			if(kmerDNA.equals("AAA")){
				countAAA++;
			}
		}
		System.out.println(countAAA);
	}
}
	

