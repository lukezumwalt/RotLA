package Characters;

import Board.Room;

public interface Entity {
    // PUBLIC METHODS
    boolean fight(Entity target);

    void move();

    Room checkRoom();

    boolean getAlive();

    String getEntityType();

    String getName();

    String getSign();
}
