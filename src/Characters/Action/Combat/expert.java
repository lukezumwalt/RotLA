package Characters.Action.Combat;

import Characters.Entity;
import Characters.Action.Combat.combatDecorator.celebrateDecorator;
import Characters.Action.Combat.combatDecorator.dance;
import Characters.Action.Combat.combatDecorator.jump;
import Characters.Action.Combat.combatDecorator.shout;
import Characters.Action.Combat.combatDecorator.spin;
import Characters.Friendlies.Adventurer;

import static Utilities.Dice.rollD6;

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
                jump.setCelebrate(subject);
                shout.setCelebrate();
                dance.setCelebrate();
                spin.setCelebrate();
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
