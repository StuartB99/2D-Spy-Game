package game;

import engine.*;

/**
 * A child class of Player that so as to be able to manipulate its playTurn, along with making the player stunnable by
 * skipping his turn.
 */
public class AlternatePlayer extends Player
{
    private boolean stunned = false;
    private int stunnedDuration = 0;

    /**
     * Constructor.
     *
     * @param name Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param priority How early in the turn the player can act
     * @param hitPoints Player's starting number of hitpoints
     */
    AlternatePlayer(String name, char displayChar, int priority, int hitPoints)
    {
        super(name, displayChar, priority, hitPoints);
        addSkill(SkillList.FLY_ROCKET);
        addSkill(SkillList.UNLOCK_DOOR);
        addSkill(SkillList.TALK_TO_Q);
        addSkill(SkillList.USE_OXYGEN_DISPENSER);
    }

    /**
     * Play a turn if player is not stunned. Doing this means displaying a menu to the user and getting their selected option.
     * If the player is stunned, the player skips a turn for 2 duration
     * @param actions the actions to display
     * @param map the map to display
     * @param display the object that performs the console I/O
     * @return if not stunned, the Action that the user selects. if stunned, the SkipTurnAction
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display)
    {
        int stunPowderStunDuration = 2;
        int oxygenNeededPerTurn = 1;
        int oxygenPointsUsedThisTurn = 0;
        int totalOxygenRemaining = 0;
        GameMap moonMap = WorldAccessible.worlds.get(0).getMaps().get(1);

        actions.add(new QuitGameAction());  // a quit game option for the user, added here so it is accessible for every turn of the player

        WorldAccessible.worlds.get(0).getDisplay().println("Health points: "+this.hitPoints);
        // counting all the oxygen points the player has to be displayed
        for(OxygenTank oxygenTank : OxygenDispenser.oxygenTanks)
        {
            if (getInventory().contains(oxygenTank))
            {
                totalOxygenRemaining += oxygenTank.getAmountOfOxygen();
            }
        }
        WorldAccessible.worlds.get(0).getDisplay().println("Oxygen Points: "+ totalOxygenRemaining);


        // Checking if the player is on the moon, that he has an oxygen tank with oxygen in it which would provide the
        // breathe in space skill. If not the safety system would place the player back on earth
        if(map.equals(moonMap)){
            if(this.hasSkill(SkillList.BREATHE_IN_SPACE)){  // if player has no oxygen
                // loop through all the available tanks to sum up the oxygen remaining
                for(OxygenTank oxygenTank : OxygenDispenser.oxygenTanks) {
                    if(getInventory().contains(oxygenTank)){
                        if(oxygenTank.hasOxygen() && oxygenPointsUsedThisTurn < oxygenNeededPerTurn ){
                            oxygenTank.decrementAmountOfOxygen();
                            oxygenPointsUsedThisTurn++;
                        }
                        totalOxygenRemaining += oxygenTank.getAmountOfOxygen();
                    }
                }
            }
            else{
                return new SafetySystemNoOxygenAction();
            }
        }

        if (!isStunned()){
            return super.playTurn(actions, map, display);
        }
        else{
            while (stunnedDuration < stunPowderStunDuration){
                stunnedDuration++;
                return new SkipTurnAction();
            }
            stunnedDuration = 0;
            this.setStunned(false);
            return super.playTurn(actions, map, display);
        }
    }

    /**
     * The accessor for stunned variable
     * @return the current state of stunned
     */
    boolean isStunned()
    {
        return stunned;
    }

    /**
     * The mutator for stunned variable
     * @param stunned the new state of player
     */
    void setStunned(boolean stunned)
    {
        this.stunned = stunned;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AlternateAttackAction(otherActor, this));
    }
}
