package Board;

import Characters.Enemies.Creature;
import Characters.Friendlies.Adventurer;
import Characters.Subject;
import Treasure.Treasure;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Logger extends Observer {
    // instantiated at beginning of each full Adv and Creature turn
    // close at end of each turn
    // subscribe for published events occurring during a turn
    // write each event to a txt file
    // Logger-n.txt, n = turn number
    private String logFileEntry;

    public Logger() {
        logFileEntry = "";
    }

    public void activate(Subject s){
        this.subject = s;
        this.subject.registerObserver(this);
    }

    public void deactivate(Subject s) {
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
            writeTo.flush();
            writeTo.close();
        }catch(IOException e){
            System.out.println("File error!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdventurerStatus(Subject subject, String eventID) {
        String temp = "";
        switch (eventID) {
            case "roomEntered" -> {
                temp = subject.getClass().getSimpleName() + " entered room "
                        + Arrays.toString(((Adventurer) subject).checkRoom().getCoordinates());
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "wonCombat" -> {
                temp = ((Adventurer) subject).getClass().getSimpleName() + " won a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "lostCombat" -> {
                temp = ((Adventurer) subject).getClass().getSimpleName() + " lost a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "celebration" -> {
                temp = ((Adventurer) subject).getClass().getSimpleName() + " celebrated.";
                this.logFileEntry += (temp + "\n");
                System.out.println();
            }
            case "tookDamage" -> {
                temp = ((Adventurer) subject).getClass().getSimpleName() + " took damage.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "died" -> {
                temp = ((Adventurer) subject).getClass().getSimpleName() + " died.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "treasureFound" -> {
                ArrayList<Treasure> inv = ((Adventurer)subject).getInventory();
                temp = ((Adventurer) subject).getClass().getSimpleName() + " found " +
                        (inv.get(inv.size() - 1)).getClass().getSimpleName() + ".";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
        }
    }

    @Override
    public void updateCreatureStatus(Creature self, String eventID) {
        String temp = "";
        switch (eventID) {
            case "roomEntered" -> {
                temp = subject.getClass().getSimpleName() + " entered room "
                        + Arrays.toString(((Creature) subject).checkRoom().getCoordinates());
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "wonCombat" -> {
                temp = ((Creature) subject).getClass().getSimpleName() + " won a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "lostCombat" -> {
                temp = ((Creature) subject).getClass().getSimpleName() + " lost a fight.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
            case "died" -> {
                temp = ((Creature) subject).getClass().getSimpleName() + " died.";
                this.logFileEntry += (temp + "\n");
                System.out.println(temp);
            }
        }
    }
}
