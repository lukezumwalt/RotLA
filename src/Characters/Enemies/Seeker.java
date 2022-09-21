package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

public class Seeker extends Creature implements Entity {

    public Seeker(){
        sign = "S";
        name = "seeker";
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

    @Override
    public boolean rollForTreasure() {
        return false;
    }

    public String getName(){ return name; }
}
