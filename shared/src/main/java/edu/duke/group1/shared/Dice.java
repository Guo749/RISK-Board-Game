package edu.duke.group1.shared;


import java.util.Random;


public class Dice {
    public static int rollDice(){
        Random random = new Random();
        int DiceVal = random.nextInt(20); // random integer in [0,20)
        return DiceVal;
    }
//
//    public static void main(String[] args) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int i = 0; i < 500; i++){
//            int num = rollDice();
//            map.put(num , map.getOrDefault(num, 0) + 1);
//        }
//        System.out.println(map);
//    }
}
