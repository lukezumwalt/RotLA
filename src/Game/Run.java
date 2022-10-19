package Game;

import java.util.Objects;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        // instantiate objects
        Scanner input = new Scanner(System.in);
        Engine game = new Engine();

        // Character Creation
//        game.view.printFrame();
//        game.characterCreation();

        // Initialize Game Properties
        game.initialize();
        game.playerStart();

//        System.out.println("Press [Enter] to begin...");
        // Primary Game.Run Loop
        while (true) {
            // Debugging condition
            if (Objects.equals(input.nextLine(), "gg")) {
                break;
            }

            // Execute one turn
            game.runOneTurn();

            // Exit Conditions
            if (game.endConditionMet()) {
                break;
            }
        }
        input.close();
    }
}
