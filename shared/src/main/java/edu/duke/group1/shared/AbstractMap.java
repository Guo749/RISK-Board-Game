package edu.duke.group1.shared;






import java.io.Serializable;
import java.util.*;


/**
 * Each game has only one map, and a map consists of many regions
 * Empty Map, Do not instantiate it.
 */
public abstract class AbstractMap implements Serializable {

    /* all regions are contained in this List */
    protected List<Region> regions;

    /* all players are contained in this list */
    protected List<Integer> playerList;

    /* the corresponding relation  ID and String name */
    protected Map<Integer, String> lookup;

    protected Map<String, Region> regionFinder;

    /* mapping global player IDs to this game's player IDs */
    protected Map<Integer, Integer> playerMapping;

    /*records whether each player is win or lost*/
    protected List<Integer> playerWinList;

    /*records the resource of each player*/
    protected Map<Integer,Resource> playerResource;


    /* mapping global player IDs to its unplaced units */
    protected Map<Integer, Integer> unplacedUnitsMapping;

    protected Map<Integer,Spy> playerSpies;

    public Map<Integer, Spy> getPlayerSpies() {
        return playerSpies;
    }

    /**
     * The constructor used to be called when Initiating the child class
     * like Map2 / Map3 / Map4 / Map5
     */
    public AbstractMap(){
        this.regions       = new ArrayList<>();
        this.playerList    = new ArrayList<>();
        this.lookup        = new HashMap<>();
        this.regionFinder  = new HashMap<>();
        this.playerMapping  = new HashMap<>();
        this.unplacedUnitsMapping  = new HashMap<>();
        this.playerWinList  = new ArrayList<>();
        this.playerResource = new HashMap<>();
        this.playerSpies = new HashMap<>();
        setUpRegions();
        setUpPlayerList();
        setUpLookup();
        setUpRegionFinder();
        setUpResource();
        setUpSpy();
        intializePlayerWinList();
    }

    /**
     * To see if one region has been connected with another region
     * no matter its ownership
     *
     * this has no longer been used. In new Version we have
     * isConnectedForMove
     * isConnectedForAttack
     *
     * @param oneRegion one region to examine
     * @param anotherRegion another region to examine
     * @return true -> connected, false -> do not connect
     */
    //@Deprecated
    public boolean isConnectedForAttack(int oneRegion, int anotherRegion){
        Region oneRegionEntity = regions.get(oneRegion - 1);

        List<Integer> neighbor = oneRegionEntity.getNeighbor();
        for(Integer num : neighbor){
            if(num == anotherRegion)
                return true;
        }

        return false;
    }

    /**
     * For Priting Text View
     *
     * @param oneRegion
     * @param anotherRegion
     * @return
     */
    public boolean isConnectedAttackingForView(int oneRegion, int anotherRegion){
        Region oneRegionEntity = regions.get(oneRegion - 1);
        Region anotherRegionEntity = regions.get(anotherRegion - 1);

        if(oneRegionEntity.getOwnerID() == anotherRegionEntity.getOwnerID())
            return false;

        for(Integer id : oneRegionEntity.getNeighbor()){
            if(id == anotherRegion)
                return true;
        }

        return false;
    }

    /**
     * This is used to justify whether two regions have been connected
     * by one owner
     * steps are
     *      1. check if it has been owned by one person
     *      2. using BFS to check if there is a path connected to them
     *
     * @param oneRegion one of the region to check out
     * @param anotherRegion another region to checkout
     * @return
     */
    public boolean isConnectedForMove(int oneRegion, int anotherRegion){
        Region src = regions.get(oneRegion - 1);
        Region dst = regions.get(anotherRegion - 1);
        if(src.getOwnerID() != dst.getOwnerID()){
            return false;
        }
        Deque<Region> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(src);
        visited.add(oneRegion);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Region cur = queue.removeFirst();

                if(cur.getName().equals(dst.getName())){
                    return true;
                }

                for(Integer neighbor : cur.getNeighbor()){
                    if(visited.contains(neighbor))
                        continue;
                    Region r = regions.get(neighbor - 1);
                    if(r.getOwnerID() != src.getOwnerID())
                        continue;
                    queue.addLast(r);
                    visited.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * This is used to see if one region A can attack another B
     * requirement: as long as there is one connected region C is the neighbor
     * of the B, then A can move to C and attack B
     * which means that A can attack B
     * @param
     * @param
     * @return
     */
/*    public boolean isConnectedForAttack(int from, int to){
        Region src = regions.get(from - 1);
        Region dst = regions.get(to - 1);
        if(src.getOwnerID() == dst.getOwnerID()){
            return false;
        }

        for(Integer neighbor : dst.getNeighbor()){
            Region cur = regions.get(neighbor - 1);
            if(cur.getOwnerID() == src.getOwnerID()){
                if(isConnectedForMove(cur.getID(), src.getID())){
                    return true;
                }
            }
        }

        return false;
    }*/


    /* used to set up the mapping relationship from ID to Name for regions
     *  like 1 -> Beijing
     *  */
    protected  abstract void setUpLookup();

    /* used to add player ID, from 1 to N */
    protected  abstract void setUpPlayerList();

    /* used to add all regions in this map */
    protected  abstract void setUpRegions();

    protected abstract void setUpResource();

    protected abstract void setUpSpy();


    /* used to set up relation between name and the region*/
    protected void setUpRegionFinder(){
        for(Region r : regions){
            regionFinder.put(r.getName(), r);
        }
    }

    /**
     * Get all regions for this map
     *
     * @return a list fo regions
     */
    public List<Region> getRegions() {
        return regions;
    }

    /**
     * Get all players of their ID
     * from 1 -> N
     *
     * @return a list of IDs
     */
    public List<Integer> getPlayerList() {
        return playerList;
    }

    /**
     * Get a Map, which maps the relation for
     * region ID and region name
     *
     * @return map
     */
    public Map<Integer, String> getLookup() {
        return lookup;
    }

    /**
     * Get the region according to the name
     *
     * @return the map for region
     */
    public Map<String, Region> getRegionFinder() {
        return regionFinder;
    }

    /**
     * This is used to add the mapping relationship between player ID in Map and player ID in server side
     *
     * @param playerID the ID in server side
     * @param mapPlayerID the ID for map
     */
    public void addPlayerMapping(int playerID, int mapPlayerID){
        playerMapping.put(playerID, mapPlayerID);
    }


    /**
     * Given an ID for player ID, this can return with a ID in map
     *
     * @param playerID the player ID in server to query
     * @return ID in map for the same player
     */
    public int getPlayerMapping(int playerID){
        return playerMapping.get(playerID);
    }

    /**
     * This is used to update unplacedUnits number for a given player
     *
     * @param playerID the ID in server side
     * @param UnplacedUnits the number of unplaced units
     */
    public void updateUnplacedUnitsMapping(int playerID, int UnplacedUnits){
        unplacedUnitsMapping.put(playerID, UnplacedUnits);
    }

    /**
     * get unplacedUnits number for a given player
     *
     * @param playerID the player ID in server to query
     * @return the number of unplaced units in this map for the player
     */
    public int getUnplacedUnitsMapping(int playerID){

        return unplacedUnitsMapping.getOrDefault(playerID, -1);
    }

    public Map<Integer, Resource> getPlayerResource() {
        return playerResource;
    }

    public void setPlayerResource(Map<Integer, Resource> playerResource) {
        this.playerResource = playerResource;
    }
    /**
     * add one unit to all regions when a round ends
     */
    public void addOneUnit(){
        for(Region r : regions){
            r.getArmy().addUnits(1,0);
        }
    }
    /**
     * add resources to all regions when a round ends
     */
    public void addOneResource(){
        for(Region r :regions){
            playerResource.get(r.getOwnerID()).addFood(500);
            playerResource.get(r.getOwnerID()).addMoney(100);
        }
    }
    /**
     * consume the magic of cloak to all regions when a round ends
     */
    public void consumeCloak(){
        for(Region r :regions){
            if(r.haveCloak>0) r.haveCloak--;
        }
    }


    public void intializePlayerWinList(){
        for(int i : playerList){
            playerWinList.add(2);
        }
    }

    /**
     * update the player winning list by checking whether each player own no region or all region
     */
    public void updatePlayerWinList(){
        this.playerWinList  = new ArrayList<>();
        for(int i : playerList){
            boolean isWin = true;
            boolean isLost = true;
            for(Region r : regions){
                if(r.getOwnerID() == i){
                    isLost = false;
                }else{
                    isWin = false;
                }
            }
            if(isWin){//0 for win, 1 for lose, 2 for not win or lose
                playerWinList.add(0);
            }else if(isLost){
                playerWinList.add(1);
            }else{
                playerWinList.add(2);
            }
        }
    }

    public int getPlayerWin(int i){
        return playerWinList.get(i);
    }

    /**
     * find the winner's map player id
     * @return winner's id
     */
    public int getWinner(){
        int count = 1;
        for(int i : playerWinList){
            if(i == 0){
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     * This is used to solve all attacks in each region in the same map
     * the sequence for launching attacks will be decided randomly by server
     *
     * @param attacks all attacks issued in the player side
     */
    public void solveAttacksAndSetRegionOwner(List<Attack> attacks){
        for(Region region: regionFinder.values()){
            region.solveAttacksAndSetOwner();
        }
    }

    /**
     * check if the input region name belongs to the map
     *
     * @return if the region is in the map
     */
    public boolean isInMap(String regionName){
        if(regionName == null)
            return false;

        for(Region r : regions){
            if(regionName.equals(r.getName())){
                return true;
            }
        }
        return false;
    }
}
