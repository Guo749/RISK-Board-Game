package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TextViewTest {


    //@Test
    public void test_view(){
        AbstractMap map = new MapForTwo();
        TextView view = new TextView(map);
        map.addPlayerMapping(1,1);
        view.displayByPlayerId(1);
    }

    //@Test
    public void test_view4(){
        AbstractMap map = new MapForThree();
        TextView view = new TextView(map);
        map.addPlayerMapping(3,1);
        view.displayByPlayerId(3);
    }


    // @Test
    public void test_view3(){
        AbstractMap map = new MapForThree();
        TextView view=  new TextView(map);
        map.addPlayerMapping(1, 1);

         Region Hebei = map.getRegionFinder().get("Hebei");
         Map<Integer, Spy> playerSpies = map.getPlayerSpies();
         playerSpies.put(1, new Spy());
         playerSpies.get(1).addSpy("Hebei", 1);


         view.displayByPlayerId(1);
     }

}
