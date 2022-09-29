package Treasure;

import java.util.ArrayList;
import java.util.Random;
import Board.*;
import Game.Engine;

public class Treasure {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;

    // PUBLIC METHODS

    public static Treasure randomTreasure(ArrayList<Treasure> treasureList) {
        int index = new Random().nextInt(treasureList.size());
        Treasure randomTreasure = treasureList.get(index);
        return randomTreasure;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }
}
