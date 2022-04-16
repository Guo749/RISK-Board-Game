package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Map3Test {

    @Test
    public void test_mapForThree(){
        AbstractMap map3 = new MapForThree();

        assert(map3.isConnectedForAttack(1,4));
        assert(!map3.isConnectedForMove(1,7));
        assert(!map3.isConnectedForMove(2,5));
        assert(map3.isConnectedForAttack(3,5));
        assert(map3.isConnectedForAttack(5,7));
        assert(!map3.isConnectedForMove(6,8));
        assert(map3.isConnectedForAttack(4,1));
        assert(!map3.isConnectedForMove(8,5));

    }

    @Test
    public void test_getRegions(){
        AbstractMap map = new MapForThree();
        List<Region> regions = map.getRegions();

        Map<String, Region> regionFinder = map.regionFinder;
        for(Region region : regions){
            assert(regionFinder.containsKey(region.getName()));
        }
    }
}
