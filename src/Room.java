import java.lang.reflect.Array;
import java.util.ArrayList;

public class Room {
    boolean treasureAvailable = true;
    ArrayList<Room> adjacentRooms;
    {
        adjacentRooms = new ArrayList<>();
    }
    ArrayList<Entity> occupants = new ArrayList<>();
}
