package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {
    @Test
    public void test_getAndSetUnits(){
        Army army = new Army();
        List<Unit> units = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            units.add(new Unit("" + i, 0));
        }
        army.setUnits(units);
        army.addUnits(5,0);
        assert(army.getUnits().size() == 15);

        Army army1 = new Army(units);

        assertThrows(IllegalArgumentException.class, () -> army.removeUnits(16,0));

        army.removeUnits(5,0);
        assert(army.getUnits().size() == 10);
    }
}
