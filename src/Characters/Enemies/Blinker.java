package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

public class Blinker extends Creature implements Entity {

    public Blinker(){
        sign = "B";
        name = "blinker";
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
