package edu.duke.group1.shared;

import java.io.Serializable;

public class Resource implements Serializable {
    /* the food for the player */
    private int food;
    /* the money for the player */
    private int money;
    /* the tech-level for the player */
    private int techLevel;
    /* the maximum tech-level for the player */
    public static final int MAX_TECH = 6;



    public Resource(int food, int money, int techLevel) {
        this.food = food;
        this.money = money;
        this.techLevel = techLevel;
    }

    /**
     * Used to consume the food, and return false if is not valid
     * @param foodResource the resource need to be consumed
     */
    public boolean consumedFood(int foodResource){
        if(foodResource > this.food) {
            return false;
        }else{
            this.food-=foodResource;
        }
        return true;
    }
    /**
     * Used to consume the money, and return false if is not valid
     * @param moneyResource the resource need to be consumed
     */

    public boolean consumedMoney(int moneyResource){
        if(moneyResource>this.money){
            return false;
        }else{
            this.money-=moneyResource;
        }
        return true;
    }
    public void addFood(int foodResource){
        this.food+=foodResource;
    }
    public void addMoney(int moneyResource){
        this.money+=moneyResource;
    }
    public int getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
    public void setResource(int food, int money, int tech){
        this.food = food;
        this.money =money;
        this.techLevel = tech;
    }

    public int getFood() {
        return food;
    }

    public int getMoney() {
        return money;
    }
}
