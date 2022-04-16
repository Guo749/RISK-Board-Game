package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Map4Test {
    @Test
    public void test_map4(){
        AbstractMap map4 = new MapForFour();

        assert(map4.isConnectedForAttack(1,8));
        assert(!map4.isConnectedForMove(1,7));

        assert(map4.isConnectedForAttack(2,3));
        assert(map4.isConnectedForMove(4,3));

        assert(!map4.isConnectedForAttack(2,6));
        assert(!map4.isConnectedForMove(3,7));
    }

    @Test
    public void test_getRegions(){
        AbstractMap map = new MapForFour();
        List<Region> regions = map.getRegions();

        Map<String, Region> regionFinder = map.regionFinder;
        for(Region region : regions){
            assert(regionFinder.containsKey(region.getName()));
        }
    }
}
