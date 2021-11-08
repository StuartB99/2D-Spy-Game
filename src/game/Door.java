package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Door that cannot be passed unless an actor has unlocked it with a Key
 */
public class Door extends Ground {

    private boolean isUnlocked = false;
    private static List<Key> keys = new ArrayList<Key>();
    /**
     * Constructor for Door. Its display char is set as '|' in the World.
     */
    public Door() {
        super('|');
    }

    /**
     * To be used as a checked on whether the actor can pass through the Door. It is false on default.
     * @param actor the actor who wants to enter through the door
     * @return true if actor can enter and false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return isUnlocked();
    }

    /**
     * Overriding the parent's method which does not blocks thrown object. As it is a door, it should always block
     * something that is thrown at it
     *
     * @return true at all cases
     */
    @Override
    public boolean blocksThrownObjects()
    {
        return true;
    }

    /**
     * Gives the Door an UnlockDoorAction when an actor near it has a key, can open it and is locked
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions that contain UnlockDoorAction if actor has key, otherwise return empty Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction)
    {
        if(actor.hasSkill(SkillList.UNLOCK_DOOR) && !this.isUnlocked()){
            for (Item item : actor.getInventory()){
                if (keys.contains(item)){
                    return new Actions(new UnlockDoorAction(this, item));
                }
            }
        }
        return new Actions();
    }

    /**
     * Accessor for the instance variable isUnlocked
     * @return the current boolean state of the instance variable isUnlocked
     */
    boolean isUnlocked()
    {
        return isUnlocked;
    }

    /**
     * Used to change the state of the instance variable isUnlocked
     * @param unlocked the new boolean state for isUnlocked
     */
    void setUnlocked(boolean unlocked)
    {
        if(unlocked){
            displayChar = '-';
        }
        this.isUnlocked = unlocked;
    }

    static void addKey(Key key)
    {
        keys.add(key);
    }
}
