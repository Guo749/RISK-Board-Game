package edu.duke.group1.shared;




import java.util.Map;
import java.util.Random;

/**
 * In the game flow, the player will attack another players' region
 * this is the first version attack based on the random dice
 * without consideration of level of units and will be modified in the future
 */
public class Attack extends Action {
    /* the id for the player */
    private Integer playerID;
    /* the source region for the attackers */
    private String srcRegion;
    /* the destination for the attackers */
    private String dstRegion;
    /* the number of the attackers */
    private Integer unitNum;
    /* the level of the attackers */
    private Integer level;





    public Attack(Integer playerID, String srcRegion, String dstRegion, Integer unitNum, Integer level) {
        this.playerID = playerID;
        this.srcRegion = srcRegion;
        this.dstRegion = dstRegion;
        this.unitNum = unitNum;
        this.level = level;
    }

    /**
     * this function is used to move units from attackers to the defenders
     * only move operation, the attack will be decided by server
     *
     * @param map the map to do the action on
     */
    @Override
    public void doAction(AbstractMap map){
        if(!map.playerResource.get(playerID).consumedFood(unitNum)){return;}
        map.getRegionFinder().get(srcRegion).getArmy().removeUnits(unitNum,level);
        Map<Integer, Army> enemyArmies = map.getRegionFinder().get(dstRegion).getEnemyArmies();
        Army army = enemyArmies.getOrDefault(playerID, null);
        if(army == null){
            army = new Army();
            army.setUnits(unitNum,level);
            enemyArmies.put(playerID, army);
        }else{
            army.addUnits(unitNum,level);
            enemyArmies.put(playerID, army);
        }

        System.out.println("Attack begins!");
    }


    public String getSrcRegion() {
        return srcRegion;
    }

    public String getDstRegion() {
        return dstRegion;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public String getString(){
        String s = "You order " + unitNum + " units of level " +  level + " to attack from " + srcRegion + " to " + dstRegion;
        return s;
    }
}
