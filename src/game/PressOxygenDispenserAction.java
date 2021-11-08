package game;

import engine.*;

/**
 * A special action that allows the actor to use the oxygen dispenser by pressing its button
 */
class PressOxygenDispenserAction extends Action
{
    private static boolean pressed = false;

    /**
     * the Constructor
     */
    PressOxygenDispenserAction()
    {
    }

    /**
     * Causes the oxygen dispenser to prepare an oxygen tank if. Can only be executed if the actor has the appropriate
     * skills.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player presses the button on the oxygen dispenser.
     *                         Oxygen dispenser is preparing a full oxygen tank. Please hold on.
     *                         Full oxygen tanks has 10 points. A point is used for every turn on the moon."
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        if(actor.hasSkill(SkillList.USE_OXYGEN_DISPENSER)){
            pressed = true;
            return menuDescription(actor) + "\nOxygen dispenser is preparing a full oxygen tank. Please hold on.\nFull oxygen tanks has 10 points. A point is used for every turn on the moon.";
        }
        return actor+" does nothing";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player presses the button on the oxygen dispenser. "
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " presses the button on the oxygen dispenser. ";
    }

    /**
     * Returns an empty string as pressing the oxygen tank dispenser does not have a dedicated hot key.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }

    /**
     * An accessor for the private attribute pressed
     * @return the private attribute pressed which is true or false
     */
    static boolean isPressed()
    {
        return pressed;
    }

    /**
     * Mutator for the private attribute pressed
     * @param pressed true if to press the button, false otherwise
     */
    static void setPressed(boolean pressed)
    {
        PressOxygenDispenserAction.pressed = pressed;
    }
}
