package Characters.Friendlies;

import Board.Room;

public class Adventurer {
    protected Room currentRoom;
    protected final String entityType = "adventurer";
    protected String sign;
    protected String name;
    protected int health;
    private int treasureCount;
    public String getSign(){ return sign; }

    public void takeDamage(){
        health--;
    }

    //! Getter Suite
    public int getHealth(){
        return health;
    }
    public int getTreasureCount(){
        return treasureCount;
    }
    public void collectTreasure( Room r ){
        r.takeTreasure();
        treasureCount++;
    }
    public void setCurrentRoom( Room newRoom ){
        currentRoom = newRoom;
    }

//    public abstract boolean rollForTreasure();
}
