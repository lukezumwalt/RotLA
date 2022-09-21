package Characters.Enemies;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;

public class Orbiter extends Creature implements Entity {

    public Orbiter(){
        sign = "O";
        name = "orbiter";
    }

    @Override
    public void move() {
        checkRoom().occupyCreature( this );
    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }

    @Override
    public String getEntityType() { return this.entityType; }

    @Override
    public Room checkRoom(){ return this.currentRoom; }

    @Override
    public boolean rollForTreasure() {
        return false;
    }

    public String getName(){ return name; }
}
