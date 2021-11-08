package game;

import engine.Item;

/**
 *  Rocket Plans, an obtainable item in the game. Required to be exchanged for Rocket Body.
 */
public class RocketPlans extends Item
{
    /**
     * Constructor. Represented with a '$' on the game map.
     */
    public RocketPlans()
    {
        super("Rocket Plans", '$');
        Q.addRocketPlans(this);
    }
}
