import java.util.ArrayList;

import Board.Room;
import Characters.Entity;
import Characters.Sneaker;

public class Engine extends TurnOrchestrator {

    // Game Board
    Room[][][] Facility = new Room[5][3][3];
    Room Entrance = new Room();

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
        Adventurers.add(new Characters.Brawler());
        Adventurers.add(new Characters.Sneaker());
        Adventurers.add(new Characters.Runner());
        Adventurers.add(new Characters.Thief());
    }
    void initializeCreatures(){
        Characters.Entity o = new Characters.Orbiter();
        Characters.Entity s = new Characters.Seeker();
        Characters.Entity b = new Characters.Blinker();
        for( int i = 0; i < 4; ++i ) {
            Creatures.add(o);
            Creatures.add(s);
            Creatures.add(b);
        }
    }


}
