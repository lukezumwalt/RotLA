package Characters.Action.Combat.combatDecorator;

import java.util.Random;

import Characters.Entity;
import Characters.Action.Combat.combatStyle;
import Characters.Friendlies.Adventurer;

// Concrete class extending abstract class
public class shout extends celebrateDecorator {

    // CONSTRUCTORS
    public shout(combatStyle decoratedCombat) {
        super(decoratedCombat);
    }

    // PUBLIC METHODS
    @Override
    public int fight(Entity self, Entity target) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void setCelebrate() {
        Random celebrateRandom = new Random();
        int numActions = celebrateRandom.nextInt(2);
        for (int i = 0; i < numActions; i++) {
            System.out.print("shout");
        }
    }
}
