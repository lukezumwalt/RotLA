package Characters;

public class Thief extends Adventurer implements Entity{
    @Override
    public void move() {

    }

    @Override
    public boolean fight(Entity target) {
        return false;
    }
}
