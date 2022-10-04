package Characters.Friendlies;

import Board.Room;
import Characters.Combat.stealth;
import Characters.Entity;
import Characters.Search.quick;
import Treasure.Treasure;

import java.util.ArrayList;
import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;
import static Utilities.Dice.rollD6;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Sneaker extends Adventurer implements Entity {

    // CONSTRUCTORS
    public Sneaker() {
        sign = "S";
        name = "Sneaker";
        health = 3;
        alive = true;
        combatStyle = new stealth();
        searchStyle = new quick();
        offenseBonus = 0;
        defenseBonus = 0;
        inventory = new ArrayList<>();
    }

    protected int health;
    protected final String entityType = "adventurer";

    // PUBLIC METHODS
    public boolean fight(Entity target) {
        int fightVal = combatStyle.fight(this, target);
        if( fightVal > 0 ){
            return true;
        }
        else if( fightVal < 0 ){
            this.takeDamage();
        }
        return false;
    }

    public boolean search(){
        Treasure obtained = searchStyle.search(this,this.currentRoom);
        if(obtained!=null){
            if( obtained.getClass().getSimpleName().equals("Trap") ){
                this.takeDamage();
            }
            else{
                this.inventory.add(obtained);
            }
            return true;
        }
        return false;
    }

    public void takeDamage() {
        health--;
    }

    @Override
    public void move() {
        if(this.getAlive()) {
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

    public String getSign() {
        return sign;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getName() {
        return name;
    }

    public int getOffenseBonus(){
        return offenseBonus;
    }
    public int getDefenseBonus(){ return defenseBonus; }

    @Override
    public int getTreasureCount() {
        return 0;
    }

    public ArrayList<Treasure> getInventory(){
        return inventory;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setCombatStyle(Characters.Combat.combatStyle cs) {
        combatStyle = cs;
    }

    public void collectTreasure(Room r) {
        inventory.add(r.takeTreasure());
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }
}
