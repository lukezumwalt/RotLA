import java.util.ArrayList;

public class Engine extends TurnOrchestrator {

    // Game Board
    Room[][][] Facility = new Room[5][3][3];
    Room Entrance = new Room();

    // Characters
    ArrayList<Entity> Adventurers = new ArrayList<Entity>();
    ArrayList<Entity> Creatures = new ArrayList<Entity>();

    void initializeGame(){
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
        Adventurers.get(0).move();
//        Adventurers.add(new Sneaker());
    }
    void initializeCreatures(){
        Entity o = new Orbiter();
//        Entity s = new Seeker();
//        Entity b = new Blinker();
        for( int i = 0; i < 4; ++i ) {
            Creatures.add(o);
//            Creatures.add(s);
//            Creatures.add(b);
        }
    }


}
