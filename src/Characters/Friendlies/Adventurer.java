package Characters.Friendlies;

import Board.Room;
import Characters.Entity;


public class Adventurer {
    private String type;
    private int health;
    private int treasureCount;
    protected Room currentRoom;
    protected final String entityType = "adventurer";

    void takeDamage(){
        health--;
    }

    //! Getter Suite
    String getType(){
        return type;
    }
    void setType( String t ){ type = t; };
    int getHealth(){
        return health;
    }
    int getTreasureCount(){
        return treasureCount;
    }
    void collectTreasure( Room r ){
        r.takeTreasure();
        treasureCount++;
    }
}
