package Treasure;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Characters.Entity;

import java.util.Random;

import static Game.Engine.Facility;
import static Game.Engine.coordinateToKey;

public class Portal extends Treasure {

    //! @TODO: current solution, teleport immediately upon pickup...
    @Override
    public void activate(Adventurer self){
        teleport(self);
    }
    public static void teleport(Adventurer self) {

        // Select a random room configuration, excluding spawn.
        Random r = new Random();
        int floor = r.nextInt(3) + 1;
        int x = r.nextInt(2);
        int y = r.nextInt(2);
        Room newRoom = Facility.get(coordinateToKey(floor, x, y));

        // Update knowledge of position.
        self.checkRoom().leaveRoom((Entity) self);
        self.setCurrentRoom(newRoom);
        newRoom.occupyAdventurer(self);
    }
}
