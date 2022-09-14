import BoardRenderer.Render;

import java.util.Objects;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        Render view = new Render();
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;

        // Primary Run Loop
        while ( !gameOver ) {

//            for (int i = 0; i < ENTITY_TOTAL; ++i) {
//                processEntity(entityList[i]);
//            }


            // print turn
            view.printTurn();

            // print board
            view.printBoard();

            // print status
            view.printStatus();

            // Exit Condition
            if(Objects.equals(input.nextLine(),"q")){
                gameOver = true;
            }
        }
    }
}
