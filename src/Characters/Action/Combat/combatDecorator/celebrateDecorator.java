package Characters.Action.Combat.combatDecorator;

import Characters.Action.Combat.combatStyle;

// Abstract class
public abstract class celebrateDecorator implements combatStyle {

    // CONSTRUCTORS
    public celebrateDecorator(combatStyle decoratedCombat) {
        this.decoratedCombat = decoratedCombat;
    }

    // PROTECTED ATTRIBUTES
    protected combatStyle decoratedCombat;
}
