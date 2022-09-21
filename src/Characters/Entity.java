package Characters;

import Board.Room;

public interface Entity {
    // PUBLIC METHODS
    public boolean fight(Entity target);

    public void move();

    public Room checkRoom();

    public boolean rollForTreasure();

    public String getEntityType();

    public String getName();
}
