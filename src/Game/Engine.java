package Game;

import java.util.ArrayList;

import Board.Room;
import Characters.Entity;
import Characters.Enemies.*;
import Characters.Friendlies.*;

public class Engine extends TurnOrchestrator {

    // Game Board
    Room[][][] Facility = new Room[5][3][3];
    Room Entrance = new Room(0,1,1);

    // Characters
    ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    ArrayList<Entity> Creatures = new ArrayList<Entity>();

    void initialize(){
        // Instantiate Game Board
        initializeBoard();
        // Instantiate Adventurers
        initializeAdventurers();
        // Instantiate Creatures
        initializeCreatures();
    }

    void initializeBoard(){
        //
    }
    void initializeAdventurers(){
        Adventurers.add(new Brawler());
        Adventurers.add(new Sneaker());
        Adventurers.add(new Runner());
        Adventurers.add(new Thief());
    }
    void initializeCreatures(){
        Characters.Entity o = new Orbiter();
        Characters.Entity s = new Seeker();
        Characters.Entity b = new Blinker();
        for( int i = 0; i < 4; ++i ) {
            Creatures.add(o);
            Creatures.add(s);
            Creatures.add(b);
        }
    }

    public Room getRoom(int[] coordinates) {
        return Facility[coordinates[0]][coordinates[1]][coordinates[2]];
    }

}
