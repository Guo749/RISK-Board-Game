package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

public class PlaceTest {
    @Test
    public void test_place(){
        AbstractMap map = new MapForTwo();
        Action place1 = new Place(1,"Beijing",0,1);
        Place place2 = new Place(1,"Shanghai",10,1);
        place1.doAction(map);
        place2.doAction(map);
        assert(place2.getLevel()==1);
        assert(map.getRegionFinder().get("Shanghai").getArmy().getUnits().size()==10);
        assert (map.getRegionFinder().get("Beijing").getArmy().getUnits().size()==0);
        place1.getString();
}
}
