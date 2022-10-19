package Characters.Action.Move;

import Board.Room;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.util.Random;
import java.util.Scanner;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

public class playerMovement extends moveStyle {

    public void move(Adventurer self){
        Scanner userInput = new Scanner(System.in);

        // Check room to return valid moves
        String[] addresses = inspectNeighbors(self.checkRoom());

        // Inquire the user on their movement selection:
        System.out.println("Select a room to enter: ");
        for(int i = 0; i < addresses.length; i++){
            System.out.println("[" + i + "] " + addresses[i]);
        }
        System.out.print("Selection: ");
        int choice = userInput.nextInt();
        Room newRoom = Facility.get(addresses[choice]);

        // Transition between source and destination rooms:
        self.checkRoom().leaveRoom((Entity) self);
        self.setCurrentRoom(newRoom);
        newRoom.occupyAdventurer(self);

        // Report Adventurer entered new room:
        self.notifyObservers("roomEntered");
    }
}
