package game;

import engine.*;

/**
 * This class is an action that allows the user to refill the water pistol when it is empty
 */
public class RefillWaterPistolAction extends Action {

    private WaterPistol waterPistol;

    /**
     *  The Constructor
     * @param waterPistol The water pistol that is to be refilled.
     */
    RefillWaterPistolAction(WaterPistol waterPistol)
    {
        this.waterPistol = waterPistol;
    }

    /**
     *  Causes the water pistol to be filled with water. This can only be done when near a water pool.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return sets the water pistol to full and returns a String,
     *          e.g. "Player refilled the water pistol. "
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.waterPistol.setFull(true);
        return actor + " refilled the water pistol. ";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player refills the water pistol."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills water pistol";
    }

    /**
     * Returns an empty string as refilling the water pistol does not have a dedicated hot key.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
