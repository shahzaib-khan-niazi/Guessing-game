import java.util.Random;
import java.util.Scanner;
 
public class GuessingGame2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(scanner);
        Game game = new Game(player); 
        

        game.start();
        scanner.close();
    }
}

class Game {
    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_NUMBER = 100;

    private Player player; 
    private Random random;

    public Game(Player player) {
        this.player = player;
        random = new Random();
    }

    public void start() {
        System.out.println("Welcome to the Guessing Game!");
        player.askPlayerName();
        System.out.println("Where you get 3 chances to guess a number from 1 to 100 and see if you're right!");

        boolean playAgain;
        do {
            playGame();
            playAgain = player.askToPlayAgain();
        } while (playAgain);

        System.out.println("Thanks for playing, " + player.getName() + "!");
    }

    private void playGame() {
        int randomNumber = random.nextInt(MAX_NUMBER) + 1; 

        System.out.println("Please enter your guesses (3 attempts):");
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            int guess = player.makeGuess(attempt);
            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed it right!");
                return;
            } else {
                System.out.println("Sorry, that is not correct.");
                if (guess < randomNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }
            }
        }

        System.out.println("Sorry, you've used all your attempts.");
        System.out.println("The correct number was: " + randomNumber);
    }
}

class Player {
    private Scanner scanner;
    private String name;

    public Player(Scanner scanner) {
        this.scanner = scanner;
    }

    public void askPlayerName() {
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
    }

    public int makeGuess(int attempt) {
        System.out.print("Attempt " + attempt + ": ");
        return scanner.nextInt();
    }

    public boolean askToPlayAgain() {
        System.out.print("Would you like to play again? (1: Yes, 2: No): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice == 1;
    }

    public String getName() {
        return name;
    }
}