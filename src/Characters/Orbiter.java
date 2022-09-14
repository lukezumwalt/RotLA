package Characters;

import Characters.Entity;

public class Orbiter extends Creature implements Entity {

    @Override
    public void move() {

    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }
}
