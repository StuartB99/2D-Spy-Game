package game;

import engine.*;

/**
 *  Key, an obtainable item, which is required for opening locked doors.
 */
class Key extends Item
{
    /**
     *  Constructor
     */
    Key()
    {
        super("Door Key", 'k');
        Door.addKey(this);
    }

    /**
     * Constructor for the key that goes straight into the inventory
     * @return The key
     */
    static Key NewInventoryKey()
    {
        Key key = new Key();
        Door.addKey(key);
        key.allowableActions.clear();
        key.allowableActions.add(new DropItemAction(key));
        return key;
    }

}
