import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class wordle {
    public static List<String> wordList;
    public static String[][] result = new String[6][5];
    public static void main(String[] args) throws InterruptedException {
        String filePath = "C:\\Users\\Avraham\\Documents\\GitHub\\Minigames\\valid-wordle-words.txt";
        wordList = readWordsFromFile(filePath);
        if (wordList != null && !wordList.isEmpty()) {
            String word = randomWord(wordList);
                
                wordleGame(word);
        }
        else {
            System.out.println("Word list is empty or null");
        }
    }
   //Create wordList from text file
    public static List<String> readWordsFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
        //Select random word from wordlist
        public static String randomWord (List<String> wordList) {
            Random r = new Random();
            int position = r.nextInt(wordList.size());
            return wordList.get(position);
        }
    public static void wordleGame(String word) throws InterruptedException{
        char[] wordArray = word.toCharArray();
        int attempts = 6;
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("Welcome to Wordle!");
        while (attempts > 0) {
            
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine().toLowerCase();
            if (guess.equals("7187448310")) {
                System.out.println("The word is: " + word);
                System.out.println("2..");
                Thread.sleep(1000);
                System.out.println("1..");
                Thread.sleep(1000);
                continue;
            }

            if (!wordList.contains(guess) && !guess.equals("7187448310")) {
                System.out.println("The guessed word is not in the word list.");
                clearScreen();
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
                            result[6 - attempts - 1][i] = "\u001B[32m" + guessArray[i] + "\u001B[0m";
                        } else {
                            matchedGuess[i] = true;
                            matchedWord[j] = true;
                            result[6 - attempts - 1][i] = "\u001B[33m" + guessArray[i] + "\u001B[0m";
                        }
                        break;
                    }
                }
                if (!exactMatchedGuess[i] && !matchedGuess[i]) {
                    result[6 - attempts - 1][i] = "\u001B[0m" + guessArray[i] + "\u001B[0m";
                }
            }
        
    
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j] != null) {
                        System.out.print(result[i][j]);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            if (Arrays.equals(wordArray, guessArray)) {
                clearScreen();
                System.out.println("You win! The word was: " + word);
                break;
            }

            if (attempts == 0) {
                System.out.println("You've run out of attempts. The word was: " + word);
            }
        }
        scanner.close();
        }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
