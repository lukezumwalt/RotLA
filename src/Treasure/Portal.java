package Treasure;

import Board.Room;
import Characters.Friendlies.Adventurer;
import Characters.Entity;

import java.util.Random;

import static Game.Engine.Facility;
import static Game.Engine.coordinateToKey;
import static Utilities.Dice.rollD6;

public class Portal extends Treasure {

    public static void teleport(Entity Adventurer) {
        // Blinker moves to any random room in the Facility.
        Random r = new Random();
        int floor = r.nextInt(3) + 1;
        int x = r.nextInt(2);
        int y = r.nextInt(2);
        Room newRoom = Facility.get(coordinateToKey(floor, x, y));

        // Update knowledge of position.
        Adventurer.checkRoom().leaveRoom(Adventurer);
        ((Characters.Friendlies.Adventurer) Adventurer).setCurrentRoom(newRoom);
        newRoom.occupyAdventurer((Characters.Friendlies.Adventurer) Adventurer);
    }
}
