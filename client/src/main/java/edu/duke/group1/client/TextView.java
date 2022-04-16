package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import edu.duke.group1.shared.AbstractMap;

import java.util.*;

/**
 * This is used to display the view for each player
 * TEXT VERSION
 */
public class TextView {

    /* the whole map for this view */
    private  AbstractMap map;

    /**
     * Constructor for this map, used to display
     *
     * @param map the map to display
     */
    public TextView (AbstractMap map){
        this.map = map;
    }

    /**
     * Display content according to user ID
     *
     * @param playerID the ID used in Map, from 1 to N(N is the number for this map)
     */
    public  void displayByPlayerId(int playerID){
        displayRegion(playerID, true);
        displayRegion(playerID, false);
    }

    /**
     * used to print to console the game information
     *
     * @param playerID the playerID to check out
     * @param mySelf true -> the view from myself, false -> not the view from myself
     */
    private  void displayRegion(int playerID, boolean mySelf){
        System.out.println(displayRegionInString(playerID, mySelf));
    }

    @Deprecated
    public  String displayRegionInString(int playerID, boolean mySelf){
        StringBuilder res = new StringBuilder();
        playerID = map.getPlayerMapping(playerID);
        res.append(basicInfoDisplay(playerID));
        res.append("\n********* "+ (mySelf ? "my" : "enemy")+" Region Situation *************");
        if(mySelf){
            for(Region region : map.getRegions()){
                if(region.getOwnerID() == playerID) {
                    res.append("\nname : " + region.getName() + "\nUnits : "+ printArmy(region.getArmy()) + "\ncurrent Owner: MySelf\n");
                    res.append(printRegionNeighbor(region));
                }

            }


            res.append("\n********* "+  "enemy" +" Region Situation *************");
            List<Integer> playerList = map.getPlayerList();
            Set<String> visited = new HashSet<>();
            for(Integer ID : playerList){
                if(ID == playerID){
                    continue;
                }

                for(Region region : map.getRegions()){
                    if(hasMySpy(region, playerID, map) || AdjacentOrNoCloaking(region, playerID, map)){
                        if(visited.contains(region.getName()))
                            continue;
                        res.append("\nname : " + region.getName() + "\nUnits : "+ printArmy(region.getArmy()) + "\ncurrent Owner: " + ID + "\n");
                        visited.add(region.getName());
                        res.append(printRegionNeighbor(region));
                    }
                }
            }
        }
        res.append(printResult(map, playerID));

        res.append(getSpyInfo(playerID, map));
        return res.toString();
    }

    public String getSpyInfo(int playerID, AbstractMap map) {
        Map<Integer, Spy> playerSpies = map.getPlayerSpies();
        Spy spy = playerSpies.get(playerID);

        StringBuilder res = new StringBuilder("\n");

        res.append("Your Spy Has Occupied \nin areas below:");
        for(String str : spy.getSpyLocation().keySet())
            res.append("\n" + str);

        return res.toString();
    }

    private boolean AdjacentOrNoCloaking(Region enemyRegion, int myID, AbstractMap map) {
        if(enemyRegion.haveCloak > 0)
            return false;

        List<Region> regions = map.getRegions();
        for(Region r : regions){
            if(r.getOwnerID() == myID && map.isConnectedAttackingForView(r.getID(), enemyRegion.getID())){
                return true;
            }
        }

        return false;
    }

    private boolean hasMySpy(Region region, int myID, AbstractMap map) {
        Map<Integer, Spy> playerSpies = map.getPlayerSpies();
        if(!playerSpies.containsKey(myID))
            return false;

        Spy mySpy = playerSpies.get(myID);

        HashMap<String, Integer> spyLocation = mySpy.getSpyLocation();
        return spyLocation.containsKey(region.getName());
    }




    public String printResult(AbstractMap map, int playerID){
        String s = "\n\n";
        if(map.getPlayerWin(playerID - 1) == 1){//the player is already lost, set it to game lose
            s = s + "You have already lost!\nYou can hit the refresh to see the current result.\n";
        }
        if(map.getWinner() != -1){
            s = "\n\n" + "Player " + map.getWinner() + " won the game!\nPlease return to the lobby.\n";
        }
        return s;
    }


    public String printRegionInfo(String text, PlayerInfo info) {
        String[] splits = text.split(" ");
        String regionName = splits[0];

        AbstractMap map = info.getMap();
        int ID = map.getPlayerMapping(info.getPlayerId());

        Map<String, Region> regionFinder = map.getRegionFinder();
        Region region = regionFinder.get(regionName);
        StringBuilder res = new StringBuilder();


        if(region.getOwnerID() == ID){
            res.append(basicInfoDisplay(ID));
            res.append(printRegion(region, ID));
            res.append(getSpyInfo(ID, map));
        }else if(NoCloaking(region, ID) && (hasMySpy(region, ID, map) || isConnected(region, ID, map))) {
            res.append(basicInfoDisplay(ID));
            res.append(printRegion(region, ID));
            res.append(getSpyInfo(ID, map));
        }

        return res.toString();
    }

    private boolean NoCloaking(Region region, int myID) {
        if(region.getOwnerID() == myID)
            return true;

        return region.haveCloak == 0;
    }

    private boolean isConnected(Region region, int myID, AbstractMap map) {
        System.out.println("In isConnected");
        if(region.getOwnerID() == myID)
            return true;

        for(Integer neighborID : region.getNeighbor()){
            for(Region r : map.getRegions()){
                if(r.getID() == neighborID && r.getOwnerID() == myID) {
                    System.out.println("region ID -> " + r.getID());
                    System.out.println("owner ID or myID" + r.getOwnerID() + "->" + myID);
                    return true;
                }
            }
        }


        return false;
    }




    public String printRegion(Region region, int  playerID){
        StringBuilder res = new StringBuilder();
        res.append(region.getName())
                .append(" \n\tcurrent owner: ")
                .append(playerID == region.getOwnerID() ? "you" : region.getOwnerID())
                .append("\n");

        res.append("Units:");
        res.append(printArmy(region.getArmy()));

        res.append(printRegionNeighbor(region));
        return res.toString();
    }

    /**
     * Print the well-formatted string of region
     *
     * @param region the region to checkout
     * @return string format of that
     */
    private  String printRegionNeighbor(Region region) {
        StringBuilder res = new StringBuilder();
        res.append("\n");
        Map<Integer, String> lookup = map.getLookup();
        res.append("this region has neighbor\n ");
        for(Integer id : region.getNeighbor()){
            String name = lookup.get(id);
            res.append("\t").append(name).append(" ").append("\n");
        }

        res.setLength(res.length() - 2);
        return res.toString();
    }



    public String printArmy(Army army){
        String s = "\n\t";
        if(army == null)
            return s;
        for (int i = 0; i < 7; i++){
            s = s + "level " + i + " : " + army.getLevelNumber(i) + " " + (i % 2 != 0 ? " \n\t" : "");
        }
        return s;
    }

    public String basicInfoDisplay(int playerID){
        StringBuilder res = new StringBuilder();
        res.append("Food resource:").append(map.getPlayerResource().get(playerID).getFood()).append(" \n");
        res.append("Tech resource:").append(map.getPlayerResource().get(playerID).getMoney()).append("\n");
        res.append("Tech level:").append(map.getPlayerResource().get(playerID).getTechLevel()).append("\n \n");

        res.append("You have : ");
        for(Region region : map.getRegions()){
            if(region.getOwnerID() == playerID)
                res.append("\n\t").append(region.getName());
        }

        res.append("\n");
        return res.toString();
    }
}



