package edu.duke.group1.shared;


import java.io.Serializable;

/**
 * Basic element for the Army
 */
public class Unit  implements Serializable {

    /* the name for the unit */
    private String name;

    /* level for the unit, at least 1 */
    private int level;

    /**
     * Constructor for Unit
     *
     * @param name the name for unit
     * @param level the level for unit
     */
    public Unit(String name, int level) {
        this.name = name;

        if(level < 0){
            throw new IllegalArgumentException("Unit Level Cannot be negative");
        }
        this.level = level;
    }

    /**
     * Get the Name for Unit
     * @return the name of unit
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the unit
     *
     * @param name set the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get current level for this unit
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * set the level for unit
     * cannot be negative
     * @param level
     */
    public void setLevel(int level) throws IllegalArgumentException{
        if(level < 0){
            throw new IllegalArgumentException("Unit Level Cannot be negative");
        }
        this.level = level;
    }
}
