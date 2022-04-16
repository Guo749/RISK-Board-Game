package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Map5Test {
    @Test
    public void test_map5(){
        AbstractMap map5 =  new MapForFive();
        assert(!map5.isConnectedForMove(1,5));
        assert(map5.isConnectedForMove(7,8));
        assert(map5.isConnectedForMove(8,7));

        assert (!map5.isConnectedForMove(3,9));
        assert(!map5.isConnectedForAttack(2,6));


        assert (map5.isConnectedForAttack(6,7));
    }

    @Test
    public void test_getRegions(){
        AbstractMap map = new MapForFive();
        List<Region> regions = map.getRegions();

        Map<String, Region> regionFinder = map.regionFinder;
        for(Region region : regions){
            assert(regionFinder.containsKey(region.getName()));
        }
    }
}
