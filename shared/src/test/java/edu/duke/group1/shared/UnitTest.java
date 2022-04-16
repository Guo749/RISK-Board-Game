package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UnitTest {
    @Test
    public void test_getAndSet(){
        Unit unit = new Unit("Ant", 1);

        assertSame("Ant",unit.getName());
        assertSame(unit.getLevel(), 1);

        unit.setName("guoguo");
        unit.setLevel(5);

        assertThrows(IllegalArgumentException.class, () -> unit.setLevel(-1));
        assertThrows(IllegalArgumentException.class, () -> new Unit("guugo",-1));

    }
}
