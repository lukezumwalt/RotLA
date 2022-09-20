package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;

public class Engine extends TurnOrchestrator {

    Engine() {
        Facility = new HashMap<>();
    }

    // Game Board
    public static Map<String, Room> Facility;

    // Characters
    ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    ArrayList<Entity> Creatures = new ArrayList<Entity>();

    void initialize() {
        // Instantiate Game Board
        createBlankBoard();
        initializeBoard();
        // Instantiate Adventurers
        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
    }

    private void createBlankBoard() {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    Facility.put(coordinateToKey(i, j, k), new Room());
                }
            }
        }
    }

    private void initializeBoard() {
        Characters.Enemies.Orbiter O = new Orbiter();
        Facility.get("011").occupyAdventurer(new Brawler());
        Facility.get("011").occupyAdventurer(new Runner());
        Facility.get("011").occupyAdventurer(new Sneaker());
        Facility.get("011").occupyAdventurer(new Thief());
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Randomized adding creatures here
                    Facility.get(coordinateToKey(i, j, k)).occupyCreature(O);
                    // System.out.println("Adding Orbiter!! "+i+j+k);
                }
            }
        }
    }

    private void initializeAdventurers() {
        // ! @TODO: use these guys to populate board
        Adventurers.add(new Brawler());
        Adventurers.add(new Sneaker());
        Adventurers.add(new Runner());
        Adventurers.add(new Thief());
    }

    private void initializeCreatures() {
        // ! @TODO: use these guys to populate board
        Characters.Entity o = new Orbiter();
        Characters.Entity s = new Seeker();
        Characters.Entity b = new Blinker();
        for (int i = 0; i < 4; ++i) {
            Creatures.add(o);
            Creatures.add(s);
            Creatures.add(b);
        }
    }

    public String coordinateToKey(int x, int y, int z) {
        return String.valueOf(x) + String.valueOf(y) + String.valueOf(z);
    }

    // public Room getRoom(int[] coordinates) {
    //// return Facility[coordinates[0]][coordinates[1]][coordinates[2]];
    // return
    // Facility.get(String.valueOf(coordinates[0])+String.valueOf(coordinates[1])+String.valueOf(coordinates[2]));
    // }

}
