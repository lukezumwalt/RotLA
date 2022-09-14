package Board;

import Characters.Entity;
import java.util.ArrayList;

public class Room {
    boolean treasureAvailable = true;
    ArrayList<Room> adjacentRooms;
    {
        adjacentRooms = new ArrayList<>();
    }
    ArrayList<Entity> occupants = new ArrayList<>();

    boolean checkIfTreasure(){ return treasureAvailable; }

}
