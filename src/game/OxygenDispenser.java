package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A special item that dispenses oxygen tank when the user presses a button on it
 */
public class OxygenDispenser extends Ground
{
    /**
     * A list of all the oxygen tanks that has been instantiated. Is not private so as to be accessed by other classes
     */
    static List<OxygenTank> oxygenTanks = new ArrayList<>();

    /**
     * A constructor for the OxygenDispenser
     */
    public OxygenDispenser()
    {
        super('&');
    }

    /**
     * If the oxygen dispenser is not creating an oxygen tank and its button is not pressed, actor has the option to
     * press the button to create an oxygen tank. An oxygen tank will then be created
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions that contain PressOxygenDispenserAction if oxygen dispenser is available, otherwise return
     *         actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction)
    {
        if(PressOxygenDispenserAction.isPressed()){
            location.addItem(new OxygenTank());
            PressOxygenDispenserAction.setPressed(false);
        }
        if(actor.hasSkill(SkillList.USE_OXYGEN_DISPENSER)){
            for(Item item : location.getItems()){
                if(oxygenTanks.contains(item)){
                    return super.allowableActions(actor, location, direction);
                }
            }
            if(!PressOxygenDispenserAction.isPressed()){
                return new Actions(new PressOxygenDispenserAction());
            }
        }
        return super.allowableActions(actor, location, direction);
    }
}
