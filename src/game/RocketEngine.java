package game;

import engine.*;

/**
 * Rocket Engine, an obtainable object, which is required for building the rocket
 */
public class RocketEngine extends Item
{
    /**
     * Constructor
     */
    public RocketEngine()
    {
        super("Rocket Engine", '^');
        RocketPad.addRocketParts(this);
    }

    /**
     * Constructor for a rocket engine that goes straight into the inventory.
     * @return The rocket engine
     */
    protected static RocketEngine NewInventoryRocketEngine()
    {
        RocketEngine rocketEngine = new RocketEngine();
        RocketPad.addRocketParts(rocketEngine);
        rocketEngine.allowableActions.clear();
        rocketEngine.allowableActions.add(new DropItemAction(rocketEngine));
        return rocketEngine;
    }
}
