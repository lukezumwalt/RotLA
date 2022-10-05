package Characters.Friendlies;

import Board.Observer;
import Board.Room;
import Characters.Action.Combat.untrained;
import Characters.Entity;
import Characters.Action.Search.quick;
import Characters.Subject;
import Treasure.Treasure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static Board.Room.inspectNeighbors;
import static Game.Engine.Facility;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Runner extends Adventurer implements Entity, Subject {

    // CONSTRUCTORS
    public Runner() {
        entityType = "adventurer";
        sign = "R";
        name = "Runner";
        health = 3;
        alive = true;
        combatStyle = new untrained();
        searchStyle = new quick();
        offenseBonus = 0;
        defenseBonus = 0;
        inventory = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    // PUBLIC METHODS
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

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers(String eventID) {
        for (Observer o : observerList) {
            o.updateAdventurerStatus(this, eventID);
        }
    }
}
