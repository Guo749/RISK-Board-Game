package edu.duke.group1.shared;
import org.junit.jupiter.api.Test;

public class RuleCheckerTest {
    @Test
    public void test_placeChecker(){
        AbstractMap map = new MapForTwo();
        Action place1 = new Place(1,"Beijing",0,1);
        Action place2 = new Place(1,"Shanghai",10,1);
        Action place3 = new Place(1,null,5,1);
        Action place5 = new Place(1,"qw98",5,1);
        Action place4 = new Place(1,"Beijing",5,1);
        PlaceChecker p1 =new PlaceChecker();
        assert(p1.checkMyRule(place1,map).equals("Invalid action! The number of units must be greater than 0"));
        assert(p1.checkMyRule(place2,map).equals("Invalid action! You must put units on your own region!"));
        assert (p1.checkMyRule(place3,map).equals("Invalid action! The Region can not be null."));
        assert(p1.checkMyRule(place4,map)==null);
        assert(p1.checkPlaceRule(place2,map,6).equals("Invalid action! The number of units can not be greater than the number you holds"));
        assert(p1.checkMyRule(place5,map).equals("Invalid action! the input region must be in the map"));
    }
    @Test
    public void test_attackChecker(){
        AbstractMap map = new MapForTwo();
        map.playerResource.get(1).addFood(200000);
        Action atk1 = new Attack(1,null,"Shanghai",6,0);
        Action atk2 = new Attack(1,"Beijing","Beijing",6,0);
        Action atk3 = new Attack(1,"Beijing","Shanghai",6,0);
        Action atk4 = new Attack(1,"Chengdu","Beijing",3,0);
        Action atk5 = new Attack(1,"Beijing","Hebei",6,0);
        Action atk6 = new Attack(1,"Beijing","Shanghai",-1,0);
        Action atk7 = new Attack(1,"qw98","Shanghai",6,0);
        Action atk8 = new Attack(1,"Beijing","Shanghai",1,0);
        RuleChecker a1 = new AttackChecker();
        assert(a1.checkMyRule(atk1,map).equals("Invalid action! The Region can not be null"));
        assert(a1.checkMyRule(atk2,map).equals("Invalid action! The destination name can not be same as the source region name"));
        assert(a1.checkMyRule(atk7,map).equals("Invalid action! the input region must be in the map"));
        Action pl = new Place(1,"Beijing",4,1);
        Action pl2 = new Place(1,"Chengdu",4,1);
        pl.doAction(map);
        pl2.doAction(map);
        assert(a1.checkMyRule(atk3,map).equals("Invalid action! The number of units you move must be less than the source region army number!"));
        System.out.println(a1.checkMyRule(atk4, map));
        assert(a1.checkMyRule(atk4,map).equals("Invalid action! The owner of destination and the source region must be different"));
        assert(a1.checkMyRule(atk6, map).equals("Invalid action! The number of units must be greater than 0"));
        AbstractMap map2 = new MapForThree();
        Action pl3 = new Place(1,"Beijing",6,1);
        pl3.doAction(map2);
        assert(a1.checkMyRule(atk5,map2).equals("Invalid action! The destination and the source region must be connected."));
        System.out.println(a1.checkMyRule(atk8, map));
        assert(a1.checkMyRule(atk8,map).equals("Invalid action! The number of the units of the corresponding level must be less than the source region can hold!"));
        map2.getRegionFinder().get("Beijing").getArmy().addUnits(5,0);
        Action atk9 = new Attack(1,"Beijing","Hangzhou",2,0);
        assert(a1.checkMyRule(atk9,map2).equals("Invalid action! The food for attack is not enough!"));
        map2.playerResource.get(1).addFood(200000);
        assert(a1.checkMyRule(atk9,map2)==null);
    }
    @Test
    public void test_moveChecker(){
        AbstractMap map = new MapForTwo();
        Action mov1 = new Move(1,"Beijing","Chengdu",7,1);
        Action mov2 = new Move(1,null,"Chengdu",5,1);
        Action mov3 = new Move(1,"Beijing","Beijing",5,1);
        Action mov4 = new Move(1,"Beijing","Shanghai",5,1);
        Action mov5 = new Move(1,"Beijing","Chengdu",-5,1);
        Action mov6 = new Move(1,"lala","lala2",5,1);
        Action mov7 = new Move(1,"Shanghai","Guangzhou",5,1);
        Action mov8 = new Move(1,"Beijing","Chengdu",2,1);
        RuleChecker m1 = new MoveChecker();
        Action pl = new Place(1,"Beijing",6,1);
        Action pl2 = new Place(2,"Shanghai",10,1);
        pl.doAction(map);
        pl2.doAction(map);
        assert(m1.checkMyRule(mov1,map).equals("Invalid action! The number of units you move must be less than the source region army number!"));
        assert(m1.checkMyRule(mov2,map).equals("Invalid action! The Region can not be null."));
        assert(m1.checkMyRule(mov3,map).equals("Invalid action! The destination name can not be same as the source region name."));
        System.out.println(m1.checkMyRule(mov4,map));
        assert (m1.checkMyRule(mov6,map).equals("Invalid action! the input region must be in the map"));
        assert(m1.checkMyRule(mov4,map).equals("Invalid action! The destination and the source region must belong to same player"));
        assert(m1.checkMyRule(mov5,map).equals("Invalid action! The number of units must be greater than 0."));
        assert(m1.checkMyRule(mov7,map).equals("Invalid action! You must move units on your own region!"));
        assert(m1.checkMyRule(mov8,map).equals("You do not have enough food to move!"));
        map.playerResource.get(1).addFood(200000);
        assert(m1.checkMyRule(mov8,map)==null);
        Action mov9 = new Move(1,"Beijing","Chengdu",2,2);
        assert(m1.checkMyRule(mov9,map).equals("Invalid action! The number of the units in the corresponding level must be less than the source region can hold!"));
        AbstractMap map2 = new MapForTwo();
        map2.playerResource.get(1).addFood(200000);
        map2.playerResource.get(1).addMoney(20000);
        Action pl3 = new Place(1,"Beijing",6,2);
        pl3.doAction(map2);
        Action u = new UpgradeAction(1,"Beijing",5,2,4,true,false);
        u.doAction(map2);
        Action mov10 = new Move(1,"Beijing","Shanghai",3,6,true);
        System.out.println(m1.checkMyRule(mov10, map2));
        assert(m1.checkMyRule(mov10,map2)==null);
        mov10.doAction(map2);
        assert(map2.getPlayerSpies().get(1).getSpyLocation().get("Shanghai").equals(3));
        Action mov11 = new Move(1,"Chengdu","Beijing",2,3,true);
        assert(m1.checkMyRule(mov11,map2).equals("You do not have spy at this region!"));
        Action mov12 = new Move(1,"Beijing","Guangzhou",2,3,true);
        assert(m1.checkMyRule(mov12,map2).equals("Invalid action! The move of spy must across one region once a time!"));
        Action mov13 = new Move(1,"Beijing","Shanghai",5,3,true);
        assert(m1.checkMyRule(mov13,map2).equals("Invalid action! The spy number is not enough for move"));
    }
    @Test
    public void test_upgradeChecker(){
        AbstractMap map = new MapForTwo();
        map.getRegionFinder().get("Beijing").getArmy().addUnits(6,0);
        map.getRegionFinder().get("Shanghai").getArmy().addUnits(6,0);
        Action upgrade1 = new UpgradeAction(1,"Beijing",5,0,1);
        RuleChecker u1 = new UpgradeActionChecker();
        assert(u1.checkMyRule(upgrade1,map).equals("You do not have enough money to upgrade your units"));
        Action upgrade2 = new UpgradeAction(1,"Beijing",5,0,7);
        assert(u1.checkMyRule(upgrade2,map).equals("Invalid action! Unit level must be in the range [0,6]!"));
        Action upgrade3 = new UpgradeAction(1,"Beijing",7,0,1);
        assert(u1.checkMyRule(upgrade3,map).equals("Invalid action! the units number in your region is not enough!"));
        map.playerResource.get(1).addMoney(10000);
        Action upgrade4 = new UpgradeAction(1,"Shanghai",5,0,1);
        assert(u1.checkMyRule(upgrade4,map).equals("Invalid action! You must put units on your own region!"));
        Action upgrade5 = new UpgradeAction(1,"qqww9988",5,0,1);
        assert(u1.checkMyRule(upgrade5,map).equals("Invalid action! the input region must be in the map"));
        assert(u1.checkMyRule(upgrade1,map)==null);
        Action upgrade6 = new UpgradeAction(1,"Beijing",2,0,6,true,false);
        assert(u1.checkMyRule(upgrade6,map).equals("Invalid action! invalid spy upgrade!"));
    }


}
