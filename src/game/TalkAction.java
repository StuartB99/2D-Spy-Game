package game;

import engine.*;

/**
 * Special Action that allows Actor to talk.
 */
public class TalkAction extends Action
{
    private Actor subject;
    private String words;

    /**
     * Constructor
     *
     * @param subject The subject which will say something to the actor
     * @param words a String that the subject will say to the actor
     */
    public TalkAction(Actor subject, String words)
    {
        this.subject = subject;
        this.words = words;
    }

    /**
     * Talking
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "John says 'Hi!'"
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        return subject + " says '" + words+"'";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player talks with John"
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor+" talks with "+subject;
    }

    /**
     * Returns an empty string as talking does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey()
    {
        return "";
    }
}
