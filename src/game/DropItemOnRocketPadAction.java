package game;

import engine.*;

/**
 * Special Action that allows Actor to drop an item specifically into the rocket pad.
 */
public class DropItemOnRocketPadAction extends DropItemAction
{
    private Item item;
    private static int numOfItemsDropped = 0; // To keep in check the number of items that has been dropped.

    /**
     * Constructor
     * @param item the item to drop
     */
    DropItemOnRocketPadAction(Item item)
    {
        super(item);
        this.item = item;
    }

    /**
     * Dropping the item onto the Rocket Pad. If 2 items has been dropped, then the rocket can be build and the player
     * can leave the planet.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a String, e.g. "Player drops the Rocket Engine into the Rocket Pad" or if the player has dropped the
     *         the second item, then "Player uses the rocket parts to build a rocket. \nPlayer has used the rocket to
     *         leave the planet."
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {

        int numberOfItemsNeededToBuildRocket = 2;
        actor.removeItemFromInventory(item);
        numOfItemsDropped++;    // Increase the number of times an item has been dropped after its execution.
        if(numOfItemsDropped == numberOfItemsNeededToBuildRocket)
        {
            int xPositionOfRocket = 10;
            int yPositionOfRocket = 8;
            map.addItem(new Rocket(actor), xPositionOfRocket, yPositionOfRocket);
            return "Player builds a rocket upon the rocket pad.";
        }
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player drops the Rocket Engine on the Rocket Pad."
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " drops the " + item + " on the Rocket Pad.";
    }
}
