package edu.duke.group1.shared;


import java.util.*;

/**
 * This class move the units from one region to another of one player
 */

public class Move extends Action {
    /* the id for the player */
    private Integer playerID;
    /* the source region for the units */
    private String srcRegion;
    /* the destination of the units */
    private String dstRegion;
    /* the number of the units */
    private Integer unitNum;
    /* the level of the units */
    private Integer level;
    /* if the units are spy */
    private boolean isSpy;


    public Move(Integer playerID,String srcRegion, String dstRegion, Integer unitNum, Integer level){
        this.playerID = playerID;
        this.srcRegion = srcRegion;
        this.dstRegion = dstRegion;
        this.unitNum = unitNum;
        this.level = level;
        this.isSpy = false;
    }

    public Move(Integer playerID, String srcRegion, String dstRegion, Integer unitNum, Integer level, boolean isSpy) {
        this.playerID = playerID;
        this.srcRegion = srcRegion;
        this.dstRegion = dstRegion;
        this.unitNum = unitNum;
        this.level = level;
        this.isSpy = isSpy;
    }

    @Override
    public void doAction(AbstractMap map){
        int consume = isSpy ? unitNum*Region.STANDARD_SIZE : getShortestSizeOfRoute(map) * unitNum;
        if(!map.playerResource.get(playerID).consumedFood(consume)) {
            return;
        }
        //System.out.println("Player "+ playerID + " will move "+ unitNum + " units from "+srcRegion+" to "+dstRegion);
        if(isSpy && map.isConnectedForAttack(map.regionFinder.get(srcRegion).getID(),map.regionFinder.get(dstRegion).getID())){
            map.playerSpies.get(playerID).addSpy(dstRegion,unitNum);
            map.playerSpies.get(playerID).removeSpy(srcRegion,unitNum);

        }else{
            map.getRegionFinder().get(srcRegion).getArmy().removeUnits(unitNum,level);
            map.getRegionFinder().get(dstRegion).getArmy().addUnits(unitNum,level);
        }
    }

    public int getShortestSizeOfRoute(AbstractMap map){
        Region src = map.getRegionFinder().get(srcRegion);
        Region des = map.getRegionFinder().get(dstRegion);

        if(!map.isConnectedForMove(src.getID(),des.getID())) return -1;

        Stack<Region> stack = new Stack<>();
        Stack<Integer> route = new Stack<>();
        Set<Region> visited = new HashSet<>();

        visited.add(src);
        stack.push(src);
        route.push(0);

        List<Integer> res = new ArrayList<>();

        while(!stack.isEmpty()){

            Region curr = stack.pop();
            int temp = route.pop();

            for(Integer i : curr.getNeighbor()){

                if(map.getRegions().get(i-1).getName().equals(dstRegion)){
                    res.add(temp+1);
                    break;
                }

                if(map.getRegions().get(i-1).getOwnerID()==src.getOwnerID() && !visited.contains(map.getRegions().get(i-1))){
                    stack.push(map.getRegions().get(i-1));
                    route.push(temp+1);
                    visited.add(map.getRegions().get(i-1));
                }
            }
        }

        return getTheMin(res) * Region.STANDARD_SIZE;
    }

    public int getTheMin(List<Integer> list){
        int res = list.get(0);
        for(Integer i:list){
            res = Math.min(i,res);
        }
        return res;
    }

    public Integer getPlayerID() {
        return playerID;
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

    public String getString(){
        String s = "You order " + unitNum + " units of level " + level + " to move from " + srcRegion + " to " + dstRegion;
        return s;
    }

    public boolean isSpy() {
        return isSpy;
    }
}
