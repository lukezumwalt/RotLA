package Board;

import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Characters.Subject;

import java.util.Arrays;

public class Logger extends Observer {
    // instantiated at beginning of each full Adv and Creature turn
    // close at end of each turn
    // subscribe for published events occurring during a turn
    // write each event to a txt file
    // Logger-n.txt, n = turn number

    private Room loggedRoom;
    private int loggedHealth;

    public Logger(Subject s) {
        System.out.println("Entered Logger.");
        this.subject = s;
        this.subject.registerObserver(this);
    }

    public void deactivate() {
        System.out.println("Exiting Logger.");
        this.subject.unregisterObserver(this);
    }

    @Override
    public void updateAdventurerStatus(Subject subject, String eventID) {
        System.out.println("Entered Update.");
        System.out.println(eventID);
        switch (eventID) {
            case "roomEntered":
                System.out.println(subject.getClass().getSimpleName() + " entered room "
                        + Arrays.toString(((Adventurer) subject).checkRoom().getCoordinates()));
                break;

            case "wonCombat":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " won a fight.");
                break;

            case "lostCombat":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " lost a fight.");
                break;

            case "celebration":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " celebrated.");
                break;

            case "tookDamage":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " took damage.");
                break;

            case "died":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " died.");
                System.out.println(((Adventurer) subject).getAlive());
                break;

            case "treasureFound":
                System.out.println(((Adventurer) subject).getClass().getSimpleName() + " found " +
                        ((Adventurer) subject).getInventory() + ".");
                break;
        }
    }

    @Override
    public void updateCreatureStatus(Creature self, String eventID) {
        switch (eventID) {
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
