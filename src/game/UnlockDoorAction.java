package game;

import engine.*;

/**
 * Special Action that allows a locked door to be unlocked
 */
public class UnlockDoorAction extends Action
{
    private Door door;
    private Item key;

    /**
     * The constructor
     * @param door the door that is to be opened
     * @param key the key that will unlock the door
     */
    public UnlockDoorAction(Door door, Item key)
    {
        this.door = door;
        this.key = key;
    }

    /**
     * Unlocking the particular door with a particular key.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player unlocks the door", if the door is locked
     *         a String, e.g. "Door is already unlocked", if the door is not locked
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        if (!door.isUnlocked())
        {
            actor.removeItemFromInventory(key);
            door.setUnlocked(true);
            return menuDescription(actor);
        }
        return "Door is already unlocked.";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player unlocks the door"
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " unlocks the door";
    }

    /**
     * Returns an empty string as unlocking doors does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }
}
