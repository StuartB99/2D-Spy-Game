package game;

import engine.*;

/**
 * Class representing Yugo Maxx, the final boss in the game. Needs to be defeated in order to win the game.
 */
public class YugoMaxx extends Actor
{
    private Exoskeleton exoskeleton;
    /**
     * Constructor. The name is fixed as Yugo Maxx and is represented by the character 'Y' within the game. Only
     * has 25 hit points but do not forget that while Yugo Maxx is wearing his exoskeleton, he will take NO damage!
     * The exoskeleton must be destroyed in order to defeat Yugo.   By default he is allowed to move and breathe freely
     * in space.
     */
    YugoMaxx()
    {
        super("Yugo Maxx",'Y',3,25);
        exoskeleton = Exoskeleton.NewInventoryExoskeleton();
        inventory.add(exoskeleton);
        this.addSkill(SkillList.BREATHE_IN_SPACE);
        this.addSkill(SkillList.MOVE_IN_SPACE);
    }

    /**
     * Yugo Maxx will wander the map randomly except when the player is next to him as he'd attack him. This method was
     * overridden to prevent him from dropping his inventory items before being knocked out. He also cannot pick up
     * other items.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return an updated set of Actions for th actor
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        for (Action action : actions)
        {
            if ((action instanceof DropItemAction) || (action instanceof PickUpItemAction))
            {
                actions.remove(action);
            }
        }
        return super.playTurn(actions, map, display);
    }

    /**
     * The accessor for the private attribute 'Exoskeleton'
     * @return exoskeleton
     */
    Exoskeleton getExoskeleton() {
        return exoskeleton;
    }

    /**
     * This method was overridden as we had to account for the extended AttackAction.
     * It accounts for if the actor is wearing an exoskeleton and handles normal attack and water pistol shots.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An updated set of actions that vary if the actor is wearing an exoskeleton
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new AlternateAttackAction(otherActor, this));

        if(hasSkill(SkillList.INVINCIBILITY)){
            for(WaterPistol waterPistol : WaterPistol.waterPistols){
                if(otherActor.getInventory().contains(waterPistol)){
                    actions.add(new ShootWaterPistolAction(waterPistol, this));
                }
            }
        }

        return actions;
    }

    /**
     * Overriding the default intrinsic weapon of actor and implementing a different type of attack with twice the
     * damage.
     *
     * @return the current intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "Yugo Smash");
    }

}
