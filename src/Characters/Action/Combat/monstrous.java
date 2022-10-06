package Characters.Action.Combat;

import Characters.Entity;
import Characters.Friendlies.Adventurer;

import static Utilities.Dice.rollD6;

import Board.Observer;

public class monstrous implements combatStyle {

    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    @Override
    public int fight(Entity self, Entity target) {
        Adventurer opponent = (Adventurer) target;

        int myRoll = rollD6(2) - opponent.getDefenseBonus();
        int targetRoll = rollD6(2) + opponent.getOffenseBonus();

        if (myRoll > targetRoll) {
            // Victory
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
