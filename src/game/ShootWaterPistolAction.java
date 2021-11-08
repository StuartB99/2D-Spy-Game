package game;

import engine.*;

import java.util.Random;

/**
 * This class is an action that allows the user to shoot the water pistol when it is full
 */
public class ShootWaterPistolAction extends Action {

    private WaterPistol waterPistol;
    private YugoMaxx enemy;

    /**
     *  The Constructor
     * @param waterPistol The water pistol that is to be refilled.
     * @param enemy The (hostile) actor to be shot by the pistol.
     */
    ShootWaterPistolAction( WaterPistol waterPistol, YugoMaxx enemy) {
        this.waterPistol = waterPistol;
        this.enemy = enemy;
    }

    /**
     *  Results in the actor shooting the water pistol. This can only be done when next to an enemy.
     *  The pistol has a 70% accuracy of hitting the intended target. Sets the water pistol to empty regardless of
     *  succession of the hit, and destroys the exoskeleton if it was a successful hit.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player missed! The pistol requires a refill!"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (waterPistol.isFull()) {
            Random randomNum = new Random();
            float accuracy = randomNum.nextFloat();
            double hitChance = 0.3;
            waterPistol.setFull(false);
            if (accuracy >= hitChance) {
                enemy.removeItemFromInventory(enemy.getExoskeleton());
                return actor + " successfully destroyed the exoskeleton!";
            }
            return actor + " missed! The pistol requires a refill!";
        }
        return "Player shoots nothing. Water pistol is empty.";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player shoots the water pistol."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots the water pistol";
    }

    /**
     * Returns an empty string as shooting the water pistol does not have a dedicated hot key.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() { return ""; }
}
