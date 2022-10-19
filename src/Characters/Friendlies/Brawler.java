package Characters.Friendlies;

import Board.Observer;
import Board.Room;
import Characters.Action.Combat.expert;
import Characters.Action.Move.npcMovement;
import Characters.Action.Move.playerMovement;
import Characters.Entity;
import Characters.Subject;
import Characters.Action.Search.careless;
import Treasure.Treasure;

import java.util.ArrayList;

/*
 * code example of Inheritance
 * all of the adventurer subclasses
 * inherit the superclass Adventurer
 */
public class Brawler extends Adventurer implements Entity, Subject {

    // CONSTRUCTORS
    public Brawler() {
        entityType = "adventurer";
        sign = "B";
        name = "Brawler";
        health = 3;
        alive = true;
        combatStyle = new expert();
        searchStyle = new careless();
//        moveStyle = new npcMovement();
        moveStyle = new playerMovement();
        offenseBonus = 2;
        defenseBonus = 0;
        inventory = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    // PUBLIC METHODS
    @Override
    public boolean fight(Entity target) {
        int fightVal = combatStyle.fight(this, target);
        if (fightVal > 0) {
            notifyObservers("wonCombat");
            return true;
        } else if (fightVal < 0) {
            this.takeDamage(1);
            notifyObservers("lostCombat");
        }
        return false;
    }

    public boolean search() {
        Treasure obtained = searchStyle.search(this, this.currentRoom);
        if (obtained != null) {
            if (obtained.getClass().getSimpleName().equals("Trap")) {
                this.takeDamage(1);
            } else {
                obtained.activate(this);
                this.inventory.add(obtained);
                notifyObservers("treasureFound");
            }
            return true;
        }
        return false;
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        notifyObservers("tookDamage");
        if(this.health <= 0){
            notifyObservers("died");
            this.alive = false;
        }
    }

    @Override
    public void move() {
        if (this.getAlive()) {
            moveStyle.move(this);
        }
    }

    public void setPlayerName(String name){
        playerName = name;
    }
    public String getPlayerName(){
        return playerName;
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

    public int getOffenseBonus() {
        return offenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public int getTreasureCount() {
        return inventory.size();
    }

    public ArrayList<Treasure> getInventory() {
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

    public void updateOffenseBonus(int amount) {
        this.offenseBonus += amount;
    }

    public void updateDefenseBonus(int amount) {
        this.defenseBonus += amount;
    }

    public void heal(int amount) {
        this.health += amount;
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(String eventID) {
        for (Observer o : observerList) {
            o.updateAdventurerStatus(this, eventID);
        }
    }
}
