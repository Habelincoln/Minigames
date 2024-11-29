import java.util.*;

public class hangman {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
		String word = "";
		
		do {
		    
		    System.out.print("\033[H\033[2J"); //clear console
		    if (word.matches(".*\\d.*")){System.out.println("\u001B[31m"+"INVALID INPUT!!!");}
		    System.out.println("Welcome to hangman!");
	    	System.out.println("Enter Word:");
		    word = scanner.nextLine().toLowerCase();
		    
		}while(word.matches(".*\\d.*"));
		
		System.out.print("\033[H\033[2J"); //clear console
		System.out.flush();

		int len = word.length();
		
		String livesin = "";
		
	do {
		    
		    System.out.print("\033[H\033[2J"); //clear console
		    if (livesin.matches(".*[a-zA-Z].*")){System.out.println("\u001B[31m"+"INVALID INPUT!!!");}
		    System.out.println("Enter lives:");
		    livesin = scanner.nextLine();
		    
		}while(livesin.matches(".*[a-zA-Z].*"));	
		
		int lives = Integer.parseInt(livesin);

		String letter = "";
		String wrongGuesses = "";
		String correctGuesses = "";
		String multiWrongGuesses = "";
		
		
		
		String retWord = "-".repeat(len);
		
		
		
		for (int i = 0; i<len; i++) {
				if ((String.valueOf(word.charAt(i)).equals(" "))) {

					retWord = retWord.substring(0, i) + " " + retWord.substring(i+1);

				}

			}
		
		
		





        System.out.print("\033[H\033[2J"); 

        System.out.println(retWord);
		System.out.println("lives: " + "❤ ".repeat(lives));
		System.out.println(returnLetters(wrongGuesses, correctGuesses));

		while (!retWord.equals(word)) {



do {
		    
		    
		    if (letter.matches(".*\\d.*")){ System.out.print("\033[H\033[2J"); System.out.println("\u001B[31m"+"INVALID INPUT!!!"+"\u001B[0m");System.out.println(retWord);
	    	System.out.println("lives: " + "❤ ".repeat(lives));
	    	System.out.println(returnLetters(wrongGuesses, correctGuesses));}
	   
	    	
		    letter = scanner.nextLine().toLowerCase();
		   
		    
		} while(letter.matches(".*\\d.*"));


			
			
			
			
			
			System.out.print("\033[H\033[2J"); 
			
			if (letter.length() == 1){
			
			if (!word.contains(letter) && !wrongGuesses.contains(letter)) {
				wrongGuesses += letter;
			    lives --;
			}
             else if (!word.contains(letter) && wrongGuesses.contains(letter)) {
            
            System.out.println("\u001B[31m" + "You've already guessed "  + "\"" + letter + "\""+"!" + "\u001B[0m");
             }

			if (!correctGuesses.contains(letter) && word.contains(letter)) {
				correctGuesses += letter;
			}
		    else if (correctGuesses.contains(letter)) {
		        System.out.println("\u001B[32m" + "You've already correctly guessed "  + "\"" + letter + "\""+"!" + "\u001B[0m");
		    }
		



} else if (letter.length() > 1) {
    lives--;
    if (!multiWrongGuesses.contains(letter)) {
        
        if (multiWrongGuesses == ""){
            multiWrongGuesses += letter;
        }
        else {
        multiWrongGuesses += ", ";
        multiWrongGuesses += letter;
        }
    }
    
    else  if (multiWrongGuesses.contains(letter)) {
        lives ++;
        System.out.println("\u001B[31m"+"You've already guessed " + "\"" + letter + "\""+"!"+"\u001B[0m");
    }
    
  if (letter.equals(word)) {
      lives++;
				retWord = word;
				break;
			}  
    
} else { 
    
    lives--;
    
}
			
			if (lives == 0) {
				break;
			}

			for (int i = 0; i<len; i++) {
				if ((String.valueOf(word.charAt(i)).equals(letter))) {

					retWord = retWord.substring(0, i) + letter + retWord.substring(i+1);

				}

			}
			letter = "";
			System.out.println(retWord);
			System.out.println("lives: " + "❤ ".repeat(lives));
			System.out.println(returnLetters(wrongGuesses, correctGuesses));
            System.out.println("Incorrect word guesses: " +multiWrongGuesses);
		}
		
		System.out.print("\033[H\033[2J"); 

		if (retWord.equals(word)) {
			System.out.println("You Win!!!");
			System.out.println("The word was: " + word);
			if (lives == 1) {
			    System.out.println("You had " + lives + " life remaining");
		}
		else if (lives > 1) {
		    System.out.println("You had " + lives + " lives remaining");
		}
		}	
		else {
			System.out.println("Game over. You lose.");
			System.out.println("The word was: " + word);
		}

scanner.close();

}	
    public static String returnLetters(String LTH, String correctGuesses) {
		StringBuilder r = new StringBuilder();
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
			r.append(correctGuesses.toUpperCase().indexOf(c) > -1 ? "\033[1;32m" + c : LTH.toUpperCase().indexOf(c) > -1 ? "\033[1;31m" + c : c).append("\033[0m");
		return r.toString();
    
    }
}
