package edu.duke.group1.shared;



/**
 * This class is used to initialize the number of the regions of the player at the placement phase
 *
 */

public class Place extends Action {
    /* the id for the player in this room */
    private Integer playerID;
    /* the region name the player want to place*/
    private String regionName;
    /* the units number the player wants to place */
    private Integer unitNum;
    /* the level the player want to place */
    private Integer level;


    public Place(Integer playerID, String regionName,
                       Integer unitNum,Integer level) {
        this.playerID = playerID;
        this.regionName = regionName;
        this.unitNum = unitNum;
        this.level = level;
    }
    /**
     * Place the number on the region in placement phase
     */
    @Override
    public void doAction(AbstractMap map) {
        map.getRegionFinder().get(regionName).getArmy().setUnits(unitNum,level);
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public String getRegionName() {
        return regionName;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public Integer getLevel() {
        return level;
    }

    public String getString(){
        String s = "You put " + unitNum + " units at " + regionName;
        return s;
    }
}
