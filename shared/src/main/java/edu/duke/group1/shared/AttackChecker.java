package edu.duke.group1.shared;


/**
 * The rule checker that can check if a single attack is valid
 * and will be involved in the multiple actions check
 */
public class AttackChecker extends RuleChecker{
    Attack atk;
    @Override
    public String checkMyRule(Action a, AbstractMap map){
        atk = (Attack) a;
            if(atk.getDstRegion()==null||atk.getSrcRegion()==null){
                return "Invalid action! The Region can not be null";
            }
            if(atk.getDstRegion().equals(atk.getSrcRegion())){
                return "Invalid action! The destination name can not be same as the source region name";
            }
            if((!map.isInMap(atk.getDstRegion()))||(!map.isInMap(atk.getSrcRegion()))){
                return "Invalid action! the input region must be in the map";
            }

            if(map.getRegionFinder().get(atk.getSrcRegion()).getArmy().getUnits().size() < atk.getUnitNum()){
                return "Invalid action! The number of units you move must be less than the source region army number!";
            }
            if(map.getRegionFinder().get(atk.getDstRegion()).getOwnerID()==map.getRegionFinder().get(atk.getSrcRegion()).getOwnerID()){
                return "Invalid action! The owner of destination and the source region must be different";
            }
           if(!map.isConnectedForAttack(map.getRegionFinder().get(atk.getSrcRegion()).getID(),map.getRegionFinder().get(atk.getDstRegion()).getID())){
               return "Invalid action! The destination and the source region must be connected.";
           }
            if(atk.getUnitNum()<=0){
                return "Invalid action! The number of units must be greater than 0";
            }
        if(map.getRegionFinder().get(atk.getSrcRegion()).getArmy().getLevelNumber(atk.getLevel()) < atk.getUnitNum()){
            return "Invalid action! The number of the units of the corresponding level must be less than the source region can hold!";
        }
            if(map.playerResource.get(atk.getPlayerID()).getFood()<atk.getUnitNum()){
                return "Invalid action! The food for attack is not enough!";
            }
            return null;
    }
}
