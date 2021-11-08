package game;

import engine.*;

/**
 * Action which is always executed by player whenever the player runs out of oxygen on the moon
 */
class SafetySystemNoOxygenAction extends Action
{
    /**
     * Constructor. Requires no parameter or have anything done in the constructor as everything is done in
     * the execute() method.
     */
    SafetySystemNoOxygenAction()
    {
    }

    /**
     * Moving the player back to the rocket pad in the lair on Earth
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player is transported back to Earth due to a lack of oxygen."
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        int earthRocketPadXCoordinate = 10;
        int earthRocketPadYCoordinate = 8;
        map.removeActor(actor);
        GameMap earthLairMap = WorldAccessible.worlds.get(0).getMaps().get(0);
        WorldAccessible.worlds.get(0).addPlayer(actor, earthLairMap, earthRocketPadYCoordinate, earthRocketPadXCoordinate);

        return actor + " is transported back to Earth due to a lack of oxygen.";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player returns to Earth due to lack of oxygen."
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor+" returns to Earth due to lack of oxygen.";
    }

    /**
     * Returns an empty string as it does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }
}
