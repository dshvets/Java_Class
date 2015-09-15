package aminoAcidClass;

import java.util.Random;

///An amino acid game. First the user is asked for how many seconds they want to play the game. 
///Then an amino acid is selected at random from the amino acid array. 
///The user then has to input the letter that they think codes for that amino acid. 
///If they are correct, the game continues, their score is increased by one, and the time remaining is returned. 
///If they are wrong then the correct answer and their current score gets displayed and the game is over. 
///If they want to exit the game they just have to type quit. 

public class aaclass {
	public static void main(String[] args){
		Random random = new Random();
		String[] Name_Array = {
				"alanine","arginine", "asparagine", 
				"aspartic acid", "cysteine",
				"glutamine",  "glutamic acid",
				"glycine" ,"histidine","isoleucine",
				"leucine",  "lysine", "methionine", 
				"phenylalanine", "proline", 
				"serine","threonine","tryptophan", 
				"tyrosine", "valine"};
			String[] Letter_Array = { "A","R", "N", "D", "C", "Q", "E", 
					"G",  "H", "I", "L", "K", "M", "F", 
					"P", "S", "T", "W", "Y", "V" };
		System.out.println("For how many seconds would you like to play the amino acid game?");
		String timeInput = System.console().readLine().toUpperCase();
		float maxTime = Float.parseFloat(timeInput);
		System.out.println("Let's play, enter the symbol for the given amino acid: ");
		float totalTime=0;
		int score =0;
		while(totalTime < maxTime){
			long startTime = System.currentTimeMillis();
			int num = random.nextInt(20);
			String chosenAA = Name_Array[num];
			System.out.println(chosenAA+"\nYour answer:");
			String userInput = System.console().readLine().toUpperCase();
			String correctAns = Letter_Array[num];
			if(userInput.equals(correctAns)){
				score++;
			}else if(userInput.equals("QUIT")){
				System.out.println("We're sorry to see you go before the time has run out!");
				break;
			}
			else{
				System.out.println("WRONG, should be "+ correctAns+ "\nGame over with score of: "+score);
				break;
			}
			long endTime = System.currentTimeMillis();
			float diff = (endTime - startTime)/1000f;
			totalTime = totalTime + diff;
			float remainTime = maxTime - totalTime;
			System.out.println("Your Score="+score+"  " + remainTime +" seconds left");
		}

	}
	
}
