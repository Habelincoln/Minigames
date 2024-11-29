import java.util.*;
public class game2048 {
   //store arrow values
public static final String upArrow = "\033[a";
public static final String downArrow = "\033[b";
public static final String rightArrow = "\033[c";
public static final String leftArrow = "\033[d";
private static final Map<Integer, String[]> colorMap = new HashMap<>();
   private static int score = 0;
   private static int gameMode = 0;
   public static boolean haveWon = false;
   public static int winCon = 2048;
    public static int menuChoice = 7;
    public static int gameType;
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
           
          String direction = scanner.nextLine().trim().toLowerCase();
          System.out.println(direction);
           if (direction.contains("\033") || direction.contains("esc")) {
          
          while (!endGame) {
      try {
          System.out.println("Menu:");
          System.out.println("(0) Back to game");
          System.out.println("(1) Exit game");
          System.out.println("(2) Change win condition");
          System.out.println("(3) Undo last move");
          System.out.println("(4) Clear board");
          System.out.print("Enter your choice: ");
          
          menuChoice = Integer.parseInt(scanner.nextLine().trim());
          
          if (menuChoice >= 0 && menuChoice <= 4) {
              break; 
          } else {
              System.out.println("\u001b[31mInvalid Input. Please enter a number between 0 and 4.\u001b[0m");
          }
      } catch (NumberFormatException e) {
          System.out.println("\u001b[31mInvalid Input. Please enter a valid number.\u001b[0m");
      }
  }
          switch (menuChoice){
          case 0: 
              direction=scanner.nextLine();
              break;
          
          
          case 1:
              System.out.print("\033[H\033[2J");
              System.out.flush();
              printBoard(board);
              endGame = true;
              break;
          
          case 2:
              System.out.println("Enter new win condition (Current is " + winCon +"):");
              winCon = scanner.nextInt();
              System.out.println("Saved. New win condition is " + winCon);
              break;
          
          case 3:
              
                  board = undoBoard;
              System.out.print("\033[H\033[2J");
              System.out.flush();
              printBoard(board);
              break;
          
          case 4:
              for (int i=0;i<bSize;i++){
                  for(int j=0;j<bSize;j++){
                      board[i][j]=0;
                  }
              }
          default:
              System.out.println("Invalid choice. Please enter a number between 0 and 4.");
              break;
          }
            if (endGame) {
            break;
        }  
              
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
       
      case downArrow:
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
      
      case rightArrow:
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
      
      case leftArrow:
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
                 
              case downArrow:
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
  
              case leftArrow:
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
  
              case rightArrow:
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