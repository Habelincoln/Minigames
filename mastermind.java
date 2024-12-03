import java.util.*;

public class mastermind {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int lives = 0;
			System.out.println("Welcome to Mastermind!");
			System.out.println("Choose code: 4 pegs, 6 possible colors. Colors are: \u001B[36mBlue\u001B[0m, \u001B[32mGreen\u001B[0m, \u001B[33mYellow\u001B[0m, \u001B[31mRed\u001B[0m, \u001B[90mGray\u001B[0m, and \u001B[37mWhite\u001B[0m");

			System.out.println("Peg 1:");
			String code1 = scanner.nextLine().toLowerCase();
			System.out.println("Peg 2:");
			String code2 = scanner.nextLine().toLowerCase();
			System.out.println("Peg 3:");
			String code3 = scanner.nextLine().toLowerCase();
			System.out.println("Peg 4:");
			String code4 = scanner.nextLine().toLowerCase();

			System.out.print("\033[H\033[2J");
			System.out.flush();
			
			System.out.println("Enter Lives:");
			int livesInput = scanner.nextInt();
			scanner.nextLine();
   
   
			System.out.print("\033[H\033[2J");
			System.out.flush();

			String[] code = {code1, code2, code3, code4}; // store codes

			while(true) {
				System.out.println("Possible colors: \u001B[36mBlue\u001B[0m, \u001B[32mGreen\u001B[0m, \u001B[33mYellow\u001B[0m, \u001B[31mRed\u001B[0m, \u001B[90mGray\u001B[0m, and \u001B[37mWhite\u001B[0m");
				System.out.println("Guess 1:");
				String guess1 = scanner.nextLine().toLowerCase();
				System.out.println("Guess 2:");
				String guess2 = scanner.nextLine().toLowerCase();
				System.out.println("Guess 3:");
				String guess3 = scanner.nextLine().toLowerCase();
				System.out.println("Guess 4:");
				String guess4 = scanner.nextLine().toLowerCase();

				String[] guess = {guess1, guess2, guess3, guess4}; // store guesses

				boolean[] codeMatched = new boolean[4]; // boolean array to store whether each code is matched yet
				boolean[] guessMatched = new boolean[4]; // same to store whether each guess is matched yet

				int exactMatches = 0;
				int colorMatches = 0;

				//look for exact matches
				for (int i = 0; i < 4; i++) {
					if (guess[i].equals(code[i])) {
						exactMatches ++;
						codeMatched[i] = true; // this code will no longer be checked, as its found it match.
						guessMatched[i] = true; // this guess is done checking, as it's been matched
					}
				}
				//look for color matches
				for (int i = 0; i < 4; i++) {
					if (!guessMatched[i]) { // ignore if already exact match
						for (int j = 0; j < 4; j++) { // new index, to cycle through the 4 answers while cycling through the 4 guesses

							if (!codeMatched[j] && guess[i].equals(code[j])) {
								colorMatches ++;
								codeMatched[j] = true; // mark this peg as matched
								break;

							}

						}
					}
				}

				// output results:
				System.out.println("Exact Matches: " + exactMatches);
				System.out.println("Color Matches (wrong spot): " + colorMatches);
				System.out.println("Lives Remaining: " + (livesInput-lives));

				// win if all correct
				if (exactMatches == 4) {
					System.out.println("You win!");
					break;
				}

				// lose if out of lives
				else if (lives == livesInput) {
					System.out.println("The code was: " + code[0] + " " +code[1] +" "+ code[2] +" "+ code[3]);
					break;
				}
				// lose a life if game not over
				else {
					lives++;
				}
			}
			scanner.close();
		}
	}
}
