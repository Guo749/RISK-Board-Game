package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;

public class RegionTest {
    @Test
    public void test_getAndSet(){
        AbstractMap map4 = new MapForFour();
        Map<String, Region> regionFinder = map4.getRegionFinder();

        Region Hainan = regionFinder.get("Hainan");
        Hainan.setOwnerID(5);
        assert(Hainan.getOwnerID() == 5);
        int id = Hainan.getID();
        assert(Hainan.getName().equals("Hainan"));
        List<Integer> neighbor = Hainan.getNeighbor();
        assert (neighbor.size() == 2);
        assert(neighbor.contains(1));
        assert(neighbor.contains(7));
    }

    @Test
    public void test_winByDice(){
        Region region = new Region(1, "", null, 1);
        Army army1 = new Army();
        army1.setUnits(50,0);
        Army army2 = new Army();
        System.out.println(region.winByDice(army1, army2));

        Army army3 = new Army();
        Army army4 = new Army();
        army3.setUnits(15,0);
        army4.setUnits(20,0);

        System.out.println(region.winByDice(army3, army4));
    }

    @Test
    public void test_solveAttacksAndSetOwner(){
        Region region = new Region(1, "", null, 1);

        Army arm1 = new Army();
        arm1.setUnits(10,0);
        region.setArmy(arm1);

        Army arm2 = new Army();
        arm2.setUnits(10,0);
        Army arm3 = new Army();
        arm3.setUnits(10,0);
        Army arm4 = new Army();
        arm4.setUnits(10,0);
        Army arm5 = new Army();
        arm5.setUnits(10,0);

        Map<Integer, Army> enemyArmies = region.getEnemyArmies();
        enemyArmies.put(2, arm2);
        enemyArmies.put(3, arm3);
        enemyArmies.put(4, arm4);
        enemyArmies.put(5, arm5);

        System.out.println("before the owner is " + region.getOwnerID());
        region.solveAttacksAndSetOwner();
        System.out.println("after the owner is " + region.getOwnerID());

        //System.out.println(enemyArmies);
    }

    @Test
    public void test_equals(){
        Region region1 = new Region(1, "qw98", null, 1);
        Region region2 = new Region(2, "qw98", null, 1);
        Region region3 = new Region(3, "qq", null, 1);
        assert(region1.equals(region2));
        assert(region1.equals(region1));
        assert(!region1.equals(region3));
    }
    @Test
    public void test_string(){
        Region region1 = new Region(1, "qw98", null, 1);
        Region region2 = new Region(2, "qw98", null, 1);
        Region region3 = new Region(3, "qq", null, 1);
        assert(region1.toString().equals(region2.toString()));
        assert(region1.toString().equals(region1.toString()));
        assert(!region1.toString().equals(region3.toString()));
    }
    @Test
    public void test_hash(){
        Region region1 = new Region(1, "qw98", null, 1);
        Region region2 = new Region(2, "qw98", null, 1);
        Region region3 = new Region(3, "qq", null, 1);
        assert(region1.hashCode()==region2.hashCode());
        assert(region1.hashCode()!=region3.hashCode());
    }




}
