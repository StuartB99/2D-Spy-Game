package game;

import engine.*;

/**
 * An item that allows the actor to fly to the moon and back, if the actor has the appropriate skills
 */
public class Rocket extends Item
{
    private Actor actor;

    /**
     * Constructor for rocket
     * @param actor the actor who wants to use the rocket
     */
    Rocket(Actor actor)
    {
        super("Rocket", 'R');
        this.actor = actor;
    }

    /**
     * Clears the Actions and only returns FlyRocketAction if actor has the appropriate skills
     * @return Actions with FlyRocketAction if actor has skill, empty Actions otherwise
     */
    @Override
    public Actions getAllowableActions()
    {
        this.allowableActions.clear();
        if(actor.hasSkill(SkillList.FLY_ROCKET)){
            allowableActions.add(new FlyRocketAction());
        }
        return allowableActions;
    }
}
