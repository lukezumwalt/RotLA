package Characters;

public class Brawler extends Adventurer implements Entity{

    static final int combatBonus = 2;


    @Override
    public boolean fight(Entity target){
        int myRoll = 0;//roll6d(2) + combatBonus;
        int targetRoll = 0;//roll6d(2);

        if( myRoll > targetRoll ) {
            // Victory
            return true;
//            target.die();
        }
        else if ( myRoll == targetRoll ) {
            // Tie
            return false;
        }
        else {
            // Loss
            takeDamage();
        }
        return false;
    }

    @Override
    public void move() {

    }
}
