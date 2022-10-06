package Characters.Action.Combat;

import java.util.ArrayList;

import Board.Observer;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

public interface combatStyle {

    // PUBLIC METHODS
    // Returns damage dealt to self
    // If > 0, self won
    // If == 0, self win or tie
    int fight(Entity self, Entity target);
}
