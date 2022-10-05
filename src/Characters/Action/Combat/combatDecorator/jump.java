package Characters.Action.Combat.combatDecorator;

import java.util.Random;

import Characters.Entity;
import Characters.Action.Combat.combatStyle;
import Characters.Friendlies.Adventurer;

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

    public static void setCelebrate(Entity self) {
        Random celebrateRandom = new Random();
        int numActions = celebrateRandom.nextInt(2);
        System.out.print(self.getName() + " celebrates: ");
        for (int i = 0; i < numActions; i++) {
            System.out.print(" jump");
        }

    }

}
