package edu.duke.group1.shared;
/**
 * This class is used to upgrade the tech level of the player
 */
public class UpgradeTechAction extends Action{

    private Integer playerId;

    public UpgradeTechAction(Integer playerId) {
        this.playerId = playerId;
    }

    public void doAction(AbstractMap map){
        upgradeTech(map);
    }

    /**
     * Used to upgrade the tech level of the player and check the validation the upgrade
     * @param map the map used to upgrade
     */
    public boolean upgradeTech(AbstractMap map){
        int currTech = map.playerResource.get(playerId).getTechLevel();
        currTech++;
        if(currTech>Resource.MAX_TECH){
            return false;
        }
        int moneyCost = 25 * (currTech - 1) * (currTech - 2) / 2 + 50;
        if(!map.playerResource.get(playerId).consumedMoney(moneyCost)){
            return false;
        }
        map.playerResource.get(playerId).setTechLevel(currTech);
        return true;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getString(){
        String s = "You order a technology upgrade";
        return s;
    }
}
