package Characters.Friendlies;

import Characters.Entity;

public class Runner extends Adventurer implements Entity {
    @Override
    public void move() {

    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }
}
