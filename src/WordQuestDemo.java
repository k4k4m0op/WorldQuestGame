import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordQuestDemo {
    public static void main(String[] args) throws IOException {

        //define the secret word am maximum allowed attempts; choose the right path to "sample-word.txt"
        String fileNAme = "F:\\Java\\WordQuestGame\\WordQuestWords\\sample-words.txt";
        String secretWord  = getRandomWord(fileNAme);
        int maxAttempts = 10;

        // Create the game board with underscores to represent unrevealed letters
        char[] gameboard = new char[secretWord.length()];

        //initialize game board with underscores to represent unrevealed letters
        Arrays.fill(gameboard, '_');

        //Scanner ro read user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Word Quest Game!");

        //Flag to check if the word has missung letters
        boolean hasMissingLetters = true;

        //Main game loop: runs while attempts left and word has missing letters
        while (maxAttempts > 0 && hasMissingLetters){
            System.out.print("Current Word: ");
            System.out.println(gameboard);
            System.out.println();
            System.out.print("Guess a letter: ");

            //read the user input, take the first character and convert it to uppercase
            String userInput = scanner.next().toUpperCase();
            char guess = userInput.charAt(0);

            //Track if the guess is correct
            boolean isGuessCorrect = false;

            //Loop trough each letter in the secret word and check if it matches the guess
            for (int i = 0; i < secretWord.length(); i++){
                if (secretWord.charAt(i) == guess){
                    gameboard[i] = guess;
                    isGuessCorrect = true;
                }
            }

            //Update game status based on the guess
            if (isGuessCorrect){
                System.out.println("Good job! You found a match!");

                //Check if there are still has missing letters ('_')
                hasMissingLetters = containsUnderscore(gameboard);
            }else {
                System.out.println("Incorrect!");

                //decrement attempts for an incorrect guess
                maxAttempts--;
            }

            //Display remaining attempts after each guess
            System.out.println("You have " + maxAttempts + " attempts left!");
            System.out.println();

        }

        // End game message based on whether the word has missing letters
        if (hasMissingLetters){
            System.out.println("You run out of attempts. The secret word was: " + secretWord);
        }else {
            System.out.println("Success!!! You´ve revealed the secret word: " + secretWord);
        }
        scanner.close();

    }

    private static boolean containsUnderscore(char[] gameboard) {
        for (char temp : gameboard) {
            if (temp == '_') {
                return true;
            }
        }
        return false;
    }

    private static String getRandomWord(String fileNAme) throws IOException {
        //String[] words = {"Friend", "Airplane", "Holiday"};

        //read in all lines from the file
        List<String> linesList = Files.readAllLines(Path.of(fileNAme));

        //convert the list to an array
        String[] words = linesList.toArray(new String[0]);


        Random random = new Random();
        int index = random.nextInt(words.length);

        String theWord = words[index];

        return theWord.toUpperCase();
    }
}
