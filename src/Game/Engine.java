package Game;

import java.util.*;

import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;
import Utilities.CircularLinkedList;

import static Board.Room.mapNeighborhood;

public class Engine {

    // CONSTRUCTORS
    Engine() {
        Facility = new HashMap<>();
    }

    // PUBLIC ATTRIBUTES
    // Game Board
    public static Map<String, Room> Facility;

    // PRIVATE ATTRIBUTES
    // Characters
    private static ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    private static ArrayList<Entity> Creatures = new ArrayList<Entity>();

    // PUBLIC METHODS
    // ! Getter Suite
    public static ArrayList<Entity> getAdventurers() {
        return Adventurers;
    }

    public static ArrayList<Entity> getCreatures() {
        return Creatures;
    }

    public void initialize() {
        // Instantiate Adventurers
        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
        // Instantiate Game Board
        createBlankBoard();
        initializeBoard();
        mapNeighborhood();
    }

    public static String coordinateToKey(int floor, int x, int y) {
        return String.valueOf(floor) + String.valueOf(x) + String.valueOf(y);
    }

    public void processAdventurers() {
        ArrayList<Entity> targets = new ArrayList<>();
        for (Entity player : Adventurers) {
            Room thisRoom = player.checkRoom();

            // Runner gets 3 "actions" per turn.
            // ! @TODO: Expand this to do more than move
            if ("runner".equals(player.getName())) {
                for (int i = 0; i < 4; i++) {
                    player.move();
                }
            }

            // Combat check, must be done before treasure check.
            if (thisRoom.getOccupantCreatures().size() > 0) {
                for (Creature target : thisRoom.getOccupantCreatures()) {
                    player.fight((Entity) target);
                }
                // Combat consumes the turn.
                 continue;
            }

            // Treasure check.
            if (thisRoom.checkIfTreasure()) {
                if (player.rollForTreasure()) {
                    ((Adventurer) player).collectTreasure(thisRoom);
                    // Collection consumes the turn.
                    continue;
                }
            }

            // No treasure and no creatures, move on broth!
            player.move();
        }
    }

    public void processCreatures() {
        for (Entity monster : Creatures) {
            Room thisRoom = monster.checkRoom();

            // If no Adventurers to fight, move on.
            if (thisRoom.getOccupantAdventurers().size() == 0) {
                monster.move();
            }

            // After either moving or not, fight any Adventurers in the room.
            for (Adventurer target : thisRoom.getOccupantAdventurers()) {
                monster.fight((Entity) target);
            }
        }
    }

    // public void killCreatures() {
    // for (Entity monster1 : Creatures) {
    // checkAliveCreatures(monster1);
    // }
    // }

    // public static void checkAliveCreatures(Entity monster) {
    // if (monster.getAlive() == true) {
    // Engine.Creatures.remove(monster);
    // }
    // return;
    // }

    public boolean endConditionMet() {

        // Treasure Victory Check
        int totalTreasure = 0;
        for (Entity a : Adventurers) {
            totalTreasure += ((Adventurer) a).getTreasureCount();
        }
        if (totalTreasure >= 10) {
            System.out.println("\n\nADVENTURERS WIN!\n\tThey collected: " + totalTreasure + " treasure!\n\n\tGG!");
            return true;
        }

        // Dead Creatures Victory Check
        if (Creatures.size() == 0) {
            System.out.println("\n\nADVENTURERS WIN!\n\tThey defeated every creature!\n\n\tGG!");
            return true;
        }

        // Dead Adventurers Victory Check
        if (Adventurers.size() == 0) {
            System.out.println("\n\nCREATURES WIN!\n\tThey defeated every adventurer!\n\n\tGG!");
            return true;
        }

        // No Victory Condition
        return false;
    }

    // PRIVATE METHODS
    private void createBlankBoard() {
        // Spawn Room
        Facility.put("011", new Room(0, 1, 1));
        // Dungeon
        for (int i = 1; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    Facility.put(coordinateToKey(i, j, k), new Room(i, j, k));
                }
            }
        }
    }

    private void initializeBoard() {
        Characters.Enemies.Orbiter O = new Orbiter();

        // Place all adventurers in the adventurer spawn room.
        for (Entity a0 : Adventurers) {
            Adventurer a = (Adventurer) a0;
            Facility.get("011").occupyAdventurer(a);
            a.setCurrentRoom(Facility.get("011"));
        }

        // Place all creatures in random rooms.
        Random r = new Random();
        for (Entity c0 : Creatures) {
            Creature c = (Creature) c0;
            int x, y, floor;

            // Selecting floor and position for a creature to spawn, excluding adventurer
            // spawn room.
            x = r.nextInt(2);
            y = r.nextInt(2);
            floor = r.nextInt(4 - 1) + 1;

            // Condition for which an orbiter can legally spawn, as they must avoid center
            // rooms.
            if ("O".equals(c.getSign())) {
                while (x == 1 && y == 1) {
                    x = r.nextInt(2);
                    y = r.nextInt(2);
                }
                ((Orbiter)c).setCurrentRoomNode(new CircularLinkedList.Node(coordinateToKey(floor, x, y)));
            }

            // Place creature in room.
            Facility.get(coordinateToKey(floor, x, y)).occupyCreature(c);
            c.setCurrentRoom(Facility.get(coordinateToKey(floor, x, y)));
        }
    }

    private void initializeAdventurers() {
        Adventurers.add(new Brawler());
        Adventurers.add(new Sneaker());
        Adventurers.add(new Runner());
        Adventurers.add(new Thief());
    }

    // A lot of overhead for our selection of an orbiter movement scheme.
    //! @TODO: In future refactor, visit more concise ways to capture orbiter movement...
    CircularLinkedList F1 = new CircularLinkedList();
    CircularLinkedList F2 = new CircularLinkedList();
    CircularLinkedList F3 = new CircularLinkedList();
    CircularLinkedList F4 = new CircularLinkedList();

    public static ArrayList<CircularLinkedList> OrbiterDungeonMap = new ArrayList<>();

    private void initializeCreatures() {
        for (int i = 0; i < 4; ++i) {
            Creatures.add(new Orbiter());
            Creatures.add(new Seeker());
            Creatures.add(new Blinker());
        }

        // Build out orbiter movement options..
        F1.add(coordinateToKey(1, 0, 0));
        F1.add(coordinateToKey(1, 0, 1));
        F1.add(coordinateToKey(1, 0, 2));
        F1.add(coordinateToKey(1, 1, 0));
        F1.add(coordinateToKey(1, 1, 2));
        F1.add(coordinateToKey(1, 2, 0));
        F1.add(coordinateToKey(1, 2, 1));
        F1.add(coordinateToKey(1, 2, 2));

        F2.add(coordinateToKey(2, 0, 0));
        F2.add(coordinateToKey(2, 0, 1));
        F2.add(coordinateToKey(2, 0, 2));
        F2.add(coordinateToKey(2, 1, 0));
        F2.add(coordinateToKey(2, 1, 2));
        F2.add(coordinateToKey(2, 2, 0));
        F2.add(coordinateToKey(2, 2, 1));
        F2.add(coordinateToKey(2, 2, 2));

        F3.add(coordinateToKey(3, 0, 0));
        F3.add(coordinateToKey(3, 0, 1));
        F3.add(coordinateToKey(3, 0, 2));
        F3.add(coordinateToKey(3, 1, 0));
        F3.add(coordinateToKey(3, 1, 2));
        F3.add(coordinateToKey(3, 2, 0));
        F3.add(coordinateToKey(3, 2, 1));
        F3.add(coordinateToKey(3, 2, 2));

        F4.add(coordinateToKey(4, 0, 0));
        F4.add(coordinateToKey(4, 0, 1));
        F4.add(coordinateToKey(4, 0, 2));
        F4.add(coordinateToKey(4, 1, 0));
        F4.add(coordinateToKey(4, 1, 2));
        F4.add(coordinateToKey(4, 2, 0));
        F4.add(coordinateToKey(4, 2, 1));
        F4.add(coordinateToKey(4, 2, 2));

        OrbiterDungeonMap.add(F1);
        OrbiterDungeonMap.add(F2);
        OrbiterDungeonMap.add(F3);
        OrbiterDungeonMap.add(F4);
    }
}
