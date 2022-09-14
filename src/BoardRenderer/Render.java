package BoardRenderer;

public class Render {

    // NOTE:    THIS ENTIRE CLASS / PACKAGE WILL BE REFACTORED TO BE VARIABLE
    //          IT IS STATIC NOW FOR DEMONSTRATION

    int turn = 0;
    static int[][][] board = new int[5][3][3];

//    static void fillBoard(){
//        for( int i = 0; i < 5; ++i ){
//            for( int j = 0; j < 3; ++j ){
//                for( int k = 0; k < 3; ++k ){
//                    board[i][j][k] = 1;
//                }
//            }
//        }
//    }

    public void printTurn(){
        System.out.println("RotLA Turn: " + turn);
        turn++;
    }

    public void printBoard(){
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

    public void printStatus(){
        System.out.println("\n====-===+===-===+===-===+===-===+===-===+===-===+====\n");
        System.out.println("Brawler - x Treasure(s) - x Damage");
        System.out.println("Sneaker - x Treasure(s) - x Damage");
        System.out.println("Runner - x Treasure(s) - x Damage");
        System.out.println("Thief - x Treasure(s) - x Damage\n");
        System.out.println("Orbiters - x Treasure(s) - x Damage");
        System.out.println("Seekers - x Treasure(s) - x Damage");
        System.out.println("Blinkers - x Treasure(s) - x Damage");
    }

    public void printRoom(){
        //
    }

//    private char[] getAdventurers( Room r ){
//        //
//        return new char[0];
//    }
//
//    private char[] getCreatures( Room r ){
//        //
//        return new char[0];
//    }
}
