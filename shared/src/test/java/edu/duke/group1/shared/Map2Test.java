package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;

public class Map2Test {
    @Test
    public void test_Connected(){
        AbstractMap map = new MapForTwo();
        map.isConnectedForMove(1,6);
        assert (map.isConnectedForAttack(1,6));
        assert (map.isConnectedForAttack(2,5));
        assert (map.isConnectedForAttack(3,4));
        assert (map.isConnectedForMove(1,2));
        assert (!map.isConnectedForAttack(1,5));
        assert (!map.isConnectedForAttack(2,4));
    }

    @Test
    public void test_getRegions(){
        AbstractMap map = new MapForTwo();
        List<Region> regions = map.getRegions();

        Map<String, Region> regionFinder = map.regionFinder;
        for(Region region : regions){
            assert(regionFinder.containsKey(region.getName()));
        }
    }


}
