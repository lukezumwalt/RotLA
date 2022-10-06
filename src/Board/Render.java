package Board;

import Characters.Entity;
import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Game.Engine;
import Treasure.Treasure;

import java.util.Arrays;

import static Game.Engine.coordinateToKey;
import static Game.Engine.getAdventurers;
import static Game.Engine.getCreatures;

public class Render {

    // CONSTRUCTORS
    public Render() {
        turn = 0;
    }

    // PRIVATE ATTRIBUTES
    private int turn;

    // PUBLIC METHODS
    /*
     * Code example of Cohesion
     * All methods are relevant to Render class
     * since they are all print methods of the
     * game.
     */
    public void printFrame() {
        printTurn();
        printBoard();
        printStatus();
    }

    // PRIVATE METHODS
    // prints turn number
    private void printTurn() {
        turn++;
        System.out.println("\nRotLA Turn: " + turn);
    }

    // printBoard access the Map via get methods that
    // reference a string called a key.
    // then it calls render for adventurers or creatures found
    // in the room based on if the key matches the room key.
    private void printBoard() {
        System.out.println("+-----------------------------------------------------------------------------------+");
        System.out.println("| 0-1-1: " + Engine.Facility.get("011").renderOccupantAdventurers() + " ]");
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k < 2) {
                        System.out.print("| " + i
                                + "-"
                                + j + "-"
                                + k + ":");
                        System.out.format("%4s%4s%12s",
                                Engine.Facility.get(coordinateToKey(i, j, k)).renderOccupantAdventurers(),
                                ':',
                                Engine.Facility.get(coordinateToKey(i, j, k)).renderOccupantCreatures());
                    } else {
                        System.out.print("| " + i
                                + "-"
                                + j + "-"
                                + k + ":");
                        System.out.format("%4s%4s%12s",
                                Engine.Facility.get(coordinateToKey(i, j, k)).renderOccupantAdventurers(),
                                ":",
                                Engine.Facility.get(coordinateToKey(i, j, k)).renderOccupantCreatures());
                        System.out.println("|");
                    }
                }
            }
        }
        System.out.println("+-----------------------------------------------------------------------------------+");
    }

    // prints the health and treasure status of all entities
    /*
     * code example of Identity
     * Adventurer a = (Adventurer) a0
     * uses identity as a vehicle for printing proper
     * status
     */
    private void printStatus() {
        int oCount = 0;
        int sCount = 0;
        int bCount = 0;
        System.out.println("Adventurers\t\tRoom\t\tHealth\t\tTreasure");
        for (Entity a0 : getAdventurers()) {
            Adventurer a = (Adventurer) a0;
//            System.out.println(a0.getName() + ":   \t" + a.getTreasureCount() + " Treasure(s) / " + (3 - a.getHealth())
//                    + " Damage");
            StringBuilder treasurePrint = new StringBuilder();
            for(Treasure t : a.getInventory()){
                treasurePrint.append(t.getClass().getSimpleName()).append(" ");
            }
            System.out.println(a.getClass().getSimpleName() + "\t\t\t" +
                    Arrays.toString(a.checkRoom().getCoordinates()) + "\t" +
                    a.getHealth() + "\t\t\t" +
                    treasurePrint);
        }
        for (Entity c0 : getCreatures()) {
            String cSign = c0.getSign();
            if (cSign == "O") {
                oCount++;
            } else if (cSign == "S") {
                sCount++;
            } else if (cSign == "B") {
                bCount++;
            }
        }
        System.out.println("Orbiters:\t" + oCount + " Remaining");
        System.out.println("Seekers:\t" + sCount + " Remaining");
        System.out.println("Blinkers:\t" + bCount + " Remaining");
    }

    // PUBLIC METHODS
    public int getTurn(){
        return turn;
    }
}
