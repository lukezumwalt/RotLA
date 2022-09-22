package Game;

import Board.Render;

import java.util.Objects;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        // instantiate objects
        Render view = new Render();
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

            view.printFrame();
            game.processAdventurers();
            game.processCreatures();
        }
        input.close();
    }
}
