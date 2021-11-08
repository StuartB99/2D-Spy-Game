package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Grunt is an enemy that follows the player around and attacks the player. Has a key that can be used to unlock locked
 * doors.
 */
public class Grunt extends Actor {

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	/**Constructor for grunt.
	 * @param name the name of the grunt
	 * @param player actor whom the grunt would follow
	 */
	public Grunt(String name, Actor player) {
		super(name, 'g', 5, 50);
		addBehaviour(new FollowBehaviour(player));
		inventory.add(Key.NewInventoryKey());
		addSkill(SkillList.MOVE_IN_SPACE);
		addSkill(SkillList.BREATHE_IN_SPACE);
	}

	/**
	 * Adding a behaviour to an ActionFactory of other behaviours for the grunt
	 * @param behaviour the behaviour to be added
	 */
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	/**
	 *  Grunt will either carry out Follow behaviour or its usual actions. If player is not on map anymore, grunt will
	 *  carry out its usual actions.
	 *
	 * @param actions collection of possible Actions for this Actor
	 * @param map     the map containing the Actor
	 * @param display the I/O object to which messages may be written
	 * @throws  NullPointerException, when the player has left the planet and can't be found on the planet/game and
	 * 		   therefore cannot be followed anymore.
	 * @return the Action to be performed
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
		// do nothing, allowing the grunt to do its usual actions, as the actor has left the game and cannot be followed
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
}
