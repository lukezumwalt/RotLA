package Characters.Action.Celebrate;

import java.util.Random;

import Characters.Entity;
import Characters.Action.Combat.combatStyle;

// Concrete class extending abstract class
public class jump extends celebrateDecorator {

    // CONSTRUCTORS
    public jump(combatStyle decoratedCombat) {
        super(decoratedCombat);
        // TODO Auto-generated constructor stub
    }

    // PUBLIC METHODS
    @Override
    public int fight(Entity self, Entity target) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void setCelebrate(Entity subject) {
        Random celebrateRandom = new Random();
        int numActions = celebrateRandom.nextInt(2);
        System.out.print(subject.getName() + " celebrates: ");
        for (int i = 0; i < numActions; i++) {
            System.out.print(" jump");
        }
    }
}
