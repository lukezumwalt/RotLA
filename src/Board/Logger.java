package Board;

import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Characters.Subject;

import java.util.Arrays;

public class Logger extends Observer{
    // instantiated at beginning of each full Adv and Creature turn
    // close at end of each turn
    // subscribe for published events occurring during a turn
    // write each event to a txt file
    //      Logger-n.txt, n = turn number

    private Room loggedRoom;
    private int loggedHealth;

    public Logger(Subject s){
        this.subject = s;
        this.subject.registerObserver(this);
    }

    public void deactivate(){
        this.subject.unregisterObserver(this);
    }

    @Override
    public void updateAdventurerStatus(Adventurer self, String eventID) {

        switch(eventID){
            case "roomEntered":
                System.out.println( self.getClass().getSimpleName() + " entered room " + Arrays.toString(self.checkRoom().getCoordinates()));
                break;

            case "wonCombat":
                break;

            case "lostCombat":
                break;

            case "celebration":
                break;

            case "tookDamage":
                System.out.println(self.getClass().getSimpleName() + " took damage.");
                break;

            case "died":
                break;

            case "treasureFound":
                break;
        }
    }

    @Override
    public void updateCreatureStatus(Creature self, String eventID) {
        switch(eventID){
            case "roomEntered":
                break;

            case "wonCombat":
                break;

            case "lostCombat":
                break;

            case "died":
                break;
        }
    }

    // store all events and log to file at end of turn
    // Destructor?
}
