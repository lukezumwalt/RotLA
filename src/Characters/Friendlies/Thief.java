package Characters.Friendlies;

import Board.Room;
import Characters.Entity;

public class Thief extends Adventurer implements Entity {

    public Thief(){
        sign = "T";
        entityType = "adventurer";
    }
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
