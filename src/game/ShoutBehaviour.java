package game;

import engine.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A shouting behaviour which provides a 0.1 chance of shouting at a target if next to target.
 */
public class ShoutBehaviour extends Action implements ActionFactory {

    private Actor intent;
    private Random rand = new Random();
    private ArrayList<String> shouts = new ArrayList<>();
    private String Shout;
    private float insultChance;

    /**
     * the Constructor
     * @param subject the actor to be shouted at
     */
    public ShoutBehaviour(Actor subject) {
        this.intent = subject;
        shouts.add("Fool");
        shouts.add("Noob");
        shouts.add("You're useless");
        shouts.add("You're reckless");
        shouts.add("Baboon");
    }

    /**
     * Picking a random insult and returning a String with the insult
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "John shouts Baboon at Player !"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Shout = shouts.get(rand.nextInt(shouts.size()));
        return menuDescription(actor);
    }

    /**
     * Getting this action to be part of the actor's behaviour if the random integer is less or equal to 0.1
     * @param actor the actor who shouts
     * @param map the map the actor is on
     * @return this action if chance less or equal to 0.1, else return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        insultChance = rand.nextFloat();
        double probabilityOfInsulting = 0.1;
        if (insultChance <= probabilityOfInsulting)
        {
            return this;
        }
        return null;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "John shouts Fool at Player !"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shouts " + Shout + " at " + intent + " !";
    }

    /**
     * Returns an empty string as exchanging items does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }

}
