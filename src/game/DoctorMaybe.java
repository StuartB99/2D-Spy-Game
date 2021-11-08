package game;

import engine.*;

import java.util.Random;

/**
 * Class representing Doctor Maybe, a mini boss within the game. Needs to be beaten in order to obtain the Rocket Engine.
 */
public class DoctorMaybe extends Actor
{
    private Random rand = new Random();

    /**
     * Constructor. The name is fixed as Doctor Maybe and is represented by the character 'd' within the game. Has only
     * half the hit points of the goons, who has 50 hit points, as he is a researcher rather than a combat speacialist
     */
    DoctorMaybe()
    {
        super("Doctor Maybe", 'd', 4, 25);
        inventory.add(RocketEngine.NewInventoryRocketEngine());
    }

    /**
     * Doctor Maybe does not do anything aside from attacking the player when near. So the playTurn method has to be
     * overriden in order to remove the other actions such as moving or dropping the rocket engine which should only be
     * dropped when Doctor Maybe is knocked out.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display)
    {
        for(Action action : actions)
        {
            if(!(action instanceof AlternateAttackAction) && !(action instanceof SkipTurnAction))
            {
                // if not instance of either Attack or Skip turn action then remove the action from actions
                actions.remove(action);
            }
        }
        return actions.get(rand.nextInt(actions.size()));
    }

    /**
     * Creates and returns an intrinsic weapon which are his fist.
     * Does half the damage of Grunt, who's damage is 5. So does 5/2 damage.
     * Damage is an integer so is rounded down to 2 instead of a the more accurate Double 2.5.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon()
    {
        return new IntrinsicWeapon(5/2, "punches");
    }
}
