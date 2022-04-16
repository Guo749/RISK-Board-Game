package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractMapTest {
    @Test
    public void test_abstract5(){
        AbstractMap map = new MapForFive();
        assert(map.getPlayerList().size() == 5);
        assert(map.getLookup().size() == 10);
        assert(map.getRegionFinder().size() == 10);
    }

    @Test
    public void test_abstract4(){
        AbstractMap map = new MapForFour();
        assert(map.getPlayerList().size() == 4);
        assert(map.getLookup().size() == 8);
        assert(map.getRegionFinder().size() == 8);
    }

    @Test
    public void test_abstract3(){
        AbstractMap map = new MapForThree();
        assert(map.getPlayerList().size() == 3);
        assert(map.getLookup().size() == 9);
        assert(map.getRegionFinder().size() == 9);
    }

    @Test
    public void test_abstract2(){
        AbstractMap map = new MapForTwo();
        assert(map.getPlayerList().size() == 2);
        assert(map.getLookup().size() == 6);
        assert(map.getRegionFinder().size() == 6);
    }

    @Test
    public void test_move_attack_map2(){
        AbstractMap map = new MapForTwo();

        assert(map.isConnectedForMove(1,2));
        assert(map.isConnectedForMove(1,3));
        assert(map.isConnectedForMove(2,3));

        assert(map.isConnectedForMove(4,5));
        assert(map.isConnectedForMove(4,6));
        assert(map.isConnectedForMove(5,6));

        assert(!map.isConnectedForMove(1,6));
        assert(!map.isConnectedForMove(2,5));
        assert(!map.isConnectedForMove(3,4));
        assert(!map.isConnectedForMove(3,6));
        assert(!map.isConnectedForMove(1,5));



        Region region = map.getRegionFinder().get("Guangzhou");
        region.setOwnerID(1);

        assert(map.isConnectedForMove(1, 5));
        assert(map.isConnectedForMove(2, 5));
        assert(map.isConnectedForMove(3, 5));

        assert(!map.isConnectedForMove(6, 5));
        assert(!map.isConnectedForMove(4, 5));
        assert(!map.isConnectedForMove(4, 6));


    }

    @Test
    public void test_move_attack(){
        AbstractMap map = new MapForThree();

        assert (map.isConnectedForMove(1,2));
        assert (map.isConnectedForMove(3,2));
        assert (map.isConnectedForMove(3,1));



        assert(map.isConnectedForMove(4,5));
        assert(map.isConnectedForMove(4,6));
        assert(map.isConnectedForMove(6,5));

        assert(map.isConnectedForMove(7,8));
        assert(map.isConnectedForMove(7,9));
        assert(map.isConnectedForMove(8,9));

        assert (!map.isConnectedForMove(1,4));
        assert (!map.isConnectedForMove(2,5));
        assert (!map.isConnectedForMove(3,6));

        assert (!map.isConnectedForMove(3,7));
        assert (!map.isConnectedForMove(2,8));
        assert (!map.isConnectedForMove(4,9));


        Region Yangzhou = map.regionFinder.get("Yangzhou");
    }
    @Test
    public void test_getMapping(){
        AbstractMap map = new MapForTwo();
        assert(map.getPlayerList().size()==2);
        assert(map.getLookup().get(1).equals("Beijing"));
        Map<Integer,Integer> playerMappings;
        map.addPlayerMapping(1,1);
        assert(map.getPlayerMapping(1)==1);
        map.addOneUnit();
        assert(map.getRegions().get(0).getArmy().getUnits().size()==1);
        map.solveAttacksAndSetRegionOwner(null);
        map.updateUnplacedUnitsMapping(1,5);
        assert(map.getUnplacedUnitsMapping(1)==5);
        Map<Integer, Resource> r1 = new HashMap<>();
        map.setPlayerResource(r1);
        AbstractMap map1 = new MapForTwo();
        map1.addOneResource();
        assert(map1.getPlayerResource().get(1).getMoney()==300);
        assert(map1.getPlayerResource().get(2).getFood()==1500);
    }
    @Test
    public void test_win(){
        AbstractMap map = new MapForTwo();
        map.updatePlayerWinList();
        assert(map.playerWinList.get(0)==2);
        assert(map.getWinner()==-1);
        map.getRegionFinder().get("Shanghai").setOwnerID(1);
        map.getRegionFinder().get("Guangzhou").setOwnerID(1);
        map.getRegionFinder().get("Kunming").setOwnerID(1);
        map.updatePlayerWinList();
        assert(map.getPlayerWin(0)==0);
        assert(map.getPlayerWin(1)==1);
        assert(map.getWinner()==1);
    }

}