import java.util.*;

public class main
{
	public static void main(String[] args) {
	System.out.println("Game select: Hangman (1), MasterMind (2), NumberGuesser (3), 2048 (4)");
	
	    Scanner input = new Scanner(System.in);
	    String gameChoice = input.nextLine().toLowerCase();
	    if (gameChoice.equals("hangman") || gameChoice.equals("1")) {
	        hangman();
	    }
	    else if (gameChoice.equals("mastermind") || gameChoice.equals("2")) {
	        mastermind();
	    }
	    else if (gameChoice.equals("numberguesser") || gameChoice.equals("3")) {
	        numberguesser();
	    }
	    else if (gameChoice.equals("2048")||gameChoice.equals("4")) {
	        game2048();
	    }
	    else {
	        System.out.println("Invalid game name");
	    }
input.close();
	}



public static void hangman() {
    Scanner scanner = new Scanner(System.in);
    
		String word = "";
		
		do {
		    
		    System.out.print("\033[H\033[2J"); //clear console
		    if (word.matches(".*\\d.*")){System.out.println("\u001B[31m"+"INVALID INPUT!!!" +"\u001B[0m" );}
		    System.out.println("Welcome to hangman!");
	    	System.out.println("Enter Word:");
		    word = scanner.nextLine().toLowerCase();
		    
		}
		while(word.matches(".*\\d.*"));
		
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
		
		
		





        System.out.print("\033[H\033[2J"); //print info

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



public static void mastermind() {
    Scanner scanner = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\u001B[33m" +"Welcome to Mastermind!" + "\u001B[0m");
		int lives = 0;
		System.out.println("Choose code");
        System.out.println("4 pegs. 6 possible colors: \u001B[36mBlue\u001B[0m, \u001B[32mGreen\u001B[0m, \u001B[33mYellow\u001B[0m, \u001B[31mRed\u001B[0m, \u001B[90mGray\u001B[0m, and \u001B[37mWhite\u001B[0m");
		System.out.println("Peg 1:");
		String code1 = scanner.nextLine().toLowerCase();
		
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		System.out.println("Peg 2:");
		String code2 = scanner.nextLine().toLowerCase();
		
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		System.out.println("Peg 3:");
		String code3 = scanner.nextLine().toLowerCase();
		
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		System.out.println("Peg 4:");
		String code4 = scanner.nextLine().toLowerCase();
	    
	    System.out.print("\033[H\033[2J");
		System.out.flush();
	    
	    
	    System.out.println("Enter Lives (Default 10):");
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
				System.out.println("Game over. You lose.");
				System.out.println("The code was: " + code[0] + " " +code[1] + " " + code[2] + " " + code[3]);
				break;
			}
			// lose a life if game not over
			else {
				lives++;
			}
		}

    scanner.close();
}

public static void numberguesser() {
    Scanner scanner = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\u001B[32m" + "Welcome to Number Guesser!" + "\u001B[32m");
		System.out.println("Random 1-100 (1) or You choose (2)?");

		int gameType = scanner.nextInt();

		int answer = 0;

		while (gameType != 1 && gameType != 2) {
			System.out.println("Please choose 1 or 2.");
			gameType = scanner.nextInt();
		}


		if (gameType == 1) {
			answer = (int) (Math.random() *100) + 1;

		}

		else if (gameType == 2) {
			System.out.println("Enter the number to be guessed: ");
			answer = scanner.nextInt();
			System.out.print("\033[H\033[2J");
			System.out.flush();

		}


		int input;
		int count = 0;
		System.out.println("Enter your first guess: ");
		do {

			input = scanner.nextInt();
			if (input < answer) {
				System.out.println("Too low!!! try again");
			}
			if (input > answer) {
				System.out.println("Too high! sound familiar?");
			}
			count++;

		} while (input != answer);


		System.out.println("Correct! finally");

		if (count == 1) {
			System.out.printf("it only took you %d try...too many\n", count);
		} else {
			System.out.printf("it only took you %d tries...too many\n", count);
		}

    scanner.close();
    }
    //store arrow values
public static final String upArrow = "\033[A";
public static final String downArrow = "\033[B";
public static final String rightArrow = "\033[C";
public static final String leftArrow = "\033[D";
private static final Map<Integer, String[]> colorMap = new HashMap<>();
   private static int score = 0;
   private static int gameMode = 0;
   public static boolean haveWon = false;
   public static int winCon = 2048;
    public static int menuChoice = 7;
    public static int gameType;
    static {
        // Initialize colorMap with the number, HEX background color, and text color
        colorMap.put(2, new String[] {"#eee4daff", "#776e65ff"});
        colorMap.put(4, new String[] {"#ded3bdff", "#776e65ff"});
        colorMap.put(8, new String[] {"#f2b179ff", "#ffffffff"});
        colorMap.put(16, new String[] {"#f59563ff", "#ffffffff"});
        colorMap.put(32, new String[] {"#f67c5fff", "#ffffffff"});
        colorMap.put(64, new String[] {"#f65e3bff", "#ffffffff"});
        colorMap.put(128, new String[] {"#edcf72ff", "#ffffffff"});
        colorMap.put(256, new String[] {"#edcc61ff", "#ffffffff"});
        colorMap.put(512, new String[] {"#edc850ff", "#ffffffff"});
        colorMap.put(1024, new String[] {"#edc53fff", "#ffffffff"});
        colorMap.put(2048, new String[] {"#edc22eff", "#ffffffff"});
        colorMap.put(4096, new String[] {"#b586b4ff", "#ffffffff"});
        colorMap.put(8192, new String[] {"#a861abff", "#ffffffff"});
        colorMap.put(16384, new String[] {"#a048a3ff", "#ffffffff"});
        colorMap.put(32768, new String[] {"#800080ff", "#ffffffff"});
        colorMap.put(65536, new String[] {"#600046ff", "#ffffffff"});
        colorMap.put(131072, new String[] {"#8b86e3ff", "#ffffffff"});
    } 

static Scanner scanner = new Scanner(System.in);
 
 public static int bSize = 0;
 

    public static void game2048 () {
      int[][]board;
      while(true) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("(1) Play");
        System.out.println("(2) Custom");
         gameType = scanner.nextInt();
        if (gameType==2) {
            
        System.out.println("Enter Spawn Per Move (Default 1):");
        
        gameMode = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter Board Size (Default 4):");
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
    
    printBoard(board);
    while (true) {   
         
        String direction = scanner.nextLine();
        if (direction.contains("\033[A")||direction.contains("\033[B")||direction.contains("\033[C")||direction.contains("\033[D")) {

        }
        else if (direction.contains("\033")) {
            printBoard(board);
            break;
        }
      
      
       System.out.println(score);
        printBoard(board);
            shiftArray(board, direction);
            merge(board, direction);
            shiftArray(board, direction);
            
             boolean boardChanged = !Arrays.deepEquals(originalBoard, board);
            
            if (boardChanged && menuChoice != 0 && menuChoice != 1 && menuChoice != 2 && menuChoice != 3) {
                addRandomNumber(board);
                undoBoard = originalBoard;
                
                
            }
            menuChoice = 7;
            originalBoard = cloneBoard(board);
            
            printBoard(board);
            
            
            if (checkWin(board) && !haveWon) {
                System.out.println("You Win!");
                System.out.print("Do you want to continue playing? (y/n): ");
                
            String response = scanner.nextLine().toLowerCase();
            if (response.contains("y")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                haveWon = true;
                printBoard(board);
            }
            else if (response.contains("n")) {
                System.out.println("You Win!");
                break;
            }
            }
            if (checkLoss(board)){
                System.out.println("You lose.");
                break;
            }
    }
        
    }

public static void merge (int[][]board, String direction) {
    
switch (direction) {
         
  case upArrow: 
             
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
     
    case downArrow:
        
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
    
    case rightArrow:
    
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
    
    case leftArrow:
       
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
    
    default: System.out.println("Error");
    
}
      
    }
    
public static void printBoard(int[][] board) {
     System.out.println(score);
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

    // Clear the screen
    System.out.print("\033[H\033[2J");
    System.out.flush();

System.out.println("Score: "+ score);

    // Print top wall (board separator)
    System.out.print(wallColor + " " + resetColor);
    for (int i = 0; i < bSize; i++) {
        System.out.print((wallColor + " " + resetColor).repeat(cellWidth) + wallColor + " " + resetColor);
    }
    
System.out.println();
    // Print board content row by row
    for (int[] row : board) {
        System.out.print(wallColor + " " + resetColor); // Start of the row
        for (int num : row) {
            // For non-zero values, print the number centered within its cell
            String cellContent = (num == 0) ? emptyCell : centerNumberInCell(num, cellWidth);
            printFormat(num);
            System.out.print(cellContent + wallColor + " " + resetColor); // Print the cell
        }
        System.out.println(); // New line after each row

        // Print a separator line after each row
        System.out.print(wallColor + " " + resetColor);
        for (int i = 0; i < bSize; i++) {
            System.out.print((wallColor + " " + resetColor).repeat(cellWidth) + wallColor + " " + resetColor);
        }
        System.out.println(); // End of the board
    }
    System.out.println("Use arrow keys to move");
    System.out.println("Press esc for menu");
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
            String reset = "\033[0m";
            String bold = "\033[1m";
            String background = "\033[48;2;" + Integer.parseInt(bgColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(bgColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(bgColor.substring(5, 7), 16) + "m";
            String text = "\033[38;2;" + Integer.parseInt(textColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(textColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(textColor.substring(5, 7), 16) + "m";
           
            // Print the number with bold formatting and color
            System.out.print(background + text + bold);
        } else if(number == 0){
            System.out.print("\033[48;2;205;193;179m");
        } else if (number > 131072){
           
            String[] colors = colorMap.get(131072);
            String bgColor = colors[0];
            String textColor = colors[1];

            // ANSI escape codes to apply text formatting (bold and color)
            String reset = "\033[0m";
            String bold = "\033[1m";
            String background = "\033[48;2;" + Integer.parseInt(bgColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(bgColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(bgColor.substring(5, 7), 16) + "m";
            String text = "\033[38;2;" + Integer.parseInt(textColor.substring(1, 3), 16) + ";"
                    + Integer.parseInt(textColor.substring(3, 5), 16) + ";"
                    + Integer.parseInt(textColor.substring(5, 7), 16) + "m";
           
        } else {
            System.out.print("\033[0m");
        }
    }


public static void shiftArray(int[][] board, String direction) {
        int[][] newBoard = new int[bSize][bSize];
       
        switch (direction) {
            case upArrow:
                for (int col = 0; col < bSize; col++) {
                    int position = 0;
                    for (int row = 0; row < bSize; row++) {
                        if (board[row][col] != 0) {
                            newBoard[position++][col] = board[row][col];
                        }
                    }
                }
                break;
               
            case downArrow:
                for (int col = 0; col < bSize; col++) {
                    int position = bSize - 1;
                    for (int row = bSize - 1; row >= 0; row--) {
                        if (board[row][col] != 0) {
                            newBoard[position--][col] = board[row][col];
                        }
                    }
                }
                break;

            case leftArrow:
                for (int row = 0; row < bSize; row++) {
                    int position = 0;
                    for (int col = 0; col < bSize; col++) {
                        if (board[row][col] != 0) {
                            newBoard[row][position++] = board[row][col];
                        }
                    }
                }
                break;

            case rightArrow:
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
                System.out.println("Invalid direction. Please use up, down, left, or right.");
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
}
