package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelperOtherTest {

    //@Test
    public void test_getMapNum(){
        PlayerInfo info1 = new PlayerInfo(1);

        info1.setMap(new MapForTwo());
        assert(Helper.initializeMapNum(info1) == 2);

        info1.setMap(new MapForThree());
        assert(Helper.initializeMapNum(info1) == 3);

        info1.setMap(new MapForFour());
        assert(Helper.initializeMapNum(info1) == 4);

        info1.setMap(new MapForFive());
        assert(Helper.initializeMapNum(info1) == 5);

        info1.setMap(new AbstractMap() {
            @Override
            protected void setUpLookup() {

            }

            @Override
            protected void setUpPlayerList() {

            }

            @Override
            protected void setUpRegions() {

            }

            @Override
            protected void setUpResource() {

            }

            @Override
            protected void setUpSpy() {

            }
        });
        assertThrows(IllegalArgumentException.class, () -> Helper.initializeMapNum(info1));
    }

    //@Test
    public void testMyRegion(){
        PlayerInfo info = new PlayerInfo(1);
        info.setMap(new MapForFive());
        info.getMap().addPlayerMapping(1,1);

        List<Region> myRegions = Helper.getMyRegions(info);

        Helper.getEnemyRegion(info);

        Helper.getUnitsNum(info);
    }

    //@Test
    public void test_Num(){
        assert(Helper.parseInt("123") == 123);
        assert(Helper.parseInt("3") == 3);
        assert(Helper.parseInt("15") == 15);
        assert(Helper.parseInt(".15") == Integer.MAX_VALUE);
        assert(Helper.parseInt("15.") == Integer.MAX_VALUE);
        assert(Helper.parseInt(null) == Integer.MAX_VALUE);
    }

    //@Test
    public void test_helper(){
        Helper.getChooseNumAction("Create Map2");
        Helper.getChooseNumAction("Create Map3");
        Helper.getChooseNumAction("Create Map4");
        Helper.getChooseNumAction("Create Map5");
    }

}
