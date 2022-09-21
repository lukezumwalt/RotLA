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
    private static ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    private static ArrayList<Entity> Creatures = new ArrayList<Entity>();

    public static ArrayList<Entity> getAdventurers(){
        return Adventurers;
    }
    public static ArrayList<Entity> getCreatures(){
        return Creatures;
    }

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

        // Place all adventurers in the adventurer spawn room.
        for( Entity a0 : Adventurers ){
            Adventurer a = (Adventurer)a0;
            Facility.get("011").occupyAdventurer(a);
            a.setCurrentRoom(Facility.get("011"));
        }

        // Place all creatures in random rooms.
        Random r = new Random();
        for( Entity c0 : Creatures ){
            Creature c = (Creature)c0;
            int x, y, floor;

            // Selecting floor and position for a creature to spawn, excluding adventurer spawn room.
            x = r.nextInt(2);
            y = r.nextInt(2);
            floor = r.nextInt(4-1)+1;

            // Condition for which an orbiter can legally spawn, as they must avoid center rooms.
            if("O".equals(c.getSign())){
                while(x==1 && y==1){
                    x = r.nextInt(2);
                    y = r.nextInt(2);
                }
            }

            // Place creature in room.
            Facility.get(coordinateToKey(floor,x,y)).occupyCreature(c);
        }
    }

    private void initializeAdventurers() {
        Adventurers.add(new Brawler());
        Adventurers.add(new Sneaker());
        Adventurers.add(new Runner());
        Adventurers.add(new Thief());
    }

    private void initializeCreatures() {
        for (int i = 0; i < 4; ++i) {
            Creatures.add(new Orbiter());
            Creatures.add(new Seeker());
            Creatures.add(new Blinker());
        }
    }

    public static String coordinateToKey(int floor, int x, int y) {
        return String.valueOf(floor) + String.valueOf(x) + String.valueOf(y);
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
            if(thisRoom.checkIfTreasure()){
//                thisRoom.takeTreasure();
                ((Adventurer)player).collectTreasure(thisRoom);
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

    public boolean endConditionMet() {

        // Treasure Victory Check
        int totalTreasure = 0;
        for( Entity a: Adventurers ){
            totalTreasure += ((Adventurer)a).getTreasureCount();
        }
        if( totalTreasure >= 10 ){
            System.out.println("\n\nADVENTURERS WIN!\n\tThey collected: " + totalTreasure + " treasure!\n\n\tGG!");
            return true;
        }

        // Dead Creatures Victory Check
        if( Creatures.size() == 0 ){
            System.out.println("\n\nADVENTURERS WIN!\n\tThey defeated every creature!\n\n\tGG!");
            return true;
        }

        // Dead Adventurers Victory Check
        if( Adventurers.size() == 0 ){
            System.out.println("\n\nCREATURES WIN!\n\tThey defeated every adventurer!\n\n\tGG!");
            return true;
        }

        // No Victory Condition
        return false;
    }

    // public Room getRoom(int[] coordinates) {
    //// return Facility[coordinates[0]][coordinates[1]][coordinates[2]];
    // return
    // Facility.get(String.valueOf(coordinates[0])+String.valueOf(coordinates[1])+String.valueOf(coordinates[2]));
    // }

}
