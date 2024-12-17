import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
        clearScreen();
        println("Welcome to the Game Hub!");
	println("Game select: Hangman (1), MasterMind (2), NumberGuesser (3), 2048 (4), Wordle (5)");
	
	    Scanner input = new Scanner(System.in);
	    String gameChoice = input.nextLine().toLowerCase();
            switch (gameChoice) {
                case "hangman":
                case "1":
                    hangman();
                    break;
                case "mastermind":
                case "2":
                    mastermind();
                    break;
                case "numberguesser":
                case "3":
                    numberguesser();
                    break;
                case "2048":
                case "4":
                    game2048();
                    break;
                case "wordle":
                case "5":
                    wordle();
                    break;
                default:
                    println("Invalid game name");
                    break;
	}
	input.close();
	}

public static void hangman() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Set<String> multiWrongGuesses = new HashSet<>();
		String word = "";
		String livesLogo = " L";
		do {
		    
		    print("\033[H\033[2J"); //clear console
		    if (word.matches(".*\\d.*")){println("\u001B[31m"+"INVALID INPUT!!!" +"\u001B[0m" );}
		    println("Welcome to hangman!");
	    	println("Enter Word:");
		    word = scanner.nextLine().toLowerCase();
		    
		}
		while(word.matches(".*\\d.*"));
		
		clearScreen();

		int len = word.length();
		
		String livesin = "";
		
	do {
		    
		    print("\033[H\033[2J"); //clear console
		    if (livesin.matches(".*[a-zA-Z].*")){println("\u001B[31m"+"INVALID INPUT!!!");}
		    println("Enter lives:");
		    livesin = scanner.nextLine();
		    
		}while(livesin.matches(".*[a-zA-Z].*"));	
		
		int lives = Integer.parseInt(livesin);

		String letter = "";
		String wrongGuesses = "";
		String correctGuesses = "";
		String dash = "-";
		
		
		String retWord = dash.repeat(len);
		
		
		
		for (int i = 0; i<len; i++) {
				if ((String.valueOf(word.charAt(i)).equals(" "))) {

					retWord = retWord.substring(0, i) + " " + retWord.substring(i+1);

				}

			}


        print("\033[H\033[2J"); //print info

        println(retWord);
		println("lives: " + livesLogo.repeat(lives));
		println(returnLetters(wrongGuesses, correctGuesses));

		while (!retWord.equals(word)) {
do {
		    		    
		    if (letter.matches(".*\\d.*")){ print("\033[H\033[2J"); println("\u001B[31m"+"INVALID INPUT!!!"+"\u001B[0m");println(retWord);
	    	println("lives: " + livesLogo.repeat(lives));
	    	println(returnLetters(wrongGuesses, correctGuesses));}
	   
		    letter = scanner.nextLine().toLowerCase();
            if (letter.equals(secretCode2048)) {
                println("Word is: " + word);
                println("2...");
                Thread.sleep(1000);
                println("1...");
                Thread.sleep(1000);
            } 
		    
		} while(letter.matches(".*\\d.*"));
			
			
			print("\033[H\033[2J"); 
			
			if (letter.length() <= 1){
                if (letter.isEmpty()) {
                println("\u001B[31m" + "You didn't enter anything!" + "\u001B[0m");
                }
			else {
			if (!word.contains(letter) && !wrongGuesses.contains(letter)) {
				wrongGuesses += letter;
			    lives --;
			}
             else if ( !word.contains(letter) && wrongGuesses.contains(letter) ) {
            
            println("\u001B[31m" + "You've already guessed "  + "\"" + letter + "\""+"!" + "\u001B[0m");
             }

			if (!correctGuesses.contains(letter) && word.contains(letter)) {
				correctGuesses += letter;
			}
		    else if (correctGuesses.contains(letter)) {
		        println("\u001B[32m" + "You've already correctly guessed "  + "\"" + letter + "\""+"!" + "\u001B[0m");
		    }
            }

} else if (letter.length() > 1) {
    
    if (!multiWrongGuesses.contains(letter)) {
        lives--;
        multiWrongGuesses.add(letter);
    }
    
    else {
        
        println("\u001B[31m"+"You've already guessed " + "\"" + letter + "\""+"!"+"\u001B[0m");
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
			println(retWord);
            
			println("lives: " + livesLogo.repeat(lives));
			println(returnLetters(wrongGuesses, correctGuesses));
            println("Incorrect word guesses: " +multiWrongGuesses);
		}
		
		print("\033[H\033[2J"); 

		if (retWord.equals(word)) {
			println("You Win!!!");
			println("The word was: " + word);
			if (lives == 1) {
			    println("You had " + lives + " life remaining");
		}
		else if (lives > 1) {
		    println("You had " + lives + " lives remaining");
		}
		}	
		else {
			println("Game over. You lose.");
			println("The word was: " + word);
		}

scanner.close();

}	
    public static String returnLetters(String LTH, String correctGuesses) {
		StringBuilder r = new StringBuilder();
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
			r.append(correctGuesses.toUpperCase().indexOf(c) > -1 ? "\033[1;32m" + c : LTH.toUpperCase().indexOf(c) > -1 ? "\033[1;31m" + c : c).append("\033[0m");
		return r.toString();
    }
    
    



          public static void mastermind() {
             Scanner scanner = new Scanner(System.in);
        
        clearScreen();

        println("\u001B[33m" +"Welcome to Mastermind!" + "\u001B[0m");
		String msg = "4 pegs. 6 possible colors: \u001B[36mBlue\u001B[0m, \u001B[32mGreen\u001B[0m, \u001B[33mYellow\u001B[0m, \u001B[31mRed\u001B[0m, \u001B[90mGray\u001B[0m, and \u001B[37mWhite\u001B[0m";

        int lives = 0;
		println("Choose code");
        println("4 pegs. 6 possible colors: \u001B[36mBlue\u001B[0m, \u001B[32mGreen\u001B[0m, \u001B[33mYellow\u001B[0m, \u001B[31mRed\u001B[0m, \u001B[90mGray\u001B[0m, and \u001B[37mWhite\u001B[0m");
		print("Set peg 1: ");
		String code1 = scanner.nextLine().toLowerCase();
		while (!checkCode1(code1)) {
            clearScreen();
            println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
            println(msg);           
            print("Set peg 1: ");
            code1 = scanner.nextLine().toLowerCase();

        }
		clearScreen();
        println(msg);
		print("Set peg 2:");
		String code2 = scanner.nextLine().toLowerCase();
		while (!checkCode2(code2)) {
            clearScreen();
            println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
            println(msg);           
            print("Set peg 2: ");
            code2 = scanner.nextLine().toLowerCase();
        }
		clearScreen();
        println(msg);
		print("Set peg 3:");
		String code3 = scanner.nextLine().toLowerCase();
		while (!checkCode3(code3)) {
            clearScreen();
            println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
            println(msg);           
            print("Set peg 3: ");
            code3 = scanner.nextLine().toLowerCase();
            
        }
		clearScreen();
        println(msg);
		print("Set peg 4:");
		String code4 = scanner.nextLine().toLowerCase();
	    
		while (!checkCode4(code4)) {
            clearScreen();
            println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
            println(msg);           
            println("Set peg 4: ");
            code4 = scanner.nextLine().toLowerCase();
            
        }
	   clearScreen();
	    
	    println("Enter Lives (Default 10):");
		int livesInput = scanner.nextInt();
        
		scanner.nextLine();
	
		clearScreen();

		String[] code = {code1, code2, code3, code4}; // store codes

		while(true) {
			
			
            println(msg);           
			print("Guess 1: ");
			String guess1 = scanner.nextLine().toLowerCase();
            while (!checkGuess1(guess1)) {
                clearScreen();
                println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
                println(msg);
                print("Guess 1: ");       
                guess1 = scanner.nextLine().toLowerCase();
            
            }
            clearScreen();
            println(msg);
            print("Guess 2: ");
			String guess2 = scanner.nextLine().toLowerCase();
            while (!checkGuess2(guess2)) {
                clearScreen();
                println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
                println(msg);
                print("Guess 2: ");        
                guess2 = scanner.nextLine().toLowerCase();
            }
            clearScreen();
            println(msg);
            print("Guess 3: ");
			String guess3 = scanner.nextLine().toLowerCase();
            while (!checkGuess3(guess3)) {
                clearScreen();
                println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
                println(msg);     
                print("Guess 3: ");      
                guess3 = scanner.nextLine().toLowerCase();
            }
            clearScreen();
            println(msg);
            print("Guess 4: ");
			String guess4 = scanner.nextLine().toLowerCase();
            while (!checkGuess4(guess4)) {
               clearScreen();
                println("\u001B[31m" + "Invalid input! Check for typos." + "\u001B[0m");
                println(msg);       
                print("Guess 4: ");    
                guess4 = scanner.nextLine().toLowerCase();
            }
            clearScreen();
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
            println(guess1 + " " + guess2 + " " + guess3 + " " + guess4);
            println("Exact Matches: " + exactMatches);
			println("Color Matches (wrong spot): " + colorMatches);
			println("Lives Remaining: " + (livesInput-lives));

			// win if all correct
			if (exactMatches == 4) {
				println("You win!");
				break;
			}

			// lose if out of lives
			else if (lives == livesInput) {
				println("Game over. You lose.");
				println("The code was: " + code[0] + " " +code[1] + " " + code[2] + " " + code[3]);
				break;
			}
			// lose a life if game not over
			else {
				lives++;
			}
		}

    scanner.close();
}
public static boolean checkCode1 (String code1) {
    if (!code1.equals("red") &&!code1.equals("blue") && !code1.equals ("green") && !code1.equals("yellow") && !code1.equals ("white") && !code1.equals("gray")) {
        return false;

    }
    return true;
}
public static boolean checkCode2 (String code2) {
    if (!code2.equals ("red") &&!code2.equals ("blue") && !code2.equals ("green") && !code2.equals ("yellow") && !code2.equals ("white") && !code2.equals ("gray")){
        return false;

    }
    return true;
}
public static boolean checkCode3 (String code3) {
    if (!code3.equals ("red") &&!code3.equals ("blue") && !code3.equals ("green") && !code3.equals ("yellow") && !code3.equals ("white") && !code3.equals ("gray")){
        return false;

    }
    return true;
}
public static boolean checkCode4 (String code4) {
    if (!code4.equals ("red") &&!code4.equals ("blue") && !code4.equals ("green") && !code4.equals ("yellow") && !code4.equals ("white") && !code4.equals ("gray")){
        return false;

    }
    return true;
}




public static boolean checkGuess1 (String guess1) {
    if (!guess1.equals("red") &&!guess1.equals("blue") && !guess1.equals ("green") && !guess1.equals("yellow") && !guess1.equals ("white") && !guess1.equals("gray")) {
        return false;

    }
    return true;
}
public static boolean checkGuess2 (String guess2) {
    if (!guess2.equals ("red") &&!guess2.equals ("blue") && !guess2.equals ("green") && !guess2.equals ("yellow") && !guess2.equals ("white") && !guess2.equals ("gray")){
        return false;

    }
    return true;
}
public static boolean checkGuess3 (String guess3) {
    if (!guess3.equals ("red") &&!guess3.equals ("blue") && !guess3.equals ("green") && !guess3.equals ("yellow") && !guess3.equals ("white") && !guess3.equals ("gray")){
        return false;

    }
    return true;
}
public static boolean checkGuess4 (String guess4) {
    if (!guess4.equals ("red") &&!guess4.equals ("blue") && !guess4.equals ("green") && !guess4.equals ("yellow") && !guess4.equals ("white") && !guess4.equals ("gray")){
        return false;

    }
    return true;
}

public static void numberguesser() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
        
        clearScreen();
        println("Welcome to Number Guesser!");
		println("Random 1-100 (1) or You choose (2)?");

		int gameType = scanner.nextInt();

		int answer = 0;

		while (gameType != 1 && gameType != 2) {
			println("Please choose 1 or 2.");
			gameType = scanner.nextInt();
		}


		if (gameType == 1) {
			answer = (int) (Math.random() *100) + 1;

		}

		else if (gameType == 2) {
			println("Enter the number to be guessed: ");
			answer = scanner.nextInt();
			clearScreen();

		}

        final double secretCode = 7187448310.0;
		double input;
		int count = 0;
		println("Enter your first guess: ");
		do {

			input = scanner.nextDouble();
			if (input < answer && input != secretCode) {
				println("Too low!!! try again");
                count++;
			}
			if (input > answer && input != secretCode) {
				println("Too high! sound familiar?");
                count++;
			}
            if (input == secretCode) {
                println("Answer is: " + answer);
                Thread.sleep(1000);
                println("2...");
                Thread.sleep(1000);
                println("1...");
                Thread.sleep(1000);
                clearScreen();
                println("Enter your next guess: ");
            }
			

		} while (input != answer);

        count++;
		println("Correct! finally");

		if (count == 1) {
			System.out.printf("it only took you %d try...too many\n", count);
		} else {
			System.out.printf("it only took you %d tries...too many\n", count);
		}

    scanner.close();
    }
private static final Map<Integer, String[]> colorMap = new HashMap<>();
   private static int score = 0;
   private static int gameMode = 0;
   public static boolean haveWon = false;
   public static int winCon = 2048;
    public static int menuChoice = 7;
    public static int gameType = 0;
    public static boolean endGame = false;
    
        // Initialize colorMap with the number, HEX background color, and text color
        static  {
        
        colorMap.put (2, new String[] {"#eee4daff", "#776e65ff"});
        colorMap.put (4, new String[] {"#ded3bdff", "#776e65ff"});
        colorMap.put (8, new String[] {"#f2b179ff", "#ffffffff"});
        colorMap.put (16, new String[] {"#f59563ff", "#ffffffff"});
        colorMap.put (32, new String[] {"#f67c5fff", "#ffffffff"});
        colorMap.put (64, new String[] {"#f65e3bff", "#ffffffff"});
        colorMap.put (128, new String[] {"#edcf72ff", "#ffffffff"});
        colorMap.put (256, new String[] {"#edcc61ff", "#ffffffff"});
        colorMap.put (512, new String[] {"#edc850ff", "#ffffffff"});
        colorMap.put (1024, new String[] {"#edc53fff", "#ffffffff"});
        colorMap.put (2048, new String[] {"#edc22eff", "#ffffffff"});
        colorMap.put (4096, new String[] {"#b586b4ff", "#ffffffff"});
        colorMap.put (8192, new String[] {"#a861abff", "#ffffffff"});
        colorMap.put (16384, new String[] {"#a048a3ff", "#ffffffff"});
        colorMap.put (32768, new String[] {"#800080ff", "#ffffffff"});
        colorMap.put (65536, new String[] {"#600046ff", "#ffffffff"});
        colorMap.put (131072, new String[] {"#8b86e3ff", "#ffffffff"});
    } 

static Scanner scanner = new Scanner(System.in);
 
 public static int bSize = 0;
 final static String secretCode2048 = "7187448310";
 final static String secretCode2048U = "7187448310u";

    public static void game2048 () throws InterruptedException {
      int[][]board;
      while(true) {
        clearScreen();
        println("(1) Play");
        println("(2) Custom");
         gameType = scanner.nextInt();
        if (gameType==2) {
            
        println("Enter Spawn Per Move (Default 1):");
        
        gameMode = scanner.nextInt();
        scanner.nextLine();
        
        println("Enter Board Size (Default 4):");
        bSize = scanner.nextInt();
        scanner.nextLine();
        board = new int[bSize][bSize];
        addRandomNumber(board);
        break;
        }
       else if (gameType==1) {
            gameMode = 1;
            bSize = 4;
            board = new int[bSize][bSize];
            addRandomNumber(board);
            break;
        }
        
      }
        
        int[][] originalBoard = cloneBoard(board);
        int[][] undoBoard = board;
        int cheatValue;
    printBoard(board);
    while (true) {   
         
        String direction = scanner.nextLine().trim().toLowerCase();
        if (direction.equals(secretCode2048)) {
            board [0][0] = 2048;
        }
        if (direction.equals(secretCode2048U)) {
            println("Cheat options: 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072");
            print("Enter value: ");
            cheatValue = scanner.nextInt();
            if (cheatValue != 2  && cheatValue != 4 && cheatValue != 8 && cheatValue != 16 && cheatValue != 32 && cheatValue != 64 && cheatValue != 128 && cheatValue != 256 && cheatValue != 512 && cheatValue != 1024 && cheatValue != 2048 && cheatValue != 4096 && cheatValue != 8192 && cheatValue != 16384 && cheatValue != 32768 && cheatValue != 65536 && cheatValue != 131072) {
                
                continue;
            }
            for (int l=0; l<bSize; l++) {
                   for (int b=0; b<bSize; b++) {
                    board[l][b] = cheatValue;
                }
            }
        }
         if (direction.contains("m")) {
        
        while (!endGame) {
    try {
        println("Menu:");
        println("(0) Back to game");
        println("(1) Exit game");
        println("(2) Change win condition");
        println("(3) Undo last move");
        println("(4) Restart game");
        print("Enter your choice: ");
        
        menuChoice = Integer.parseInt(scanner.nextLine().trim());
        
        if (menuChoice >= 0 && menuChoice <= 4) {
            break; 
        } else {
            println("\u001b[31mInvalid Input. Please enter a number between 0 and 4.\u001b[0m");
            Thread.sleep(500);
        
    } } catch (NumberFormatException e) {
        println("\u001b[31mInvalid Input. Please enter a valid number.\u001b[0m");
        Thread.sleep(500);
        
    }
}
        switch (menuChoice){
        case 0: 
            printBoard(board);
        direction = scanner.nextLine();
            break;
        
        
        case 1:
            clearScreen();
            printBoard(board);
            endGame = true;
            break;
        
        case 2:
            println("Enter new win condition (Current is " + winCon +"):");
            winCon = scanner.nextInt();
            println("Saved. New win condition is " + winCon);
            break;
        
        case 3:
            
                board = undoBoard;
            clearScreen();
            printBoard(board);
            break;
        
        case 4:
            for (int i=0;i<bSize;i++){
                for(int j=0;j<bSize;j++){
                    board[i][j]=0;
                }
            }
        default:
            println("Invalid choice. Please enter a number between 0 and 4.");
            break;
        }
          if (endGame) {
          break;
      }  
            
        }
      
      
       printlni(score);
        printBoard(board);
            shiftArray(board, direction);
            merge(board, direction);
            shiftArray(board, direction);
            
             boolean boardChanged = !Arrays.deepEquals(originalBoard, board);
            
            if (boardChanged && menuChoice != 0 && menuChoice != 1 && menuChoice != 2 && menuChoice != 3) {
                addRandomNumber(board);
                undoBoard = originalBoard;
                
                
            }
            menuChoice = 7; //just to set it to an irrelevant value
            
            printBoard(board);
            
            
            if (checkWin(board) && !haveWon) {
                println("You Win!");
                print("Do you want to continue playing? (y/n): ");
                
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("y")) {
                clearScreen();
                haveWon = true;
                printBoard(board);
            }
            else if (response.equals("n")) {
                println("You Win!");
                break;
            }
            else {
                println("Invalid input. Please enter y or n.");
            }
            }
            if (checkLoss(board)){
                println("You lose.");
                break;
            }
    }
        
    }

public static void merge (int[][]board, String direction) {
    
switch (direction) {
         
  
  case "w": 
             
      for (int col = 0; col < bSize; col++){
      for (int row = 0; row < bSize - 1 ; row++) {
                     
        if (board[row][col] != 0 && board[row][col] == board[row + 1][col]){
            board[row + 1][col] *= 2;
            score += board[row+1][col];
            board[row][col] = 0;
        }             
                     
                 }
             }
    break;
     
    
    case "s":
        
         for (int col = 0; col < bSize; col++){
      for (int row = bSize - 1; row > 0 ; row--) {
                     
        if (board[row][col] != 0 && board[row][col] == board[row - 1][col]){
            board[row - 1][col] *= 2;
            score += board[row-1][col];
            board[row][col] = 0;
        }             
                     
                 }
             }
    break;
    
    
    case "d":
    
     for (int row = 0; row < bSize ; row++){
     for (int col = bSize - 1; col > 0; col--){
     
                     
        if (board[row][col] != 0 && board[row][col] == board[row][col - 1]){
            board[row][col - 1] *= 2;
            score += board [row][col-1];
            board[row][col] = 0;
        }             
                     
                 }
             }
    
    break;
    
    
    case "a":
       
     for (int row = 0; row < bSize; row++){
      for (int col = 0; col < bSize - 1 ; col++) {
                     
        if (board[row][col] != 0 && board[row][col] == board[row][col + 1]){
            board[row][col+1] *= 2;
            score += board[row][col+1];
            board[row][col] = 0;
        }            
                     
                 }
             }
   
    break;
    
    default: println("Error");
    
}
      
    }
    
public static void printBoard(int[][] board) {
     printlni(score);
    // Find the largest number in the board to define cell width
    int largestNum = 0;
    for (int i = 0; i < bSize; i++) {
        for (int j = 0; j < bSize; j++) {
            largestNum = Math.max(largestNum, board[i][j]);
        }
    }

    // Convert largestNum to a string to calculate its length
    String largestNumStr = Integer.toString(largestNum);
    final int cellWidth = largestNumStr.length() * 2 + 1; // Add extra space for the largest number

    // Define colors for the background and reset ANSI codes
    String wallColor = "\033[48;2;188;174;161m";
    String resetColor = "\033[0m";

    // Define the format for empty cells
    String emptyCell = String.format("%" + cellWidth + "s", " "); // empty cell format

   
    clearScreen();

println("Score: "+ score);

    // Print top wall (board separator)
    print(wallColor + " " + resetColor);
    for (int i = 0; i < bSize; i++) {
        print((wallColor + " " + resetColor).repeat(cellWidth) + wallColor + " " + resetColor);
    }
    
println("");
    // Print board content row by row
    for (int[] row : board) {
        print(wallColor + " " + resetColor); // Start of the row
        for (int num : row) {
            // For non-zero values, print the number centered within its cell
            String cellContent = (num == 0) ? emptyCell : centerNumberInCell(num, cellWidth);
            printFormat(num);
            print(cellContent + wallColor + " " + resetColor); // Print the cell
        }
        println(""); // New line after each row

        // Print a separator line after each row
        print(wallColor + " " + resetColor);
        for (int i = 0; i < bSize; i++) {
            print((wallColor + " " + resetColor).repeat(cellWidth) + wallColor + " " + resetColor);
        }
        println(""); // End of the board
    }
    println("Use WASD to move");
    println("Press M for menu");
}

public static String centerNumberInCell(int num, int cellWidth) {
    String numStr = Integer.toString(num);

    // Special case: If the number has an even number of digits, add spaces between each digit
    if (numStr.length() % 2 == 0) {
        // Add spaces between each digit of even-length numbers (e.g., 16 -> "1 6")
        numStr = String.join(" ", numStr.split(""));
    }

    // Calculate the padding required to center the number within the cell width
    int totalPadding = cellWidth - numStr.length();
    int leftPadding = totalPadding / 2;
    int rightPadding = totalPadding - leftPadding;

    // Create the result with padding and the number centered
    return " ".repeat(leftPadding) + numStr + " ".repeat(rightPadding);
}
  public static void printFormat(int number) {
        // Check if the number exists in the color map
        if (colorMap.containsKey(number)) {
            String[] colors = colorMap.get(number);
            String bgColor = colors[0];
            String textColor = colors[1];

            // ANSI escape codes to apply text formatting (bold and color)
           
            String bold = "\033[1m";
            String background = "\033[48;2;" + Integer.parseInt(bgColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(bgColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(bgColor.substring(5, 7), 16) + "m";
            String text = "\033[38;2;" + Integer.parseInt(textColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(textColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(textColor.substring(5, 7), 16) + "m";
           
            // Print the number with bold formatting and color
            print(background + text + bold);
        } else if(number == 0){
            print("\033[48;2;205;193;179m");
        } else {
            print("\033[0m");
        }
    }

public static void shiftArray(int[][] board, String direction) {
        int[][] newBoard = new int[bSize][bSize];
       
        switch (direction) {
            
            case "w":
                for (int col = 0; col < bSize; col++) {
                    int position = 0;
                    for (int row = 0; row < bSize; row++) {
                        if (board[row][col] != 0) {
                            newBoard[position++][col] = board[row][col];
                        }
                    }
                }
                break;
               
            
            case "s":
                for (int col = 0; col < bSize; col++) {
                    int position = bSize - 1;
                    for (int row = bSize - 1; row >= 0; row--) {
                        if (board[row][col] != 0) {
                            newBoard[position--][col] = board[row][col];
                        }
                    }
                }
                break;

            
            case "a":
                for (int row = 0; row < bSize; row++) {
                    int position = 0;
                    for (int col = 0; col < bSize; col++) {
                        if (board[row][col] != 0) {
                            newBoard[row][position++] = board[row][col];
                        }
                    }
                }
                break;

            
            case "d":
                for (int row = 0; row < bSize; row++) {
                    int position = bSize - 1;
                    for (int col = bSize - 1; col >= 0; col--) {
                        if (board[row][col] != 0) {
                            newBoard[row][position--] = board[row][col];
                        }
                    }
                }
                break;
               
            default:
                println("Invalid direction. Please use up, down, left, or right.");
                return;
        }

        // Update the original board
        for (int i = 0; i < bSize; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, bSize);
        }
    }
    


public static void addRandomNumber(int[][] board) {
        for (int p=0;p<gameMode;p++){
        List<int[]> emptySpots = new ArrayList<>();

        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                if (board[i][j] == 0) {
                    emptySpots.add(new int[]{i, j});
                }
            }
        }

        if (!emptySpots.isEmpty()) {
            int[] randomSpot = emptySpots.get(new Random().nextInt(emptySpots.size()));
            board[randomSpot[0]][randomSpot[1]] = Math.random() < 0.1 ? 4 : 2;
        }
     }
  }
  
  
  public static int[][] cloneBoard(int[][] board) {
        int[][] clonedBoard = new int[bSize][bSize];
        for (int i = 0; i < bSize; i++) {
            clonedBoard[i] = Arrays.copyOf(board[i], bSize);
        }
        return clonedBoard;
    }
    
    
    public static boolean checkWin (int[][]board){
        for (int[]row:board){
            for (int num:row){
                if (num == winCon) {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    public static boolean checkLoss(int[][] board) {
        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                if (board[i][j] == 0) {
                    return false; // There's still an empty spot, game is not over
                }
            }
        }

        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                if (i < bSize - 1 && board[i][j] == board[i + 1][j]) {
                    return false; // Merge possible vertically
                }
                if (j < bSize - 1 && board[i][j] == board[i][j + 1]) {
                    return false; // Merge possible horizontally
                }
            }
        }
    return true;
    }
    public static void clearScreen() {
        print("\033[H\033[2J");
        System.out.flush();
    }
    public static List<String> wordList;
    public static String[][] result = new String[6][5];
    public static boolean invalid = false;

    public static void wordle() throws InterruptedException {
        String filePath = "C:\\Users\\Avraham\\onedrive\\Documents\\GitHub\\Minigames\\valid-wordle-words.txt";
        wordList = readWordsFromFile(filePath);
        if (wordList != null && !wordList.isEmpty()) {
            String word = randomWord(wordList);
            wordleGame(word);
        } else {
            println("Word list is empty or null");
        }
    }

    public static List<String> readWordsFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static String randomWord(List<String> wordList) {
        Random r = new Random();
        int position = r.nextInt(wordList.size());
        return wordList.get(position);
    }

    public static void wordleGame(String word) throws InterruptedException {
        char[] wordArray = word.toCharArray();
        int attempts = 6;
        Scanner scanner = new Scanner(System.in);
        Set<Character> exactMatches = new HashSet<>();
        Set<Character> nonExactMatches = new HashSet<>();
        Set<Character> incorrectGuesses = new HashSet<>();

        while (attempts > 0) {
           
            println("You have " + attempts + " attempts left.");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j] != null) {
                        print(result[i][j]);
                    } else {
                        print(" ");
                    }
                }
                println(" ");
            }

            // Display the alphabet with colors
            displayAlphabet(exactMatches, nonExactMatches, incorrectGuesses);
            if (invalid) {
                Thread.sleep(1000);
               }
               invalid = false;
               clearScreen();
               println("You have " + attempts + " attempts left.");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j] != null) {
                        print(result[i][j]);
                    } else {
                        print(" ");
                    }
                }
                println(" ");
            }

            // Display the alphabet with colors
            displayAlphabet(exactMatches, nonExactMatches, incorrectGuesses);
            print("Enter your guess: ");
            String guess = scanner.nextLine().toLowerCase();
            if (guess.equals("7187448310")) {
                println("The word is: " + word);
                println("2..");
                Thread.sleep(1000);
                println("1..");
                Thread.sleep(1000);
                clearScreen();
                continue;
            }

            if (!wordList.contains(guess) && !guess.equals("7187448310")) {
                clearScreen();
                println("\u001B[31m" + "The guessed word is not in the word list." + "\u001B[0m");
                invalid = true;
                continue;
            }

            clearScreen();
            attempts--;
            char[] guessArray = guess.toCharArray();

            boolean[] matchedWord = new boolean[wordArray.length];
            boolean[] matchedGuess = new boolean[guessArray.length];
            boolean[] exactMatchedGuess = new boolean[guessArray.length];

            for (int i = 0; i < wordArray.length; i++) {
                for (int j = 0; j < wordArray.length; j++) {
                    if (!matchedWord[j] && wordArray[j] == guessArray[i]) {
                        if (i == j) {
                            exactMatchedGuess[i] = true;
                            matchedWord[j] = true;
                            result[6 - attempts - 1][i] = "\u001B[32m" + guessArray[i] + "\u001B[0m"; // Bright green
                            exactMatches.add(guessArray[i]);
                        } else {
                            matchedGuess[i] = true;
                            matchedWord[j] = true;
                            // Changed to a more golden yellow color
                            result[6 - attempts - 1][i] = "\u001B[38;2;255;191;0m" + guessArray[i] + "\u001B[0m";
                            nonExactMatches.add(guessArray[i]);
                        }
                        break;
                    }
                }
                if (!exactMatchedGuess[i] && !matchedGuess[i]) {
                    result[6 - attempts - 1][i] = "\u001B[90m" + guessArray[i] + "\u001B[0m"; // Dark gray
                    incorrectGuesses.add(guessArray[i]);
                }
            }

            if (Arrays.equals(wordArray, guessArray)) {
                clearScreen();
                println("You won in " + attempts + "The word was: " + word);
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        if (result[i][j] != null) {
                            print(result[i][j]);
                        } else {
                            print(" ");
                        }
                    }
                    println(" ");
                }
                break;
            }

            if (attempts == 0) {
                println("You've run out of attempts. The word was: " + word);
            }
        }
        scanner.close();
    }

    public static void displayAlphabet(Set<Character> exactMatches, Set<Character> nonExactMatches, Set<Character> incorrectGuesses) {
        StringBuilder alphabet = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            if (exactMatches.contains(c)) {
                alphabet.append("\033[1;32m").append(c).append("\033[0m "); // Bright green
            } else if (nonExactMatches.contains(c)) {
                alphabet.append("\033[1;33m").append(c).append("\033[0m "); // Bright yellow
            } else if (incorrectGuesses.contains(c)) {
                alphabet.append("\033[1;30m").append(c).append("\033[0m "); // Darkest possible black
            } else {
                alphabet.append("\033[1;37m").append(c).append("\033[0m "); // Bright white
            }
        }
        println(alphabet.toString());
    }
    public static void print(String a) {
        System.out.print(a);
    }
    public static void printi(int a) {
        System.out.print(a);
    }
    public static void println(String a) {
        System.out.println(a);
    }
    public static void printlni(int a) {
        System.out.println(a);
    }
    
}