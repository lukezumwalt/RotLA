package Characters;

import Board.Room;

public interface Entity {
    // Methods
    void move();
    boolean fight(Entity target);
    String getEntityType();
    String getName();
    Room checkRoom();

    boolean rollForTreasure();
}
