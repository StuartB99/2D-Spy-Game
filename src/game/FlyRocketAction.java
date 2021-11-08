package game;

import engine.Action;
import engine.Actor;
import engine.GameMap;

/**
 * Special Action that allows the Actor to use the rocket to fly to the moon or to earth
 */
public class FlyRocketAction extends Action
{
    /**
     * Constructor. Requires no parameter or have anything done in the constructor as everything is done in
     * the execute() method.
     */
    FlyRocketAction()
    {
    }

    /**
     * Allows the player to fly the rocket from earth to the moon or vice versa by removing the player from the current
     * map and placing him on the other map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player uses the rocket to fly to the base on the moon."
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        String destination;
        map.removeActor(actor);
        GameMap otherMap;
        if(map == WorldAccessible.worlds.get(0).getMaps().get(0)){
            otherMap = WorldAccessible.worlds.get(0).getMaps().get(1);
            destination = "to the base on the moon.";
            WorldAccessible.worlds.get(0).addPlayer(actor, otherMap, 6, 10);
        }
        else{
            otherMap = WorldAccessible.worlds.get(0).getMaps().get(0);
            destination = "back to the lair on Earth.";
            WorldAccessible.worlds.get(0).addPlayer(actor, otherMap, 8, 10);
        }

        return menuDescription(actor)+" to fly "+destination;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player uses the rocket."
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return "Player uses the rocket";
    }

    /**
     * Returns an empty string as flying the rocket does not have a dedicated hot key.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }
}
