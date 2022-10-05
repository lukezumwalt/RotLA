package Board;

public class Logger implements Observer{
    // instantiated at beginning of each full Adv and Creature turn
    // close at end of each turn
    // subscribe for published events occurring during a turn
    // write each event to a txt file
    //      Logger-n.txt, n = turn number

    private Room loggedRoom;
    private int loggedHealth;
}
