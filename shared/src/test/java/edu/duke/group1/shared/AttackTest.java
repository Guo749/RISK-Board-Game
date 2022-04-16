package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

public class AttackTest {
    @Test
    public void test_attack(){
    AbstractMap map = new MapForTwo();
    map.playerResource.get(1).setResource(20,20,4);
    map.getRegionFinder().get("Beijing").getArmy().addUnits(10,0);
    Attack atk = new Attack(1,"Beijing","Shanghai",3,0);
    Attack atk0 = new Attack(1,"Beijing","Shanghai",2,0);
    atk.doAction(map);
    atk0.doAction(map);
    assert(map.getRegionFinder().get("Shanghai").getEnemyArmies().get(1).getUnits().size()==5);

    AbstractMap map1 = new MapForTwo();
    map1.playerResource.get(1).setResource(40,40,6);
    map1.getRegionFinder().get("Beijing").getArmy().addUnits(10,6);
    map1.getRegionFinder().get("Shanghai").getArmy().addUnits(4,1);
    Attack atk1 = new Attack(1,"Beijing","Shanghai",5,6);
    atk1.doAction(map1);
    assert(map1.getRegionFinder().get("Beijing").getArmy().getUnits().size()==5);
    map1.getRegionFinder().get("Shanghai").solveAttacksAndSetOwner();
    System.out.println(map1.getRegionFinder().get("Shanghai").getOwnerID());
    assert(map1.getRegionFinder().get("Shanghai").getOwnerID()==1);
    atk1.getString();
}
    @Test
    public void test_dice(){
        assert(Dice.rollDice()<20);
        assert (Dice.rollDice()>=0);
    }
}
