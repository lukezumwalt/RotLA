package Utilities;

import java.util.Random;

public class Dice {
    public static int rollD6(int numberOfDice){
        /* https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values */
        Random r = new Random();
        int low = 1;
        int high = 6*numberOfDice;
        return(r.nextInt(high-low) + low );
    }
}
