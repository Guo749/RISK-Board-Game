package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

public class UpgradeTechActionTest {
    @Test
    public void test_upgradeTechAction() {
        AbstractMap map = new MapForTwo();
        UpgradeTechAction u = new UpgradeTechAction(1);
        assert(!u.upgradeTech(map));
        map.playerResource.get(1).addMoney(1000);
        u.doAction(map);
        assert(map.getPlayerResource().get(1).getTechLevel()==2);
        u.doAction(map);
        u.doAction(map);
        u.doAction(map);
        u.doAction(map);
        assert(map.getPlayerResource().get(1).getTechLevel()==Resource.MAX_TECH);
        u.doAction(map);
        u.getString();
    }
}