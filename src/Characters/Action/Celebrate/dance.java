package Characters.Action.Celebrate;

import java.util.Random;

import Characters.Entity;
import Characters.Action.Combat.combatStyle;

// Concrete class extending abstract class
public class dance extends celebrateDecorator {

    // CONSTRUCTORS
    public dance(combatStyle decoratedCombat) {
        super(decoratedCombat);
        // TODO Auto-generated constructor stub
    }

    // PUBLIC METHODS
    @Override
    public int fight(Entity self, Entity target) {
        return 0;
    }

    public static void setCelebrate() {
        Random celebrateRandom = new Random();
        int numActions = celebrateRandom.nextInt(2);
        for (int i = 0; i < numActions; i++) {
            System.out.print(" dance");
        }
    }
}