package Characters.Friendlies;

import Board.Room;
import Characters.Entity;

public class Runner extends Adventurer implements Entity {
    @Override
    public void move() {

    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }

    @Override
    public String getEntityType() {
        return null;
    }

    @Override
    public Room checkRoom() {
        return null;
    }
}
