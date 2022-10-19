package Characters.Action.Move;

import Board.Room;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

public class npcMovement extends moveStyle {

    public void move(Adventurer self){
        // check room to return valid moves
        String[] addresses = inspectNeighbors(self.checkRoom());
        // randomly select a valid move from that list
        int choice;
        if (addresses.length <= 1) {
            choice = 0;
        } else {
            Random r = new Random();
            choice = r.nextInt(0, addresses.length);
        }
        Room newRoom = Facility.get(addresses[choice]);

        // finally:
        self.checkRoom().leaveRoom((Entity) self);
        self.setCurrentRoom(newRoom);
        newRoom.occupyAdventurer(self);

        // Report Adventurer entered new room:
        self.notifyObservers("roomEntered");
    }
}
