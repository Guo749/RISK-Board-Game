package edu.duke.group1.shared;
/**
 * This class is used to check the validation of the upgrade
 */
public class UpgradeActionChecker extends RuleChecker{
    UpgradeAction upgrade;

    @Override
    public String checkMyRule(Action a, AbstractMap map){
        upgrade = (UpgradeAction) a;
        if(!map.isInMap(upgrade.getRegionName())){
            return "Invalid action! the input region must be in the map";
        }
        System.out.println(upgrade.isSpy());
        System.out.println(upgrade.getPreLevel());
        System.out.println(upgrade.getUnitNum()*20);
        System.out.println(map.getPlayerResource().get(upgrade.getPlayerId()).getMoney());
        if(upgrade.isSpy()&&(upgrade.getPreLevel()<1 || upgrade.getPreLevel()>6 || upgrade.getUnitNum()*20 > map.getPlayerResource().get(upgrade.getPlayerId()).getMoney())){
            return "Invalid action! invalid spy upgrade!";
        }
        if(!upgrade.isCloak()&& !upgrade.isSpy()&&(upgrade.getNextLevel() > map.getPlayerResource().get(upgrade.getPlayerId()).getTechLevel() || upgrade.getPreLevel() < 0 || upgrade.getPreLevel() > 6 || upgrade.getNextLevel() < 0 || upgrade.getNextLevel() > 6 || upgrade.getPreLevel() > upgrade.getNextLevel())){
            return "Invalid action! Unit level must be in the range [0,6]!";
        }
        if(map.getRegionFinder().get(upgrade.getRegionName()).getArmy().getLevelNumber(upgrade.getPreLevel()) < upgrade.getUnitNum()){
            return "Invalid action! the units number in your region is not enough!";
        }
        if(!upgrade.isCloak()&& !upgrade.isSpy()&&(upgrade.calUsedMoneyForUnits() > map.getPlayerResource().get(upgrade.getPlayerId()).getMoney())){
            return "You do not have enough money to upgrade your units";
        }
        if (map.getRegionFinder().get(upgrade.getRegionName()).getOwnerID() != upgrade.getPlayerId()) {
            return "Invalid action! You must put units on your own region!";
        }
        return null;
    }
}
