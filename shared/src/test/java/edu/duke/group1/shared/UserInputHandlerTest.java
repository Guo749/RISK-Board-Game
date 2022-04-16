package edu.duke.group1.shared;


import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.StringReader;

public class UserInputHandlerTest {
    @Test
    public void test_chooseNumber(){
        String str = "fjiaef\nafjioa\naojfioae\n3\n";
        int num = UserInputHandler.chooseNumberForGame(new BufferedReader(new StringReader(str)), "input the num");
        assert(num == 3);
    }

    @Test
    public void test_place(){
        String str = "gioers hsefg\nbeijing 232\n";
        Place place = UserInputHandler.getPlacementFromUser(new BufferedReader(new StringReader(str)), "", 1);

        assert(place.getRegionName().equals("Beijing"));
        assert(place.getUnitNum() == 232);

        String str1 = "432 42342\nfe 323\n";
        Place place1 = UserInputHandler.getPlacementFromUser(new BufferedReader(new StringReader(str1)), "", 1);

        assert(place1.getRegionName().equals("432"));
        assert(place1.getUnitNum() == 42342);
    }


    @Test
    public void test_moveFromUser(){
        String str = "bEijiNg ShanGHAi 23\n";
        Move move = UserInputHandler.getMoveFromUser(new BufferedReader(new StringReader(str)), "", 1);

        assert(move.getSrcRegion().equals("Beijing"));
        assert(move.getDstRegion().equals("Shanghai"));
        assert(move.getUnitNum() == 23);

        String str1 = "ajf feaioj fe as\nBEiJing ShAngHai 55\n";
        Move move1 = UserInputHandler.getMoveFromUser(new BufferedReader(new StringReader(str1)), " ", 1);

        assert(move1.getSrcRegion().equals("Beijing"));
        assert(move1.getDstRegion().equals("Shanghai"));
        assert(move1.getUnitNum() == 55);
    }

    @Test
    public void test_attackFromUser(){
        String str = "bEijiNg ShanGHAi 23\n";
        Attack attack = UserInputHandler.getAttackFromUser(new BufferedReader(new StringReader(str)), "", 1);

        assert(attack.getSrcRegion().equals("Beijing"));
        assert(attack.getDstRegion().equals("Shanghai"));
        assert(attack.getUnitNum() == 23);

        String str1 = "ajf feaioj fe 78\nBEiJing ShAngHai 55\n";
        Attack attack1 = UserInputHandler.getAttackFromUser(new BufferedReader(new StringReader(str1)), " ", 1);

        assert(attack1.getSrcRegion().equals("Beijing"));
        assert(attack1.getDstRegion().equals("Shanghai"));
        assert(attack1.getUnitNum() == 55);
    }

    @Test
    public void test_(){
        String suzhou = UserInputHandler.getCamelCase("suzhou");
        System.out.println(suzhou.equals("Suzhou"));
    }

    @Test
    public void test_getChoiceFromUser(){
        String choice1 = "a";
        String choice2 = "guygu\nf\n";
        String choice3 = "m";
        BufferedReader br = new BufferedReader(new StringReader(choice1));

        UserInputHandler.getChoiceFromUser(br,"test");

        assert(UserInputHandler.getChoiceFromUser(new BufferedReader(new StringReader(choice1)),"test").equals("A"));
        assert(UserInputHandler.getChoiceFromUser(new BufferedReader(new StringReader(choice2)),"test").equals("F"));
        assert(UserInputHandler.getChoiceFromUser(new BufferedReader(new StringReader(choice3)),"test").equals("M"));

    }
}