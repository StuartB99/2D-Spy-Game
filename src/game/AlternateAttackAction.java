package game;

import engine.Actor;
import engine.AttackAction;
import engine.GameMap;

import java.util.Random;

/**
 * This class is an extension of the existing AttackAction class and it manipulates attacks towards
 * actors wearing the exoskeleton.
 */
public class AlternateAttackAction extends AttackAction {

    private Actor actor;
    private Actor subject;

    /***
     * The Constructor
     * @param actor The actor performing the action.
     * @param subject The actor on the receiving end of the action.
     */
    public AlternateAttackAction(Actor actor, Actor subject) {
        super(actor, subject);
        this.actor = actor;
        this.subject = subject;
    }

    /**
     * Returns a statement for the result an action taken under the given circumstances (actor punching another
     * who is wearing the exoskeleton). Wearing the exoskeleton gives that actor the skill 'INVINCIBILITY'
     * and this results in the actor taking no damage.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player does no damage as Yugo Maxx is wearing the exoskeleton..."
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.subject.hasSkill(SkillList.INVINCIBILITY)){
            return actor+" does no damage as "+ this.subject + " is wearing the exoskeleton. \n"+this.subject+" laughs at " + actor;
        }
        else{
            return super.execute(actor, map);
        }
    }
}
