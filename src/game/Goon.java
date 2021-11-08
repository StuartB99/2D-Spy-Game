package game;

import engine.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Goon is an enemy that follows the player around and occasionally shouts at him. Has double the damage of a goon. Has
 * a key that can be used to unlock locked doors.
 */
public class Goon extends Actor {
    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    /**
     * Constructor for Goon
     * @param name name of the goon
     * @param player actor that the goon should follow
     */
    Goon(String name, Actor player) {
        super(name, 'G', 5, 50);
        addBehaviour(new ShoutBehaviour(player));
        addBehaviour(new FollowBehaviour(player));
        inventory.add(Key.NewInventoryKey());
        addSkill(SkillList.MOVE_IN_SPACE);
        addSkill(SkillList.BREATHE_IN_SPACE);
    }

    /**
     * Adding a behaviour to an ActionFactory of other behaviours for the goon
     * @param behaviour the behaviour to be added
     */
    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Goon will either implement Follow behaviour, Shout behaviour or its usual actions. If player is not on map
     * anymore, grunt will carry out its usual actions.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action to be performed
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        try {
            for (ActionFactory factory : actionFactories)
            {
                Action action = factory.getAction(this, map);
                if (action != null)
                {
                    return action;
                }
            }
        }
        // catch NullPointerException, when the player is removed from the game map
        // do nothing, allow the grunt to do its usual actions, as the actor has left the game and cannot be followed
        catch(Exception NullPointerException)
        {}
        for (Action action : actions)
        {
            if ((action instanceof DropItemAction) || (action instanceof PickUpItemAction))
            {
                // remove dropping the key actions from all his possible actions
                actions.remove(action);
            }
        }
        return super.playTurn(actions,  map,  display);
    }

    /**
     * Overriding the default intrinsic weapon of actor and implementing a different type of attack with twice the
     * damage.
     *
     * @return the current intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "Focus Punch");
    }

}
