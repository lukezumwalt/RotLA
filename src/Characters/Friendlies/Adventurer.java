package Characters.Friendlies;

import Board.Room;
import Characters.Entity;


public class Adventurer {
    private int health;
    private int treasureCount;
    protected Room currentRoom;
    protected String entityType = "adventurer";
    protected String sign;
    protected String name;
    public String getSign(){ return sign; }

    void takeDamage(){
        health--;
    }

    //! Getter Suite
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
    public void setCurrentRoom( Room r ){
        currentRoom = r;
    }
}
