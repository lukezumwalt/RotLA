package Board;

import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Characters.Subject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Logger extends Observer {
    // instantiated at beginning of each full Adv and Creature turn
    // close at end of each turn
    // subscribe for published events occurring during a turn
    // write each event to a txt file
    // Logger-n.txt, n = turn number

    private Room loggedRoom;
    private int loggedHealth;
    private String logFileEntry;

    public Logger() {
        logFileEntry = "";
    }

    public void activate(Subject s){
        //System.out.println("Entered Logger.");
        this.subject = s;
        this.subject.registerObserver(this);
    }

    public void deactivate(Subject s) {
        //System.out.println("Exiting Logger.");
        this.subject.unregisterObserver(this);
        this.subject = null;
    }

    public void closeFrame(int turn) {
        // https://www.w3schools.com/java/java_files_create.asp
        // print to file
        String fileName = "Logger-" + turn + ".txt";
        try{
            FileWriter writeTo = new FileWriter(fileName);
            writeTo.write(this.logFileEntry);
        }catch(IOException e){
            System.out.println("File error!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdventurerStatus(Subject subject, String eventID) {
//        System.out.println("Entered Update.");
//        System.out.println(eventID);
        String temp = "";
        switch (eventID) {
            case "roomEntered":
                temp = subject.getClass().getSimpleName() + " entered room "
                        + Arrays.toString(((Adventurer) subject).checkRoom().getCoordinates());
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
                break;

            case "wonCombat":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " won a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
                break;

            case "lostCombat":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " lost a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
                break;

            case "celebration":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " celebrated.";
                this.logFileEntry += (temp + "\n");
                System.out.println();
                break;

            case "tookDamage":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " took damage.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
                break;

            case "died":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " died.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
                System.out.println(((Adventurer) subject).getAlive());
                break;

            case "treasureFound":
                temp = ((Adventurer) subject).getClass().getSimpleName() + " found " +
                        Arrays.toString(((Adventurer) subject).getInventory().toArray()) + ".";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
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
