package edu.duke.group1.shared;

import java.util.HashMap;
/**
 * this class is used to upgrade the level of the units on the ground.
 */
public class UpgradeAction extends Action{
    private Integer playerId;
    private String regionName;
    private Integer unitNum;
    private Integer preLevel;
    private Integer nextLevel;
    private boolean isSpy;
    private boolean isCloak;

    public UpgradeAction(Integer playerId, String regionName, Integer unitNum, Integer preLevel, Integer nextLevel) {
        this.playerId = playerId;
        this.regionName = regionName;
        this.unitNum = unitNum;
        this.preLevel = preLevel;
        this.nextLevel = nextLevel;
        this.isCloak = false;
        this.isSpy = false;
    }

    public UpgradeAction(Integer playerId, String regionName, Integer unitNum, Integer preLevel, Integer nextLevel, boolean isSpy, boolean isCloak) {
        this.playerId = playerId;
        this.regionName = regionName;
        this.unitNum = unitNum;
        this.preLevel = preLevel;
        this.nextLevel = nextLevel;
        this.isSpy = isSpy;
        this.isCloak = isCloak;
    }

    @Override
    public void doAction(AbstractMap map){
        if(isCloak){
            if (!map.playerResource.get(playerId).consumedMoney(20)) return;
            map.regionFinder.get(regionName).haveCloak += 3;
            return;
        }
        int usedMoney = isSpy? unitNum*20 :calUsedMoneyForUnits();
        if(!map.playerResource.get(playerId).consumedMoney(usedMoney)){
            return;
        }
        Region currRegion= map.getRegionFinder().get(regionName);
        currRegion.getArmy().removeUnits(unitNum,preLevel);
        if(!isSpy) currRegion.getArmy().addUnits(unitNum,nextLevel);
        if(isSpy && preLevel>=1){
            map.playerSpies.get(playerId).addSpy(regionName,unitNum);
        }
    }

    /**
     * Used to calculated the total money used for upgrades the units
     */
    public int calUsedMoneyForUnits(){
        HashMap<Integer,Integer> upTable=new HashMap<>();
        upTable.put(0,0);
        upTable.put(1,3);
        upTable.put(2,8);
        upTable.put(3,19);
        upTable.put(4,25);
        upTable.put(5,35);
        upTable.put(6,50);
        int perUnit = 0;
        for(int i = preLevel+1; i <= nextLevel;i++){
            perUnit+=upTable.get(i);
        }
        return perUnit*unitNum;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getRegionName() {
        return regionName;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public Integer getPreLevel() {
        return preLevel;
    }

    public Integer getNextLevel() {
        return nextLevel;
    }

    public String getString(){
        String s = "You order " + unitNum + " units of level " + preLevel + " to upgrade to level " + nextLevel + " at " + regionName;

        return s;
    }

    public boolean isSpy() {
        return isSpy;
    }

    public boolean isCloak() {
        return isCloak;
    }
}
