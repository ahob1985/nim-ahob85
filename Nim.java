import java.util.Scanner;

/**
 * Write a description of class Nim here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Nim
{
    /**
     * stonesRemaining
     * int. Represents the number of stones that haven't yet been removed. 
     * You should initialize this in setupGame().
     * 
     * activePlayer
     * int. Represents the currently active player. Encoded as 1 for 
     * Player One and 2 for Player Two. Initialize this in setupGame() by 
     * "flipping a coin".
     * 
     * quit
     * boolean. Represents if the player has chosen to quit the game (true) 
     * or not (false). Initialized to false in run(), can be altered in 
     * processResult().
     * 
     * sc
     * Scanner. The tool used to get user input.
     */
    private static int stonesRemaining; 
    private static int activePlayer; 
    private static boolean quit;
    private static Scanner sc;

    /**
     * Prints a simple greeting. Be as creative as you want here. Be sure to 
     * include your name as the author!
     */
    public static void printGreeting() {
        System.out.println("Nim");
        System.out.println("By: Hidden Genius");
    }

    /**
     * Initialize stonesRemaining to whatever value you'd like the game to 
     * start with (10 is good). Then initialize activePlayer by simulating a 
     * coin flip to decide who will go first. To simulate a coin flip, 
     * generate a random integer between 0 and 1 (inclusive).
     * 
     * Here's how you generate random integers in general:
     * (int) Math.floor(Math.random() * (max - min + 1) + min)
     * 
     * Here's how you simulate a coin flip:
     * (int) Math.floor(Math.random() * (2 - 1 + 1) + 1)
     * ...which can be simplified to:
     * (int) Math.floor(Math.random() * 2 + 1)
     */
    public static void setupGame() {
        stonesRemaining = 10;
        activePlayer = (int) Math.floor(Math.random() * 2 + 1);
    }

    /**
     * Print a visual representation of the number of stones remaining 
     * (e.g., a line of "O" characters), along with a message that conveys 
     * this same information.
     * 
     * Hint: Create a local string variable and use a for loop to concatenate 
     * "O " to it every iteration!
     */
    public static void printStones() {
        String stoneString = "";
        for(int i = 0; i < stonesRemaining; i++) {
            stoneString += "O ";
        }
        System.out.println(stoneString);
        System.out.println("Stones remaining: " + stonesRemaining);
    }

    /**
     * This function is the "workhorse" of the program. It is called 
     * repeatedly in run() until there are no more stones remaining.
     * 
     * It first asks the currently active player (as denoted by activePlayer) 
     * to enter the number of stones they would like to remove. Players 
     * should only be allowed to enter values 1, 2, or 3. If they enter any 
     * other value, the program should lightly scold them and then ask again, 
     * and continue to do so until they enter a valid number.
     * 
     * Moreover, if players enter a valid number but there aren't that many 
     * stones remaining (e.g., they enter 3 but there are only 2 stones 
     * remaining), the program should tell them this, then ask for input 
     * again, just like above.
     * 
     * When players have finally entered an acceptable number, this function
     * should subtract that number from stonesRemaining, and switch 
     * activePlayer to the other player.
     */
    public static void removeStones() {
        int stonesToRemove = 0;
        while(!(stonesToRemove >= 1 && stonesToRemove <= 3)) {
            if(activePlayer == 1) {
                System.out.print("Player One, enter # of stones to remove (1, 2, or 3): ");
                stonesToRemove = Integer.parseInt(sc.nextLine());
            } else {
                System.out.print("Player Two, enter # of stones to remove (1, 2, or 3): ");
                stonesToRemove = Integer.parseInt(sc.nextLine());
            }
            if(stonesToRemove < 1 || stonesToRemove > 3) {
                System.out.println("Please enter 1, 2, or 3!");
            }
            else if(stonesToRemove > stonesRemaining) {
                stonesToRemove = 0;
                System.out.println("You can't remove more stones than there are remaining!");
            }
        }
        stonesRemaining -= stonesToRemove;
        if(activePlayer == 1) {
            activePlayer = 2;
        } else {
            activePlayer = 1;
        }
    }

    /**
     * This function is called in run() when there are no more stones 
     * remaining. It first prints a "Player X Wins!" message, depending on 
     * who the currently active player is (this was switched at the end of 
     * the last call to removeStones()). Note that whoever removes the last 
     * stone loses, which is already accounted for if you wrote 
     * removeStones() correctly!
     * 
     * This function then asks players if they would like to keep playing. 
     * If players choose yes, nothing happens here. If players choose no, 
     * quit is set to true. The rest is handled in run().
     */
    public static void processResult() {
        if(activePlayer == 1) {
            System.out.println("Player One wins!");
        } else {
            System.out.println("Player Two wins!");
        }
        System.out.print("Play again? (yes or no): ");
        String keepPlaying = sc.nextLine();
        if(keepPlaying.equals("no") || keepPlaying.equals("n")) {
            quit = true;
        }
    }

    /**
     * This is the function that calls all the other functions. Here is what
     * it should do, in order:
     * 
     * 1: Print a greeting.
     * 2: Initialize the sc variable to a new scanner for user input.
     * 2: Initialize quit to false.
     * 3: As long as quit is false, continue running the following:
     *      A. Set up the game.
     *      B. As long as there are stones remaining, continue running the 
     *         following:
     *          I. Print the stones remaining.
     *          II. Ask players to remove stones.
     *      C. There are no more stones remaining, so process results here.
     * 4. quit is true, so print a goodbye message here.
     */
    public static void main(String[] args) {
        printGreeting();
        sc = new Scanner(System.in);
        quit = false;
        while(!quit) {
            setupGame();
            while(stonesRemaining > 0) {
                printStones();
                removeStones();
            }
            processResult();
        }
        System.out.println("Thanks for playing! Goodbye!");
    }
}
