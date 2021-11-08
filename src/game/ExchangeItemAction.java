package game;

import engine.*;

/**
 * Special Action that allows Actors to exchange items and disappear.
 */
public class ExchangeItemAction extends Action
{
    private Item itemExchangedWith;
    private Item itemExchangedFor;
    private Actor actor;
    private Actor subject;

    /**
     * Constructor
     * @param itemExchangedWith Item that is to be given
     * @param itemExchangedFor Item that is to be received
     * @param actor The actor performing the action
     * @param subject Subject that will give player something in return for something else
     */
    public ExchangeItemAction(Actor actor, Actor subject,Item itemExchangedWith, Item itemExchangedFor)
    {
        this.actor = actor;
        this.subject = subject;
        this.itemExchangedWith = itemExchangedWith;
        this.itemExchangedFor = itemExchangedFor;
    }

    /**
     * Exchanging the item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player exchanges potato for tomato with John.
     *                         John disappears with a cheery wave. "
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        actor.removeItemFromInventory(itemExchangedWith);      // player gives item to subject
        subject.addItemToInventory(itemExchangedWith);         // subject takes the item from player

        subject.removeItemFromInventory(itemExchangedFor);   // subject gives in return another item to player
        actor.addItemToInventory(itemExchangedFor);          // player keeps the item given

        map.removeActor(subject);                             // subject disappear after exchanging item

        return menuDescription(actor)+"\n"+subject+" disappears with a cheery wave.";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player exchanges potato for tomato with John"
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return (actor + " exchanges " + itemExchangedWith + " for " + itemExchangedFor + " with " + subject);
    }

    /**
     * Returns an empty string as exchanging items does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }
}
