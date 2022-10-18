package Game.UserInterface;

import java.util.Scanner;

public class UserInterface {

    private static UserInterface uniqueInstance;
    private Scanner input;
    private UserInterface() {
        // Empty Private Constructor
        input = new Scanner(System.in);
    }

    // Singleton Instance Control
    public static UserInterface getInstance(){
        if(uniqueInstance == null){
            // Construction
            uniqueInstance = new UserInterface();
        }
        return uniqueInstance;
    }

    public void solicitCommand(int stateOption){
        switch(stateOption){
            // Empty room option.
            case 0 ->{
                // Provide user with options:
                System.out.println(
                    """
                    The room appears unoccupied...
                    What would you like to do?
                    
                    [1] Move
                    [2] Search for Treasure
                    [3] Celebrate!
                    
                    """
                );

                // Solicit choice:
                System.out.print("Selection: ");
                switch(input.nextInt()){
                    case 1 ->{
                        // execute movement command query
                    }
                    case 2 ->{
                        // execute search command query
                    }
                    case 3 ->{
                        // execute celebration command query
                    }
                }

            }
            // Occupied room option.
            case 1 ->{

                // Provide user with options:
                System.out.println(
                    """
                    You've run into some monsters!
                    What would you like to do?
                    
                    [1] Fight!  >:D
                    [2] Run!    <:O
                    
                    """
                );

                // Solicit choice:
                System.out.print("Selection: ");
                switch(input.nextInt()){
                    case 1 ->{
                        // execute fight command query
                    }
                    case 2 ->{
                        // execute flee command query
                        // essentially a move command but with a notion to take 1dmg/creature in current room
                    }
                }
            }
        }
    }
}
