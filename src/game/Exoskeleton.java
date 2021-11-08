package game;

import engine.*;

/**
 *  Exoskeleton, an item which makes the wearing actor invulnerable whilst (s)he is wearing it
 */
class Exoskeleton extends Item
{

    /**
     *  Constructor
     */
    Exoskeleton()
    {
        super("Exoskeleton", 'x');
        this.addSkill(SkillList.INVINCIBILITY);

    }

    /**
     * Constructor for the exoskeleton to go straight into Yugo's inventory
     * @return The exoskeleton
     */
    static Exoskeleton NewInventoryExoskeleton()
    {
        Exoskeleton exoskeleton = new Exoskeleton();
        exoskeleton.allowableActions.clear();
        exoskeleton.allowableActions.add(new DropItemAction(exoskeleton));
        return exoskeleton;
    }

}
