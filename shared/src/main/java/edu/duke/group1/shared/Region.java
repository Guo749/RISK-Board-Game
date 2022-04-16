package edu.duke.group1.shared;


import java.io.Serializable;
import java.util.*;

/**
 * A region is a country, which has a kind of army,
 * occupied by one player
 */
public class Region implements Serializable {

    /* region ID, */
    private int ID;

    /* region name */
    private String name;

    /* all neighbors in this region */
    private List<Integer> neighbor;

    /* the player/owner ID for this region */
    private int ownerID;

    /* the army occupied in this region */
    private Army army;

    /* every region in the map has the same size*/
    public static final int STANDARD_SIZE = 50;

    /* the magic of cloak*/
    public int haveCloak;

    /**
     *  this is used to contain the enemy armies
     *  key -> the playerID for the army
     *  value -> the army she sends to this Territory to attack
     */
    private Map<Integer, Army> enemyArmies;

    public Region(int ID, String name, List<Integer> neighbor, int ownerID) {
        this.ID = ID;
        this.name = name;
        this.neighbor = neighbor;
        this.ownerID = ownerID;
        this.army = new Army();
        this.enemyArmies = new HashMap<>();
        this.haveCloak = 0;
    }

    /**
     * Get The ID for this region in the map
     * Do not change it once assigned.
     *
     * @return the ID for this region
     */
    public int getID() {
        return ID;
    }

    /**
     * Get The name of the region, like Beijing
     *
     * @return the name of the region
     */
    public String getName() {
        return name;
    }

    /**
     * get all the neighbors for this region
     * it may, or may not belongs to one owner
     *
     * @return a list of IDs which specify the region
     */
    public List<Integer> getNeighbor() {
        return neighbor;
    }

    /**
     * Get the ID for current owner
     *
     * @return current owner ID
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * Set new Owner Id for this region
     * This usually happens after one attack
     *
     * @param ownerID new OwnerID for this region
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Current Army occupied in this place
     * @return the army in this place
     */
    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    /**
     * The mapping for this enemy army
     *
     * @return the mapping entity
     */
    public Map<Integer, Army> getEnemyArmies() {
        return enemyArmies;
    }

    /**
     * This is used to solve the attack phase and to set the final
     * ownership
     */
    public void solveAttacksAndSetOwner(){
        int currentOwnerID = this.getOwnerID();
        int attackerID = -1;
        Army attack   = null;
        Army defender = null;
        int index = 0;

        while(index != enemyArmies.size()){
            for(Integer ID : enemyArmies.keySet()){
                if(enemyArmies.get(ID).getUnits().size() == 0)
                    continue;

                attackerID = ID;
                attack = enemyArmies.get(attackerID);
                defender = this.getArmy();

                //boolean res = winByDice(attack, defender);
                boolean res = winByDice(attack, defender);
                index++;
                System.out.println("battle between attacker " + attackerID + " defender ID " + currentOwnerID);
                System.out.println("attacker has " + attack.getUnits().size() + "defender has " + defender.getUnits().size());
                if(res) {
                    System.out.println("attack win, the id is" + attackerID);
                    currentOwnerID = attackerID;
                    this.setOwnerID(currentOwnerID);
                    this.setArmy(attack);
                }else{
                    System.out.println("defender win, the id is " + currentOwnerID);
                    this.setOwnerID(currentOwnerID);
                    this.setArmy(defender);
                }
            }
        }

        enemyArmies.clear();
    }


    /**
     * to decide final result for two armies
     *
     * @return true if army1 wins, false if army2 win
     */
    public boolean winByDice(Army atk, Army def){
       int units1 = atk.getUnits().size();
       int units2 = def.getUnits().size();
       boolean flag = true;
       if(units1 == 0 || units2 == 0){
            return units2 == 0 ;
       }
        System.out.println(units1 + "----" +units2);

       while(true){
           int res1 = Dice.rollDice()+getBonus(flag ? atk.getHighestLevel() : atk.getLowestLevel());
           int res2 = Dice.rollDice()+getBonus(flag ? def.getLowestLevel() : def.getHighestLevel());
           System.out.println("round -> ");
           if(res1 > res2){
               def.removeUnits(1,flag?def.getLowestLevel(): def.getHighestLevel());
               units2--;
           }else{
               atk.removeUnits(1,flag?atk.getHighestLevel():atk.getLowestLevel());
               units1--;
           }
           if(units1 == 0 || units2 == 0){
               return units2 == 0;
           }
           flag=!flag;
       }
    }

    /**
     * get the bonus of the unit in a battle
     * @param level the level of the unit
     */
    public int getBonus(int level){
        Map<Integer, Integer> table = new HashMap<>();
        table.put(0,0);
        table.put(1,1);
        table.put(2,3);
        table.put(3,5);
        table.put(4,8);
        table.put(5,11);
        table.put(6,15);
        return table.get(level);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Region region = (Region) o;
        return region.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
