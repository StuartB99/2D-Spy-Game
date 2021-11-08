package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the character Q. A friendly NPC in the game.
 *
 */
public class Q extends Actor
{
    private static List<RocketPlans> RocketPlansList = new ArrayList<RocketPlans>();
    /**
     * Constructor. Q is a NPC single character in the game who's name is Q and is always represented as 'Q' on the map.
     * Being a friendly NPC towards the player, he has priority second only to player.
     */
    public Q()
    {
        super("Q", 'Q', 2, 100);
    }

    /**
     * Overriden method from Actor as Q does not attack anyone
     * Returns a collection of the Actions containing either a TalkAction or both ExchangeItemAction and TalkAction
     * that the otherActor can do to the Q, depending on whether the otherActor has the rocket plans
     *
     * @param otherActor the Actor that might be performing the actions
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map)
    {
        Actions listOfActions = new Actions();
        if(otherActor.hasSkill(SkillList.TALK_TO_Q)){
            for (Item item : otherActor.getInventory())
            {
                if(RocketPlansList.contains(item))
                {
                    listOfActions.add(new TalkAction(this, "Hand them over, I don't have all day!"));
                    listOfActions.add(new ExchangeItemAction(otherActor, this, item, RocketBody.NewInventoryRocketBody()));
                    return listOfActions;
                }
            }
            listOfActions.add(new TalkAction(this, "I can give you something that will help, but I'm going to need the plans."));
        }

        return listOfActions;
    }

    /**
     * Q has the default actions of random movement but should not be attacking anyone or picking anything up as he only
     * serves to assist the player in building the rocket
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action to be performed
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display)
    {
        for (Action action : actions)
        {
            if ((action instanceof AttackAction)|| (action instanceof PickUpItemAction))
            {
                // remove attacking actions from all his possible actions
                actions.remove(action);
            }
        }
        return super.playTurn(actions, map, display);
    }

    /**
     * Adds RocketPlans objects to Q's RocketPlansList
     * @param rocketPlans the rocket plan to add
     */
    static void addRocketPlans(RocketPlans rocketPlans){
        RocketPlansList.add(rocketPlans);
    }

}
