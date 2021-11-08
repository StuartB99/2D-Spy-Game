package game;

import engine.*;

/**
 * Item which holds oxygen that allows user to be on the moon
 */
class OxygenTank extends Item
{
    private int amountOfOxygen;

    /**
     * Constructor for the oxygen tank. Provides the user who holds the OxygenTank with breathe_in_space skill. Each
     * tank has 10 oxygen points
     */
    OxygenTank()
    {
        super("Oxygen tank", '8');
        OxygenDispenser.oxygenTanks.add(this);
        addSkill(SkillList.BREATHE_IN_SPACE);
        amountOfOxygen = 10;
    }

    /**
     * Returns whether the oxygen tank has oxygen or not
     * @return true if the oxygen point is greater than 0, false otherwise
     */
    boolean hasOxygen(){
        if(getAmountOfOxygen() <= 0)
        {
            return false;
        }
        return true;
    }

    /**
     * Accessor for the private attribute amountOfOxygen
     * @return the private attribute amountOfOxygen
     */
    int getAmountOfOxygen()
    {
        return amountOfOxygen;
    }

    /**
     * Decrement by one the amount of oxygen within the oxygen tank. Removes breathe_in_skill space from the oxygen tank
     * if the oxygen tank has 0 oxygen points in it
     */
    void decrementAmountOfOxygen()
    {
        this.amountOfOxygen--;
        if(getAmountOfOxygen() == 0){
            this.removeSkill(SkillList.BREATHE_IN_SPACE);
            displayChar = '9';
        }
    }
}
