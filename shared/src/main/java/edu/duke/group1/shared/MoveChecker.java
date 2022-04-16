package edu.duke.group1.shared;


/**
 * The rule checker that can check if a single move is valid
 * and will be involved in the multiple actions check
 * do not add the  "is connected", because the regions of one owner are all connected with each other in our map
 */
public class MoveChecker extends RuleChecker{
    Move mov;
    @Override
    public String checkMyRule(Action a, AbstractMap map){
        mov = (Move) a;
        if(mov.getDstRegion()==null||mov.getSrcRegion()==null){
            return "Invalid action! The Region can not be null.";
        }
        if(mov.getDstRegion().equals(mov.getSrcRegion())){
            return "Invalid action! The destination name can not be same as the source region name.";
        }
        if((!map.isInMap(mov.getDstRegion()))||(!map.isInMap(mov.getSrcRegion()))){
            return "Invalid action! the input region must be in the map";
        }
        if(mov.isSpy()&& !map.getPlayerSpies().get(mov.getPlayerID()).getSpyLocation().containsKey(mov.getSrcRegion())){
            return "You do not have spy at this region!";
        }
        if(mov.isSpy()&& !map.isConnectedForAttack(map.getRegionFinder().get(mov.getSrcRegion()).getID(),map.getRegionFinder().get(mov.getDstRegion()).getID())){
            return "Invalid action! The move of spy must across one region once a time!";
        }
        if(mov.isSpy()&& mov.getUnitNum() > map.getPlayerSpies().get(mov.getPlayerID()).getSpyLocation().get(mov.getSrcRegion())){
            System.out.println(map.getPlayerSpies().get(mov.getPlayerID()).getSpyLocation().get(mov.getSrcRegion()));
            return  "Invalid action! The spy number is not enough for move";
        }
        if(!mov.isSpy()&&map.getRegionFinder().get(mov.getSrcRegion()).getArmy().getUnits().size() < mov.getUnitNum()){
            return "Invalid action! The number of units you move must be less than the source region army number!";
        }
        if(!mov.isSpy()&&map.getRegionFinder().get(mov.getDstRegion()).getOwnerID()!=map.getRegionFinder().get(mov.getSrcRegion()).getOwnerID()){
            return "Invalid action! The destination and the source region must belong to same player";
        }
        if(!mov.isSpy()&&(map.getRegionFinder().get(mov.getSrcRegion()).getOwnerID() != mov.getPlayerID()||map.getRegionFinder().get(mov.getDstRegion()).getOwnerID()!= mov.getPlayerID())) {
            return "Invalid action! You must move units on your own region!";
        }
        if(!mov.isSpy()&&!map.isConnectedForMove(map.getRegionFinder().get(mov.getSrcRegion()).getID(),map.getRegionFinder().get(mov.getDstRegion()).getID())){
            return "Invalid action! The destination and the source region must be connected";
        }
        if(mov.getUnitNum()<=0){
            return "Invalid action! The number of units must be greater than 0.";
        }
        if(!mov.isSpy()&&map.getRegionFinder().get(mov.getSrcRegion()).getArmy().getLevelNumber(mov.getLevel()) < mov.getUnitNum()){
            return "Invalid action! The number of the units in the corresponding level must be less than the source region can hold!";
        }
        if(!mov.isSpy()&&map.playerResource.get(mov.getPlayerID()).getFood()<mov.getShortestSizeOfRoute(map)*mov.getUnitNum()){
            return "You do not have enough food to move!";
        }
        return null;
    }
}
