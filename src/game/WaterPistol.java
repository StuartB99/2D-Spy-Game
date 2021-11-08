package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A weapon which does no damage but can destroy Yugo Maxx's exoskeleton
 */
public class WaterPistol extends WeaponItem {

    private boolean isFull = false;
    static List<WaterPistol> waterPistols = new ArrayList();

    /**
     * the Constructor. Does no damage as it shoots water but has the potential to destroy an exoskeleton.
     */
    public WaterPistol() {
        super("Water pistol", 'j',0,"shoots nothing at" );
        waterPistols.add(this);
    }

    /**
     * The mutator for the private boolean attribute.
     * @param full this boolean value is a representation of whether the gun has water filled or not.
     */
    void setFull(boolean full) {
        isFull = full;
    }

    /**
     * The mutator for the private boolean attribute
     * @return boolean value of isFull
     */
    boolean isFull() {
        return isFull;
    }

}
