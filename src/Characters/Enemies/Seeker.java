package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

public class Seeker extends Creature implements Entity {

    public Seeker(){
        sign = "S";
        entityType = "creature";
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
