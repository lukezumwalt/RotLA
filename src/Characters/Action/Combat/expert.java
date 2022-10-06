package Characters.Action.Combat;

import Characters.Entity;
import Characters.Action.Combat.combatDecorator.dance;
import Characters.Action.Combat.combatDecorator.jump;
import Characters.Action.Combat.combatDecorator.shout;
import Characters.Action.Combat.combatDecorator.spin;
import Characters.Friendlies.Adventurer;

import static Utilities.Dice.rollD6;

import java.util.Random;

public class expert implements combatStyle {

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
                // 25% chance Adventurer will celebrate upon victory
                Random r = new Random();
                if (0 == r.nextInt(3)) {
                    jump.setCelebrate(subject);
                    shout.setCelebrate();
                    dance.setCelebrate();
                    spin.setCelebrate();
                    System.out.print("\n");
                    ((Adventurer)subject).notifyObservers("celebration");
                }
                return 1;
            } else if (myRoll == targetRoll) {
                // Tie
                return 0;
            } else {
                // Loss
                return -1;
            }
        }
    }
}
