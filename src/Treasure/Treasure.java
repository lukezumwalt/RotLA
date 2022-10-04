package Treasure;

import java.util.ArrayList;
import java.util.Random;
import Board.*;
import Characters.Entity;
import Characters.Friendlies.Adventurer;
import Game.Engine;

public class Treasure {

    // PROTECTED ATTRIBUTES
    protected Room currentRoom;

    // PUBLIC METHODS
    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

}
