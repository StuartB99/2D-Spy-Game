package game;

import engine.*;

/**
 * The ninja behaviour is the behaviour which involves throwing stun powder at the player and moving a step away from him.
 * This only occurs if the player is 5 spaces or nearer to the Ninja
 */
public class NinjaBehaviour implements ActionFactory {

    private AlternatePlayer target;

    /**
     * the Constructor
     * @param target the target of the subject
     */
    public NinjaBehaviour(AlternatePlayer target) {
        this.target = target;
    }

    /**
     * Returns the ThrowStunPowderAction() and moves a step away if the player is five spaces or nearer. If not the
     * this behaviour causes the subject to do nothing
     *
     * @param subject the actor who has the NinjaBehaviour
     * @param map the map the actor is on
     * @return an Action depending on the player's position, e.g. ThrowStunPowderAction()
     */
    @Override
    public Action getAction(Actor subject, GameMap map) {
        GameMap playersMap = WorldAccessible.worlds.get(0).getActorLocations().locationOf(target).map();
        if(map.equals(playersMap)){
            Location here = map.locationOf(subject); // location of ninja
            Location there = map.locationOf(target); // location of player
            int ninjaRange = 5;

            // checks if obstacle between player and ninja horizontally
            if ((Math.abs(here.x() - there.x()) <= ninjaRange) & here.y() == there.y()) {
                int start = here.x(); // x location of ninja
                int stop = there.x(); // x location of player
                int same = here.y();  // same as there.y() - stays constant in for loop
                // The following min and max were determined to be able to detect to the ninja's East and West
                int min = Math.min(start, stop);
                int max = Math.max(start, stop);
                //loops between x indices between ninja and player
                for (int i = min; i <= max; i++)
                {
                    if (map.at(i, same).getGround().blocksThrownObjects())
                    {
                        return null;
                    }
                }
            }
            // checks if obstacle between player and ninja vertically
            if ((Math.abs(here.y() - there.y()) <= ninjaRange) & here.x() == there.x())
            {
                int start = here.y(); // y location of ninja
                int stop = there.y(); // y location of player
                int same = here.x(); // same as there.x() - stays constant in for loop
                // The following min and max were determined to be able to detect to the ninja's North and South
                int min = Math.min(start, stop);
                int max = Math.max(start, stop);
                //loops between y indices between ninja and player
                for (int i = min; i <= max; i++)
                {
                    if (map.at(same, i).getGround().blocksThrownObjects())
                    {
                        return null;
                    }
                }
            }

            //if no obstacles are in the path, we determine the ninja's next movement
            if (((Math.abs(here.x() - there.x()) <= ninjaRange) & here.y() == there.y()) | ((Math.abs(here.y() - there.y()) <= ninjaRange) & here.x() == there.x()))
            {

                //check for block thrown object whether random is executing getAction
                if (here.x() < there.x())
                {
                    //if ninja is to the west of player
                    int currentDistance = there.x() - here.x();

                    for (Exit exit : here.getExits())
                    {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(subject))
                        {
                            int newDistance = there.x() - destination.x();
                            if (newDistance > currentDistance)
                            {
                                map.moveActor(subject, destination);
                            }
                        }
                    }
                } else if (here.x() > there.x())
                {
                    //if ninja is to the east of player, move another step to east
                    int currentDistance = here.x() - there.x();
                    for (Exit exit : here.getExits())
                    {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(subject))
                        {
                            int newDistance = destination.x() - there.x();
                            if (newDistance > currentDistance)
                            {
                                map.moveActor(subject, destination);
                            }
                        }
                    }
                } else if (here.y() < there.y())
                {
                    //if ninja is to the north of player
                    int currentDistance = there.y() - here.y();
                    for (Exit exit : here.getExits())
                    {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(subject))
                        {
                            int newDistance = there.y() - destination.y();
                            if (newDistance > currentDistance)
                            {
                                map.moveActor(subject, destination);
                            }
                        }
                    }
                } else if (here.y() > there.y())
                {
                    //if ninja is to the south of player
                    int currentDistance = here.y() - there.y();
                    for (Exit exit : here.getExits())
                    {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(subject))
                        {
                            int newDistance = destination.y() - there.y();
                            if (newDistance > currentDistance)
                            {
                                map.moveActor(subject, destination);
                            }
                        }
                    }
                }
                if (!target.isStunned()) // if player is not stunned, return the ThrowStunPowderAction
                {
                    return new ThrowStunPowderAction(target, new StunPowder());
                }
            }
        }

        return null;
    }
}
