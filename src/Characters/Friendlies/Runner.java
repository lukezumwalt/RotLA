package Characters.Friendlies;

import Board.Room;
import Characters.Combat.untrained;
import Characters.Entity;

import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;
import static Utilities.Dice.rollD6;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Runner extends Adventurer implements Entity {

    // CONSTRUCTORS
    public Runner() {
        sign = "R";
        name = "Runner";
        health = 3;
        alive = true;
        combatStyle = new untrained();
    }

    protected int health;
    protected final String entityType = "adventurer";

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {
//        if(health <= 0){
//            // do nothing
//        }
//        else {
//            int myRoll = rollD6(2);
//            int targetRoll = rollD6(2);
//
//            if (myRoll > targetRoll) {
//                // Victory
//                return true;
//                // target.die();
//            } else if (myRoll == targetRoll) {
//                // Tie
//                return false;
//            } else {
//                // Loss
//                this.takeDamage();
//            }
//        }
//        return false;
        int fightVal = combatStyle.fight(this, target);
        if( fightVal > 0 ){
            return true;
        }
        else if( fightVal < 0 ){
            this.takeDamage();
        }
        return false;

    }

    public void takeDamage() {
        health--;
    }

    @Override
    public void move() {
        if(health <= 0){
            // do nothing
            return;
        }
        else {
            // check room to return valid moves
            String[] addresses = inspectNeighbors(this.currentRoom);
            // randomly select a valid move from that list
            int choice;
            if (addresses.length <= 1) {
                choice = 0;
            } else {
                Random r = new Random();
                choice = r.nextInt(0, addresses.length);
            }
            Room newRoom = Facility.get(addresses[choice]);

            // finally:
            this.currentRoom.leaveRoom(this);
            this.setCurrentRoom(newRoom);
            newRoom.occupyAdventurer(this);
        }
    }

    @Override
    public Room checkRoom() {
        return currentRoom;
    }

    @Override
    public boolean rollForTreasure() {
        if (rollD6(2) >= 10) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public int getCombatBonus(){return combatBonus; }
    public int getDefenseBonus(){ return defenseBonus; }

    @Override
    public int getTreasureCount() {
        return 0;
    }
}
