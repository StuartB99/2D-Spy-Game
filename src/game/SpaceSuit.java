package game;

import engine.*;

/**
 * A special item that provides the actor with the ability to move in space
 */
class SpaceSuit extends Item
{
    /**
     * Constructor
     */
    SpaceSuit()
    {
        super("Space Suit", 's');
        this.addSkill(SkillList.MOVE_IN_SPACE);
    }

}
