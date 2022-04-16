package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

public class UpgradeActionTest {
    @Test
    public void test_upgradeAction(){
        AbstractMap map = new MapForTwo();
        map.playerResource.get(1).addMoney(1000);
        map.getRegionFinder().get("Beijing").getArmy().addUnits(5,0);
        map.getRegionFinder().get("Beijing").getArmy().addUnits(5,2);
        UpgradeAction u = new UpgradeAction(1,"Beijing",2,0,1);
        map.getRegionFinder().get("Beijing").getArmy().addUnits(5, 0);
        u.doAction(map);
        assert(u.getPreLevel()==0);
        assert(u.getNextLevel()==1);
        assert(u.getPlayerId()==1);
        assert(u.getUnitNum()==2);
        assert(u.getRegionName().equals("Beijing"));
        assert(u.calUsedMoneyForUnits()==6);
        u.getString();
        UpgradeAction u1 = new UpgradeAction(1,"Beijing",4,2,4,true,false);
        u1.doAction(map);
        assert(map.getPlayerSpies().get(1).getSpyLocation().get("Beijing")==4);
        UpgradeAction u2 = new UpgradeAction(1,"Beijing",4,2,4,false,true);
        u2.doAction(map);
        assert(map.regionFinder.get("Beijing").haveCloak==3);
    }
}
