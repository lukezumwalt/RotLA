package Characters.Action.Combat;

import Board.Observer;
import Characters.Entity;
import Characters.Action.Combat.combatDecorator.celebrateDecorator;
import Characters.Action.Combat.combatDecorator.dance;
import Characters.Action.Combat.combatDecorator.jump;
import Characters.Action.Combat.combatDecorator.shout;
import Characters.Action.Combat.combatDecorator.spin;
import Characters.Friendlies.Adventurer;

import static Utilities.Dice.rollD6;

import java.util.Random;

public class expert implements combatStyle {

    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    @Override
    public int fight(Entity subject, Entity target) {
        Adventurer self = (Adventurer) subject;
        if (self.getHealth() <= 0) {
            // do nothing
            return 0;
        } else {
            int myRoll = rollD6(2) + 2 + self.getOffenseBonus();
            int targetRoll = rollD6(2) - self.getDefenseBonus();

            if (myRoll > targetRoll) {
                // Victory
                notifyObservers(self, "wonCombat");
                // 25% chance Adventurer will celebrate upon victory
                Random r = new Random();
                if (0 == r.nextInt(3)) {
                    jump.setCelebrate(subject);
                    shout.setCelebrate();
                    dance.setCelebrate();
                    spin.setCelebrate();
                    notifyObservers(self, "celebration");
                }
                return 1;
            } else if (myRoll == targetRoll) {
                // Tie
                return 0;
            } else {
                // Loss
                notifyObservers(self, "lostCombat");
                return -1;
            }
        }
    }

    @Override
    public void registerObserver(Observer o) {
        Adventurer.observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        Adventurer.observerList.remove(Adventurer.observerList.indexOf(o));
    }

    @Override
    public void notifyObservers(Adventurer subject, String eventID) {
        // TODO Auto-generated method stub
        Adventurer self = (Adventurer) subject;
        for (Observer o : Adventurer.observerList) {
            o.updateAdventurerStatus(self, eventID);
        }

    }
}
