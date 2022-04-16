package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

public class MoveTest {
    @Test
    public void test_move(){
        AbstractMap map = new MapForTwo();
        map.playerResource.get(1).setResource(600,20,4);
        map.getRegionFinder().get("Beijing").getArmy().setUnits(10,0);
        map.getRegionFinder().get("Chengdu").getArmy().setUnits(10,0);
        Move move = new Move(1,"Beijing","Chengdu",5,0);
        move.doAction(map);
        assert(move.getLevel()==0);
        assert(map.getRegionFinder().get("Chengdu").getArmy().getUnits().size()==15);
        assert (map.getRegionFinder().get("Beijing").getArmy().getUnits().size()==5);
    }
    @Test
    public void test_nearest(){
        AbstractMap map = new MapForThree();
        map.playerResource.get(1).setResource(600,20,4);
        map.getRegionFinder().get("Beijing").getArmy().setUnits(10,0);
        Move move = new Move(1,"Beijing","Guangzhou",5,0);
        System.out.println(move.getShortestSizeOfRoute(map));
        assert(move.getShortestSizeOfRoute(map)==50);


        AbstractMap map1 = new MapForTwo();
        map1.playerResource.get(1).setResource(600,20,4);
        map1.getRegionFinder().get("Beijing").getArmy().setUnits(10,0);
        map1.getRegionFinder().get("Shanghai").setOwnerID(1);
        map1.getRegionFinder().get("Guangzhou").setOwnerID(1);
        map1.getRegionFinder().get("Kunming").setOwnerID(1);

        Move move1 = new Move(1,"Beijing","Suzhou",5,0);
        System.out.println(move1.getShortestSizeOfRoute(map1));
        assert(move1.getShortestSizeOfRoute(map1)==100);

        AbstractMap map2 = new MapForThree();
        map2.playerResource.get(1).setResource(1249,20,4);
        map2.getRegionFinder().get("Beijing").getArmy().setUnits(10,0);
        map2.getRegionFinder().get("Guangzhou").getArmy().setUnits(10,0);
        map2.getRegionFinder().get("Suzhou").getArmy().setUnits(10,0);
        map2.getRegionFinder().get("Hangzhou").setOwnerID(1);
        map2.getRegionFinder().get("Yangzhou").setOwnerID(1);
        map2.getRegionFinder().get("Suzhou").setOwnerID(1);
        map2.getRegionFinder().get("Shanxi").setOwnerID(1);
        map2.getRegionFinder().get("Henan").setOwnerID(1);
        map2.getRegionFinder().get("Hebei").setOwnerID(1);

        assert(map2.isConnectedForMove(map2.regionFinder.get("Guangzhou").getID(),map2.regionFinder.get("Hangzhou").getID()));

        Move move2 = new Move(1,"Guangzhou","Hangzhou",5,0);
        System.out.println(move2.getShortestSizeOfRoute(map2));
        assert(move2.getShortestSizeOfRoute(map2)==100);
        move2.doAction(map2);
        Move move3 = new Move(1,"Suzhou","Shanghai",5,0);
        System.out.println(move3.getShortestSizeOfRoute(map2));
        assert(move3.getShortestSizeOfRoute(map2)==150);
        move3.doAction(map2);
        System.out.println(map2.playerResource.get(1).getFood());
        assert(map2.playerResource.get(1).getFood()==749);
        move3.getString();
    }


}
