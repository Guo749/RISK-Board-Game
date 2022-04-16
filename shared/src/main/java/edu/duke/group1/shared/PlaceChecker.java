package edu.duke.group1.shared;


/**
 * The rule checker that can check if a single place is valid
 * and will be involved in the multiple actions check
 */
public class PlaceChecker extends RuleChecker {
    Place pl;
    @Override
    public String checkMyRule(Action a, AbstractMap map) {
        pl = (Place) a;
        if (pl.getRegionName() == null) {
            return "Invalid action! The Region can not be null.";
        }
        if (pl.getUnitNum() <= 0) {
            return "Invalid action! The number of units must be greater than 0";
        }
        if(!map.isInMap(pl.getRegionName())){
            return "Invalid action! the input region must be in the map";
        }
        if (map.getRegionFinder().get(pl.getRegionName()).getOwnerID() != pl.getPlayerID()) {
            return "Invalid action! You must put units on your own region!";
        }
        return null;
    }
    /**
     * Add the capacity check based on the checkMyRule
     */
    public String checkPlaceRule(Action a, AbstractMap map, int c) {
            pl = (Place) a;
            String s = checkMyRule(pl, map);
            if ((pl.getUnitNum() > c)) {
                return "Invalid action! The number of units can not be greater than the number you holds";
            }
            return s;
    }
}
