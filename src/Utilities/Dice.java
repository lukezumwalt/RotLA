package Utilities;

import java.util.Random;

public class Dice {

    // PUBLIC METHODS
    public static int rollD6(int numberOfDice) {
        /*
         * https://stackoverflow.com/questions/5271598/java-generate-random-number-
         * between-two-given-values
         */
        Random r = new Random();
        // smallest possible value is a one on every die rolled, thus #ofDice == minimum
        // roll
        int low = numberOfDice;
        int high = 6 * numberOfDice;
        return (r.nextInt(high - low) + low);
    }
}
