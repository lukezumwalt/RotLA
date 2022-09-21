package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;

import static Board.Room.mapNeighborhood;

public class Engine {

    Engine() {
        Facility = new HashMap<>();
    }

    // Game Board
    public static Map<String, Room> Facility;

    // Characters
    ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    ArrayList<Entity> Creatures = new ArrayList<Entity>();

    void initialize() {
        // Instantiate Adventurers
        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
        // Instantiate Game Board
        createBlankBoard();
        initializeBoard();
        mapNeighborhood();
    }

    private void createBlankBoard() {
        // Spawn Room
        Facility.put("011",new Room(0,1,1));
        // Dungeon
        for (int i = 1; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    Facility.put(coordinateToKey(i, j, k), new Room(i,j,k));
                }
            }
        }
    }

    private void initializeBoard() {
        Characters.Enemies.Orbiter O = new Orbiter();

        // Place all adventurers in the spawn room
        for( Entity a0 : Adventurers ){
            Adventurer a = (Adventurer)a0;
            Facility.get("011").occupyAdventurer(a);
            a.setCurrentRoom(Facility.get("011"));
        }

        // Place all creatures in random rooms
        Random r = new Random();
        for( Entity c0 : Creatures ){
            Creature c = (Creature)c0;

        }

        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Randomized adding creatures here
                    Facility.get(coordinateToKey(i,j,k)).occupyCreature(new Orbiter());
                    Facility.get(coordinateToKey(i,j,k)).occupyCreature(new Seeker());
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

    public static String coordinateToKey(int x, int y, int z) {
        return String.valueOf(x) + String.valueOf(y) + String.valueOf(z);
    }

    public void processAdventurers(){
        ArrayList<Entity> targets = new ArrayList<>();
        for( Entity player : Adventurers ){
            Room thisRoom = player.checkRoom();
            //                case "brawler":
            if ("runner".equals(player.getName())) {
                for (int i = 0; i < 4; i++) {
                    player.move();
                }
            }
            player.move();
//                case "sneaker":
//                case "thief":
//            for( Creature target : thisRoom.getOccupantCreatures() ){
//                player.fight((Entity) target);
//            }
//            player.move();
//            for( Creature target : thisRoom.getOccupantCreatures() ){
//                player.fight((Entity) target);
//            }
        }
    }

    public void processCreatures( ArrayList<Entity> Creatures ){
        for( Entity monster : Creatures ){
            Room thisRoom = monster.checkRoom();
            if( thisRoom.getOccupantAdventurers().size() == 0 ){
                monster.move();
            }
            for( Adventurer target : thisRoom.getOccupantAdventurers() ){
                monster.fight((Entity) target);
            }
        }
    }

    // public Room getRoom(int[] coordinates) {
    //// return Facility[coordinates[0]][coordinates[1]][coordinates[2]];
    // return
    // Facility.get(String.valueOf(coordinates[0])+String.valueOf(coordinates[1])+String.valueOf(coordinates[2]));
    // }

}
