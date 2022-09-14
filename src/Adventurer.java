public class Adventurer {
    private String type;
    private int health;
    private int treasureCount;
    Room currentRoom;


    void takeDamage(){
        health--;
    }

    //! Getter Suite
    String getType(){
        return type;
    }
    int getHealth(){
        return health;
    }
    int getTreasureCount(){
        return treasureCount;
    }
}
