package game;

import engine.*;

/**
 * A weapon which does no damage but can stun the player
 */
public class StunPowder extends WeaponItem {

    /**
     * the Constructor
     */
    public StunPowder() { super("stun powder", '{',0,"stuns" ); }

    /**
     * A method which sets the player to stunned
     * @param player the player to be stunned
     */
    public void stuns(AlternatePlayer player){
        player.setStunned(true);
    }
}