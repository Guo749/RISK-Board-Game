package edu.duke.group1.server;


import edu.duke.group1.shared.*;

import java.util.ArrayList;
import java.util.List;
/**
 * room model
 */
@javax.persistence.Entity
public class Room {

    /* room ID for this room */
    @javax.persistence.Id
    final private int roomId;

    /* the capacity for this room */
    final private int capacity;

    /* contains all ids for each player */
    @javax.persistence.ElementCollection(fetch=javax.persistence.FetchType.EAGER)
    @org.hibernate.annotations.Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Integer> playerIds;

    /* each room will have a game map, used to represent */
    @javax.persistence.Lob
    private AbstractMap map;

    /* all the move the players made in a single round */
    @javax.persistence.ElementCollection(fetch=javax.persistence.FetchType.EAGER)
    @org.hibernate.annotations.Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Move> moves;

    /* all the attacks the players made in a single round */
    @javax.persistence.ElementCollection(fetch=javax.persistence.FetchType.EAGER)
    @org.hibernate.annotations.Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Attack> attacks;

    /* all the upgrades the players made in a single round */
    @javax.persistence.ElementCollection(fetch=javax.persistence.FetchType.EAGER)
    @org.hibernate.annotations.Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<UpgradeAction> upgrades;

    /* all the upgrade techs the players made in a single round */
    @javax.persistence.ElementCollection(fetch=javax.persistence.FetchType.EAGER)
    @org.hibernate.annotations.Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<UpgradeTechAction> upgradeTechs;
    /*
     * room stage status, will be used in future version
     * now it informs the game ends
     */
    private String status;

    private int lock;

    private int playerLock;
    /**
     * Constructs a room object
     *
     * @param roomId is the room's id, currently only one room
     * @param capacity is the number of player of the map
     */
    public Room(int roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.playerIds = new ArrayList<>();// all the players' id in this room
        this.moves = new ArrayList<>();
        this.attacks = new ArrayList<>();
        this.upgrades = new ArrayList<>();
        this.upgradeTechs = new ArrayList<>();
        this.status = "ConnectPhase";
        this.lock = 1;
        this.playerLock = 1;
        setMap(capacity);
    }

    public Room() {
        this.roomId = -1;
        this.capacity = 15;
    }

    /**
     * Get room ID
     *
     * @return the ID of the room
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Get the list of IDs for the players
     *
     * @return the list of IDs
     */
    public List<Integer> getPlayerIds(){
        return playerIds;
    }

    /**
     * add one player ID to the list
     *
     * @param playerId the playerID to be added
     */
    public void addPlayerId(int playerId){
        playerIds.add(playerId);
    }

    /**
     * if the number of current player online has reached the capacity
     *
     * @return true -> people are here, false -> we still need some guys
     */
    public Boolean isFull(){
        return capacity == playerIds.size();
    }

    /**
     * Get the map for this room
     *
     * @return the map part
     */
    public AbstractMap getMap(){
        return map;
    }

    /**
     * create a map for the room
     *
     * todo: if argument is illegal, should throw exception
     * @param capacity is the number of player
     */
    public void setMap(int capacity){
        if(capacity == 2){
            map = new MapForTwo();
        }else if(capacity == 3){
            map = new MapForThree();
        }else if(capacity == 4){
            map = new MapForFour();
        }else if(capacity == 5){
            map = new MapForFive();
        }else{
            map = null;
        }
    }

    /**
     * map the player ids to this map's id
     */
    public void setPlayerMapping(){
        int count = 1;
        for (Integer i: playerIds){
            map.addPlayerMapping(i, count);
            count += 1;
        }
    }

    /**
     * set each player's unplaced units
     */
    public void setUnplacedUnitsMapping(){
        for (Integer i: playerIds){
            map.updateUnplacedUnitsMapping(i, 15);
        }
    }

    /**
     * Add to move lists, solve it in one time
     *
     * @param move the move to add into move list
     */
    public void addToMoves(Move move){
        moves.add(move);
    }

    /**
     * add to attack lists, solve it in one time
     *
     * @param attack the attack to add int attack list
     */
    public void addToAttacks(Attack attack){
        attacks.add(attack);
    }

    /**
     * Add to upgrade lists, solve it in one time
     *
     * @param upgrade the upgrade to add into upgrade list
     */
    public void addToUpgrades(UpgradeAction upgrade){
        upgrades.add(upgrade);
    }

    /**
     * Add to upgrade tech lists, solve it in one time
     *
     * @param upgradeTech the upgrade tech to add into upgrade tech list
     */
    public void addToUpgradeTechs(UpgradeTechAction upgradeTech){
        for (UpgradeTechAction i: upgradeTechs){
            if (i.getPlayerId().equals(upgradeTech.getPlayerId())){
                return;
            }
        }
        upgradeTechs.add(upgradeTech);
    }

    /**
     * set the list of moves
     * @param moves
     */
    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public List<Move> getMoves(){
        return moves;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public List<UpgradeAction> getUpgrades() {
        return upgrades;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Object getLock() {
        return lock;
    }

    public Object getPlayerLock() {
        return playerLock;
    }

    public void setUpgrades(ArrayList<UpgradeAction> upgrades) {
        this.upgrades = upgrades;
    }

    public List<UpgradeTechAction> getUpgradeTechs() {
        return upgradeTechs;
    }

    public void setUpgradeTechs(ArrayList<UpgradeTechAction> upgradeTechs) {
        this.upgradeTechs = upgradeTechs;
    }

}
