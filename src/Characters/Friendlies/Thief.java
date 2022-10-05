package Characters.Friendlies;

import Board.Room;
import Characters.Action.Combat.trained;
import Characters.Entity;
import Characters.Action.Search.careful;
import Treasure.Treasure;

import java.util.ArrayList;
import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Thief extends Adventurer implements Entity {

    // CONSTRUCTORS
    public Thief() {
        sign = "T";
        name = "Thief";
        health = 3;
        alive = true;
        combatStyle = new trained();
        searchStyle = new careful();
        offenseBonus = 1;
        defenseBonus = 0;
        inventory = new ArrayList<>();
    }

    // PROTECTED ATTRIBUTES
    protected int health;
    protected static final int treasureRollMod = 1;
    protected final String entityType = "adventurer";

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {
        int fightVal = combatStyle.fight(this, target);
        if( fightVal > 0 ){
            return true;
        }
        else if( fightVal < 0 ){
            this.takeDamage(1);
        }
        return false;
    }

    public boolean search(){
        Treasure obtained = searchStyle.search(this,this.currentRoom);
        if(obtained!=null){
            if( obtained.getClass().getSimpleName().equals("Trap") ){
                this.takeDamage(1);
            }
            else{
                this.inventory.add(obtained);
            }
            return true;
        }
        return false;
    }

    public void takeDamage(int amount) {
        this.health -= amount;
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

    public void setCombatStyle(Characters.Action.Combat.combatStyle cs) {
        combatStyle = cs;
    }

    public void collectTreasure(Room r) {
        inventory.add(r.takeTreasure());
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public void updateOffenseBonus(int scalar){
        this.offenseBonus += scalar;
    }

    public void updateDefenseBonus(int scalar){
        this.defenseBonus += scalar;
    }

    public void heal(int amount){
        this.health += amount;
    }
}
