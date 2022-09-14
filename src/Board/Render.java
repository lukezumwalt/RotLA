package Board;

public class Render {

    // NOTE:    THIS ENTIRE CLASS / PACKAGE WILL BE REFACTORED TO BE VARIABLE
    //          IT IS STATIC NOW FOR DEMONSTRATION
    // @TODO: BUG, the second print lags until the third query but only for the second print loop.

    int turn = 0;

    public void printFrame(){
        printTurn();
        printBoard();
        printStatus();
        System.out.println("\n\n");
    }

    private void printTurn(){
        System.out.println("RotLA Turn: " + turn);
        turn++;
    }

    private void printBoard(){
        System.out.println("+--------------------------------------------------+");
        System.out.println("| 0-1-1: - : -                                     |");
        System.out.println("| 1-0-0: - : -    1-0-1: - : -    1-0-2: - : -     |");
        System.out.println("| 1-1-0: - : -    1-1-1: - : -    1-1-2: - : -     |");
        System.out.println("| 1-2-0: - : -    1-2-1: - : -    1-2-2: - : -     |");
        System.out.println("| 2-0-0: - : -    2-0-1: - : -    2-0-2: - : -     |");
        System.out.println("| 2-1-0: - : -    2-1-1: - : -    2-1-2: - : -     |");
        System.out.println("| 2-2-0: - : -    2-2-1: - : -    2-2-2: - : -     |");
        System.out.println("| 3-0-0: - : -    3-0-1: - : -    3-0-2: - : -     |");
        System.out.println("| 3-1-0: - : -    3-1-1: - : -    3-1-2: - : -     |");
        System.out.println("| 3-2-0: - : -    3-2-1: - : -    3-2-2: - : -     |");
        System.out.println("| 4-0-0: - : -    4-0-1: - : -    4-0-2: - : -     |");
        System.out.println("| 4-1-0: - : -    4-1-1: - : -    4-1-2: - : -     |");
        System.out.println("| 4-2-0: - : -    4-2-1: - : -    4-2-2: - : -     |");
        System.out.println("+--------------------------------------------------+");
    }

    private void printStatus(){
//        System.out.println("\n====-===+===-===+===-===+===-===+===-===+===-===+====\n");
        System.out.print("\n");
        System.out.println("Characters.Brawler:\t x Treasure(s) / x Damage");
        System.out.println("Sneaker:\t x Treasure(s) / x Damage");
        System.out.println("Runner:\t\t x Treasure(s) / x Damage");
        System.out.println("Thief:\t\t x Treasure(s) / x Damage\n");
        System.out.println("Orbiters:\t x Remaining");
        System.out.println("Seekers:\t x Remaining");
        System.out.println("Blinkers:\t x Remaining");
    }
}
