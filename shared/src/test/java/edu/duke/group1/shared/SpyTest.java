package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

public class SpyTest {
    @Test
    public void test_spy(){
        Spy s = new Spy();
        s.addSpy("qw98",5);
        s.addSpy("Beijing",6);
        s.removeSpy("qw98",3);
        s.removeSpy("Beijing",1);
        assert(s.getSpyLocation().get("qw98")==2);
        assert(s.getSpyLocation().get("Beijing")==5);
    }
}
