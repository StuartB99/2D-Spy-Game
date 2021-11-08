package game;

import engine.*;

/**
 * A special action that the user calls in order to quit the game
 */
public class QuitGameAction extends Action
{
    /**
     * Constructor, no parameters needed
     */
    QuitGameAction()
    {
    }

    /**
     * Sets the quit flag in the World to let it know to end the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Quitting game..."
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        WorldAccessible.worlds.get(0).setQuit(true);
        return "Quitting game...";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Quit game. "
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return "Quit game.";
    }

    /**
     * As this option is always available on every turn, we give it a specific key. A capital letter was chosen so as to
     * prevent the user from accidentally pressing on it
     * @return a char, 'E'
     */
    @Override
    public String hotKey()
    {
        return "E";
    }
}
