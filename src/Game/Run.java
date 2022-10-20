package Game;

public class Run {
    public static void main(String[] args) {

//        Scanner input = new Scanner(System.in);
        Engine game = new Engine();

        // Initialize Game Properties
        game.initialize();
        game.playerStart();

//        System.out.println("Press [Enter] to begin...");
        // Primary Game.Run Loop
        while (true) {
            // Debugging condition
//            if (Objects.equals(input.nextLine(), "gg")) {
//                break;
//            }

            // Execute one turn
            game.runOneTurn();

            // Exit Conditions
            if (game.endConditionMet()) {
                break;
            }
        }
//        input.close();
    }
}
