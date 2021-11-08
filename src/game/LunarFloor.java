package game;

import engine.*;

/**
 * The floor on the of the base on the moon. Cannot be moved on unless actor has the appropriate skilss
 */
public class LunarFloor extends Ground
{
    /**
     * Constructor for the lunar floor. Has skills.
     */
    public LunarFloor()
    {
        super(',');
        addSkill(SkillList.MOVE_IN_SPACE);
        addSkill(SkillList.BREATHE_IN_SPACE);
    }

    /**
     * Overrided method as the actor can only move on the moon if he has the specific skills needed
     * @param actor the actor who moves on the LunarFloor
     * @return true if the actor has the skills, false if not
     */
    @Override
    public boolean canActorEnter(Actor actor)
    {
        if(actor.hasSkill(SkillList.MOVE_IN_SPACE) && actor.hasSkill(SkillList.BREATHE_IN_SPACE)){
            return true;
        }
        return false;
    }

}
