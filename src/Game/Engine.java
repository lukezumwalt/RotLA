package Game;

import java.util.ArrayList;

import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;

public class Engine extends TurnOrchestrator {

    // Game Board
    Room[][][] Facility = new Room[5][3][3];
    Room Entrance = Facility[0][1][1];
    // Room Entrance = new Room(0, 1, 1);

    // Characters
    ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    ArrayList<Entity> Creatures = new ArrayList<Entity>();

    void initialize() {
        // Instantiate Game Board
        initializeBoard();
        // Instantiate Adventurers
        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
    }

    void initializeBoard() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 3; j++)
                for (int z = 0; z < 3; z++) {
                    System.out.println("arr[" + i
                            + "]["
                            + j + "]["
                            + z + "] = "
                            + Facility[i][j][z]);
                }
        for (Room x : Facility) {
            if (x == Entrance) {
                // place adventurers here
            } else if (x != Entrance) {
                // pick random rooms for creatures
            }
        }
    }

    void initializeAdventurers() {
        Adventurers.add(new Brawler());
        Adventurers.add(new Sneaker());
        Adventurers.add(new Runner());
        Adventurers.add(new Thief());
    }

    void initializeCreatures() {
        Characters.Entity o = new Orbiter();
        Characters.Entity s = new Seeker();
        Characters.Entity b = new Blinker();
        for (int i = 0; i < 4; ++i) {
            Creatures.add(o);
            Creatures.add(s);
            Creatures.add(b);
        }
    }

    public Room getRoom(int[] coordinates) {
        return Facility[coordinates[0]][coordinates[1]][coordinates[2]];
    }

}
