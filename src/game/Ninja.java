package game;

import engine.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A enemy in the game that has NinjaBehaviour.
 */
public class Ninja extends Actor {

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    /**
     * the Constructor. He has low hit points as the player is not expected to attack him due to his NinjaBehaviour
     * which should keep him out of reach for most of the game. Drops a key when knocked out.
     * @param name name of the ninja
     * @param player the player whom the ninja uses his ninja behaviour against
     */
    Ninja(String name, AlternatePlayer player) {
        super(name, 'n',4,25);
        addBehaviour(new NinjaBehaviour(player));
        inventory.add(Key.NewInventoryKey());
        addSkill(SkillList.MOVE_IN_SPACE);
        addSkill(SkillList.BREATHE_IN_SPACE);
    }

    /**
     * Adding a behaviour to an ActionFactory of other behaviours for the ninja
     * @param behaviour the behaviour to be added
     */
    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Ninja will implement NinjaBehaviour or its usual actions which is nothing. If player is not on map
     * anymore, ninja will carry out its usual actions.
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action to be performed
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        // try to execute the actions from the actionFactories
        try{
            for (ActionFactory factory : actionFactories) {
                Action action = factory.getAction(this, map);
                if(action != null)
                    return action;
            }
        }
        // catch NullPointerException, when the player is removed from the game map
        // do nothing, allow the ninja to do its usual actions, as the actor has left the game and cannot be followed
        catch(Exception NullPointerException)
        {}
        return new SkipTurnAction();
    }
}
