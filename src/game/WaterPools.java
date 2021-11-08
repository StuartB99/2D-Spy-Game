package game;

import engine.*;

/**
 * The water pools on Earth. Cannot be moved over. Player can refill the pistol once next to these pools.
 */
public class WaterPools extends Ground
{
    /**
     * The Constructor
     */
    public WaterPools()
    {
        super('~');
    }

    /**
     * Ensures that actors cannot pass through these pools of water
     * @param actor any actor in the game
     * @return false on all cases as no actor should be able to pass it
     */
    @Override
    public boolean canActorEnter(Actor actor) { return false; }

    /**
     * This allows the actors nearby to fill the water pistol, if it is empty and found in the inventory.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an updated set of actions that the user can perform once next to the pools of water.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        for (WaterPistol waterPistol : WaterPistol.waterPistols){
            if(actor.getInventory().contains(waterPistol)){
                if(!waterPistol.isFull()){
                    return new Actions(new RefillWaterPistolAction(waterPistol));
                }
            }
        }

        return super.allowableActions(actor, location, direction);
    }
}
