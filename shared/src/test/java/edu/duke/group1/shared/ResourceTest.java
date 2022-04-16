package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

public class ResourceTest {
    @Test
    public void test_consume(){
        Resource r = new Resource(0,0,0);
        r.setResource(10,10,10);
        r.setTechLevel(3);
        r.addMoney(5);
        r.addFood(5);
        r.consumedMoney(2);
        r.consumedFood(2);
        assert(r.getTechLevel()==3);
        assert(r.getFood()==13);
        assert(r.getMoney()==13);
    }
}
