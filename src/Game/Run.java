package Game;

import Board.Logger;
import Board.Render;

import java.util.Objects;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        // instantiate objects
        Scanner input = new Scanner(System.in);
        Engine game = new Engine();

        game.initialize();

        System.out.println("Press [Enter] to begin...");
        // Primary Game.Run Loop
        while (true) {

            // Exit Conditions
            if (game.endConditionMet()) {
                break;
            }
            // Debugging condition
            if (Objects.equals(input.nextLine(), "gg")) {
                break;
            }

            // Execute one turn
            game.runOneTurn();
        }
        input.close();
    }
}
