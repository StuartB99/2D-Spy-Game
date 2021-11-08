package game;

import engine.*;

/**
 *  Rocket Body, an obtainable item, which is required for building the rocket.
 */
public class RocketBody extends Item
{
    /**
     *  Constructor
     */
    public RocketBody()
    {
        super("Rocket Body", 'H');
        RocketPad.addRocketParts(this);
    }

    /**
     * Constructor for a rocket body that goes straight into the inventory
     * @return The rocket body
     */
    protected static RocketBody NewInventoryRocketBody()
    {
        RocketBody rocketBody = new RocketBody();
        RocketPad.addRocketParts(rocketBody);
        rocketBody.allowableActions.clear();
        rocketBody.allowableActions.add(new DropItemAction(rocketBody));
        return rocketBody;
    }
}
