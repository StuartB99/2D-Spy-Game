package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Rocket Pad which is a place on the map where the rocket can be build by dropping the rocket
 * engine and rocket building upon it.
 */
public class RocketPad extends Ground
{
    private static List<Item> RocketParts = new ArrayList<Item>();
    /**
     * Constructor
     * Has a fixed representing character on the game map, 'O'.
     */
    public RocketPad()
    {
        super('O');
    }

    /**
     * Returns actions where the player can drop either the rocket engine or the rocket body into it.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a List of actions that involves items being drop into it
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction)
    {
        Actions droppingRocketPartsActions = new Actions();

        for (Item item : actor.getInventory())  // loop throughout the actor's inventory
        {
            if (RocketParts.contains(item))   // if either rocket engine or rocket body is found
            {
                droppingRocketPartsActions.add(new DropItemOnRocketPadAction(item));
            }
        }
        return droppingRocketPartsActions;
    }

    /**
     * Adds item such as RocketEngine or RocketBody objects to RocketParts
     * @param item item instantiated
     */
    static void addRocketParts(Item item)
    {
        RocketParts.add(item);
    }

    /**
     * Overrides the one from ground as only those who can fly the rocket should be allowed to be on the rocket pad to
     * interact with the rocket. This also prevents other actors from colliding with the player when he flies back from
     * the other map which would cause an error
     * @param actor who approaches the rocketpad
     * @return true if actor can fly the rocket, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor)
    {
        if(actor.hasSkill(SkillList.FLY_ROCKET)){
            return true;
        }
        return false;
    }
}
