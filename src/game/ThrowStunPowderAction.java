package game;

import engine.Action;
import engine.Actor;
import engine.GameMap;

import java.util.Random;

/**
 * Special Action that allows an Actor to throw stun powder at a subject
 */
public class ThrowStunPowderAction extends Action
{
    private float accuracy;
    private AlternatePlayer subject;
    private StunPowder stunPowder;

    /**
     * the Constructor
     * @param subject the player who gets thrown at
     * @param stunPowder a stun powder to throw
     */
    public ThrowStunPowderAction(AlternatePlayer subject, StunPowder stunPowder)
    {
        this.subject = subject;
        this.stunPowder = stunPowder;
    }

    /**
     * Throws the stun powder. 50/50 chance the player will be stunned
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random randomNum = new Random();
        accuracy = randomNum.nextFloat();
        double hitChance = 0.5;
        if (accuracy >= hitChance ) {
            stunPowder.stuns(subject);
            return menuDescription(actor)+ "\nThe stun powder hits! You must now wait for 2 turns for it to wear off.";
        }
        else {
            return menuDescription(actor)+ "\nThe stun powder misses " + subject + "!";
        }
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player throws the stun powder and moves away."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" throws the stun powder and moves away.";
    }

    /**
     * Returns an empty string as throwing the stun powder does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
