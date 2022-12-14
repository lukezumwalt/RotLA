package Game;

import java.util.*;

import Board.Logger;
import Board.Render;
import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;
import Characters.Subject;
import Game.UserInterface.UserInterface;
import Treasure.*;

import static Board.Room.mapNeighborhood;

public class Engine {

    // CONSTRUCTORS
    public Engine() {
        Facility = new HashMap<>();
        Adventurers = new ArrayList<>();
        Creatures = new ArrayList<>();
        Treasures = new ArrayList<>();
        UI = UserInterface.getInstance();

        // Eager Singleton
        view = Render.getInstance();
    }

    // Factory pattern.
    // Generates a runtime player object based on user input
    // from a suite of derived Adventurer subclasses.
    private void characterCreator() {
        Scanner in = new Scanner(System.in);
        System.out.println(
                        """
                        Welcome to Raiders of the Lost Arc-tangent!
                        Please select a character from the following list of options:

                        [1] BRAWLER
                        [2] RUNNER
                        [3] SNEAKER
                        [4] THIEF
                        """);
        System.out.print("Selection: ");
        int choice = in.nextInt();
        String type = "";
        System.out.print("You chose... ");
        switch (choice) {
            case 1 -> {
                type = "Brawler";
                this.Player = new Brawler();
            }
            case 2 -> {
                type = "Runner";
                this.Player = new Runner();
            }
            case 3 -> {
                type = "Sneaker";
                this.Player = new Sneaker();
            }
            case 4 -> {
                type = "Thief";
                this.Player = new Thief();
            }
        }
        System.out.println(type + "!");
        System.out.print("\nPlease enter a name for your " + type + ": ");
        String playerName = in.next();
        this.Player.setPlayerName(playerName);

        // Object Creation
        /* this is where we would call a factory to generate an adventurer */
        /* in fact, we may have to do this regardless... */
        Adventurers.add((Entity) Player);

        // Place Player in the Facility spawn room
        Facility.get("011").occupyAdventurer(Player);
        this.Player.setCurrentRoom(Facility.get("011"));
    }

    // PUBLIC ATTRIBUTES
    // Game Board
    public static Map<String, Room> Facility;

    // PRIVATE ATTRIBUTES
    // Characters
    private static ArrayList<Entity> Adventurers;
    private static ArrayList<Entity> Creatures;
    private static ArrayList<Treasure> Treasures;
    private static UserInterface UI;
    private Adventurer Player;
    private final Render view;

    // PUBLIC METHODS
    public static ArrayList<Entity> getAdventurers() {
        return Adventurers;
    }

    public static ArrayList<Entity> getCreatures() {
        return Creatures;
    }

    public static ArrayList<Treasure> getTreasures() {
        return Treasures;
    }

    public static String coordinateToKey(int floor, int x, int y) {
        return String.valueOf(floor) + String.valueOf(x) + String.valueOf(y);
    }

    public void processAdventurers(Logger recorder) {
        for (Entity p : Adventurers) {

            // First and foremost, alive check
            if (((Adventurer) p).getAlive()) {
                // TODO: clean this up!!
                // Runner gets 2 "actions" per turn.
                if ("Runner".equals(p.getName())) {
                    // Get current room
                    Room thisRoom = p.checkRoom();
                    for (int i = 0; i < 2; i++) {
                        recorder.activate((Subject) p);
                        // Combat check, must be done before treasure check.
                        if (thisRoom.getOccupantCreatures().size() > 0) {
                            ArrayList<Entity> mobsToKill = new ArrayList<>();
                            for (Creature target : thisRoom.getOccupantCreatures()) {
                                if (p.fight((Entity) target)) {
                                    if (p.fight((Entity) target)) {
                                        // kill target creature on player victory (i.e. remove from list)
                                        mobsToKill.add((Entity) target);
                                        target.notifyObservers("died");
                                    }
                                }
                            }
                            for (Entity k : mobsToKill) {
                                k.checkRoom().leaveRoom(k);
                                Creatures.remove(k);
                            }
                            // Combat consumes the turn.
                            recorder.deactivate((Subject) p);
                            continue;
                        }
                        // Treasure check.
                        if (thisRoom.checkIfTreasure()) {
                            if (((Adventurer) p).search()) {
                                ((Adventurer) p).collectTreasure(thisRoom);
                                // Collection consumes the turn.
                                recorder.deactivate((Subject) p);
                                continue;
                            }
                        }
                        // No treasure and no creatures, move on!
                        p.move();
                        // Final creature-combat check after final movement.
                        if (thisRoom.getOccupantCreatures().size() > 0) {
                            ArrayList<Entity> mobsToKill = new ArrayList<>();
                            for (Creature target : thisRoom.getOccupantCreatures()) {
                                if (p.fight((Entity) target)) {
                                    // kill target creature on player victory (i.e. remove from list)
                                    mobsToKill.add((Entity) target);
                                    target.notifyObservers("died");
                                }
                            }
                            for (Entity k : mobsToKill) {
                                k.checkRoom().leaveRoom(k);
                                Creatures.remove(k);
                            }

                            // Combat consumes the turn.
                            recorder.deactivate((Subject) p);
                        }
                        // End of turn, finally..
                        recorder.deactivate((Subject) p);
                    }
                } else {
                    // Everyone else only gets once chance for the song and dance.
                    recorder.activate((Subject) p);

                    // Get current room
                    Room thisRoom = p.checkRoom();

                    // Combat check, must be done before treasure check.
                    if (thisRoom.getOccupantCreatures().size() > 0) {
                        ArrayList<Entity> mobsToKill = new ArrayList<>();
                        for (Creature target : thisRoom.getOccupantCreatures()) {
                            if (p.fight((Entity) target)) {
                                // kill target creature on player victory (i.e. remove from list)
                                mobsToKill.add((Entity) target);
                                target.notifyObservers("died");
                            }
                        }
                        for (Entity k : mobsToKill) {
                            k.checkRoom().leaveRoom(k);
                            Creatures.remove(k);
                        }

                        // Combat consumes the turn.
                        recorder.deactivate((Subject) p);
                        continue;
                    }

                    // TODO: Add checks to confirm adventurer is still alive after each action

                    // Treasure check.
                    if (thisRoom.checkIfTreasure()) {
                        // ((Adventurer) player).search();
                        if (((Adventurer) Player).search()) {
                            // Treasure found consumes the turn.
                            recorder.deactivate((Subject) p);
                            continue;
                        } else {
                            // Treasure not found.
                            // tracker.publishTreasureFound(Treasure);
                        }
                    }

                    // No treasure and no creatures, move on!
                    p.move();

                    // Final creature-combat check after final movement.
                    if (thisRoom.getOccupantCreatures().size() > 0) {
                        ArrayList<Entity> mobsToKill = new ArrayList<>();
                        for (Creature target : thisRoom.getOccupantCreatures()) {
                            if (p.fight((Entity) target)) {
                                // kill target creature on player victory (i.e. remove from list)
                                mobsToKill.add((Entity) target);
                                target.notifyObservers("died");
                            }
                        }
                        for (Entity k : mobsToKill) {
                            k.checkRoom().leaveRoom(k);
                            Creatures.remove(k);
                        }

                        // Combat consumes the turn.
                        recorder.deactivate((Subject) p);
                        continue;
                    }

                    // End of turn, finally..
                    recorder.deactivate((Subject) p);
                }
            }
        }
    }

    public void processPlayer(Logger recorder){
        // Loop through list of player controlled adventurers
        for(Entity player : Adventurers){
            if(player.getAlive()){
                recorder.activate((Subject) player);
                Room thisRoom = player.checkRoom();

                // Room Occupancy Check
                if(thisRoom.getOccupantCreatures().size() > 0){
                    // Current room has monsters!
                    switch(UI.solicitCommand(1)){
                        case "fight" ->{
                            // Fight all the mobs in the room
                            ArrayList<Entity> mobsToKill = new ArrayList<>();
                            for(Creature mob : thisRoom.getOccupantCreatures() ){
                                if(player.fight((Entity)mob)){
                                    mobsToKill.add((Entity) mob);
                                    mob.notifyObservers("died");
                                }
                            }
                            // Cull defeated mobs
                            for (Entity k : mobsToKill) {
                                k.checkRoom().leaveRoom(k);
                                Creatures.remove(k);
                            }
                        }
                        case "flee" ->{
                            // Take damage equal to number of mobs in exited room
                            int totalDamage = 0;
                            for(Creature mob : thisRoom.getOccupantCreatures() ){
                                totalDamage++;
                            }
                            player.move();
                            ((Adventurer)player).takeDamage(totalDamage);
                        }
                    }
                }
                else{
                    // Current room is empty...
                    switch(UI.solicitCommand(0)){
                        case "move" ->{
                            player.move();
                        }
                        case "search" ->{
                            ((Adventurer)player).search();
                        }
                        case "celebrate" ->{
                            ((Adventurer)player).celebrate();
                        }
                    }
                }
                recorder.deactivate((Subject) player);
            }
        }
    }

    public void processCreatures(Logger recorder) {
        ArrayList<Entity> mobsToDie = new ArrayList<>();
        for (Entity monster : Creatures) {

            // Top of turn, BAE
            recorder.activate((Subject) monster);

            // Get current room.
            Room thisRoom = monster.checkRoom();

            // If no Adventurers to fight, move on.
            if (thisRoom.getOccupantAdventurers().size() == 0) {
                monster.move();
            }

            // After either moving or not, fight any Adventurers in the room.
            for (Adventurer target : thisRoom.getOccupantAdventurers()) {
                if (monster.fight((Entity) target)) {
                    // Monster wins! Report event
                } else {
                    // Monster lost, marked for death
                    mobsToDie.add(monster);
                }
            }

            // End of turn, finally
            recorder.deactivate((Subject) monster);
        }
        for (Entity m : mobsToDie) {
            m.checkRoom().leaveRoom(m);
            Creatures.remove(m);
        }
    }

    public boolean endConditionMet() {

        // First, check if adventurer returned to spawn room
        if(Player.checkRoom() == Facility.get("011")){
            // Treasure Victory Check
            int totalTreasure = 0;
            for (Entity a : Adventurers) {
                totalTreasure += ((Adventurer) a).getTreasureCount();
            }
            if (totalTreasure >= 5) {
                System.out.println("\n\nADVENTURER(S) WIN!\n\tThey collected: " + totalTreasure + " treasure!\n\n\tGG!");
                return true;
            }
            // Dead Creatures Victory Check
            else if (Creatures.size() == 0) {
                System.out.println("\n\nADVENTURER(S) WIN!\n\tThey defeated every creature!\n\n\tGG!");
                return true;
            }
            else{
                // Condition where hero returned to spawn without one of each treasure nor defeating all creatures
                System.out.println("\n\nADVENTURER(S) LOSE!\n\tThey left the dungeon before meeting a victory condition!\n\n\tTry again!");
                return true;
            }
        }

        // Dead Adventurers Victory Check
        int totalHealth = 0;
        for (Entity a : Adventurers) {
            totalHealth += ((Adventurer) a).getHealth();
        }
        if (totalHealth <= 0) {
            System.out.println("\n\nCREATURE(S) WIN!\n\tThey defeated every adventurer!\n\n\tTry again!");
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
        // // Place all adventurers in the adventurer spawn room.
        // for (Entity a0 : Adventurers) {
        // Adventurer a = (Adventurer) a0;
        // Facility.get("011").occupyAdventurer(a);
        // a.setCurrentRoom(Facility.get("011"));
        // }

        // Place all creatures in random rooms.
        Random r = new Random();
        for (Entity c0 : Creatures) {
            Creature c = (Creature) c0;
            int x, y, floor;

            // Selecting floor and position for a creature to spawn, excluding adventurer
            // spawn room.
            x = r.nextInt(2);
            y = r.nextInt(2);
            floor = r.nextInt(3) + 1;

            // Condition for which an orbiter can legally spawn, as they must avoid center
            // rooms.
            if ("O".equals(c.getSign())) {
                while (x == 1 && y == 1) {
                    x = r.nextInt(2);
                    y = r.nextInt(2);
                }
            }

            // Place creature in room.
            Facility.get(coordinateToKey(floor, x, y)).occupyCreature(c);
            c.setCurrentRoom(Facility.get(coordinateToKey(floor, x, y)));
        }

        // Place all Treasures in random rooms.
        Random randomTreasure = new Random();
        for (Treasure t : Treasures) {
            int a, b, level;

            // Selecting floor and position for a creature to spawn, excluding adventurer
            // spawn room.
            // while(totalTreasuresPlaced < 24)
            do {
                level = randomTreasure.nextInt(4) + 1;
                a = randomTreasure.nextInt(3);
                b = randomTreasure.nextInt(3);
                if (!Facility.get(coordinateToKey(level, a, b)).checkIfTreasure()) {
                    Facility.get(coordinateToKey(level, a, b)).occupyTreasure(t);
                    break;
                }
            } while (true);
            // Place creature in room.
        }
    }

    public void initialize() {
        // Instantiate Adventurers
//        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
        // Instantiate Treasures
        initializeTreasures();
        // Instantiate Game Board
        createBlankBoard();
        initializeBoard();
        mapNeighborhood();
    }

    public void playerStart(){
        // User Interface
        view.printBoard();
        characterCreator();
    }

    private void initializeAdventurers() {
         Adventurers.add(new Brawler());
         Adventurers.add(new Sneaker());
         Adventurers.add(new Runner());
         Adventurers.add(new Thief());
    }

    // Factory pattern.
    // Indiscriminate runtime production of Creature subclasses.
    private void initializeCreatures() {
        for (int i = 0; i < 4; ++i) {
            Creatures.add(new Orbiter());
            Creatures.add(new Seeker());
            Creatures.add(new Blinker());
        }
    }

    private void initializeTreasures() {
        for (int i = 0; i < 4; ++i) {
            Treasures.add(new Armor());
            Treasures.add(new Gem());
            Treasures.add(new Portal());
            Treasures.add(new Potion());
            Treasures.add(new Sword());
            Treasures.add(new Trap());
        }
    }

    public void runOneTurn() {
        // Logger recorder = new Logger();
        Logger recorder = Logger.getInstance();
//        processAdventurers(recorder);
        processPlayer(recorder);
        processCreatures(recorder);
        view.printFrame();
        // recorder.closeFrame(view.getTurn());
        recorder.terminate(view.getTurn());
    }
}
