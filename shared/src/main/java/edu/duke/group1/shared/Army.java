package edu.duke.group1.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the container for holding all units
 * Units can have different levels and to contain it,
 * we use this class
 * A region has one Army, one Army has many Units
 */
public class Army implements Serializable {

    /* one army has many units with different levels */
    private List<Unit> units;

    /**
     * Constructor for Army
     */
    public Army(){
        this.units = new ArrayList<>();
    }

    /**
     * Constructor for Army
     *
     * @param units a list of units
     */
    public Army(List<Unit> units){
        this.units = units;
    }

    /**
     * Get the list of units
     *
     * @return a list of units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Set a list of units
     *
     * @param units the units to be set in this region
     */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    /**
     * Set a list of units
     *
     * @param number of units the units to be set in this region
     */
    public void setUnits(int number,int level){
        this.units.clear();
        addUnits(number,level);
    }

    /**
     * Used to add the number of units into army
     * @param number how many units need to be added
     */
    public void addUnits(int number,int level){
        for(int i = 0; i < number; i++)
            this.units.add(new Unit("unit", level));
    }

    /**
     * Used to remove the units in army
     * @param number how many units needs to be removed
     * @throws IllegalArgumentException
     */
    public void removeUnits(int number,int level) throws  IllegalArgumentException{
        if(this.units.size() < number){
            throw new IllegalArgumentException("not enough units to be remove, check your input");
        }
        Iterator<Unit> u = units.iterator();


        int num = number;
        while(u.hasNext()&& num>0){
            Unit next = u.next();
            if(next.getLevel()==level){
                u.remove();
                num--;

            }
        }

    }

    /**
     * Used to get the number of the specific level in the army
     * @param level the number of the level
     */
    public int getLevelNumber(int level){
        int number = 0;
        for(Unit u :units){
            if(u.getLevel()==level){
                number++;
            }
        }
        return number;
    }

    /**
     * Used to get the highest level unit in the army
     */
    public int getHighestLevel(){
        int level = 0;
        for(Unit u :units){
            if(u.getLevel()>level){
                level = u.getLevel();
            }
        }
        return level;
    }

    /**
     * Used to get the lowest level unit in the army
     */
    public int getLowestLevel(){
        int level = 6;
        for(Unit u: units){
            if(u.getLevel()<level){
                level = u.getLevel();
            }
        }
        return level;
    }

}
