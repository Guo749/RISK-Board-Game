package edu.duke.group1.shared;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

/**
 * This is the tool class
 * used to handle anything related to read input from user
 * then convert it into integer
 */
public class UserInputHandler {

    /**
     * used to get input for how many people should be in this room
     * @param reader the source to read
     * @param hint the hint info
     * @return number between 2 ~ 5
     */
    public static int chooseNumberForGame(BufferedReader reader, String hint){
        int num;
        while(true){
            try {
                System.out.println(hint);
                num = Integer.parseInt(reader.readLine());
                if(2 <= num && num <= 5){
                    return num;
                }
            } catch (Exception e) {
                System.out.println(hint);
            }
        }
    }

    /**
     * This is used to get the input from the user for placement
     * string format regionName + NUm, like Beijing 15
     *
     * @param reader the source to read from
     * @param hint the hint information to give to users
     * @param playerID the ID for this player
     * @return the place action
     */
    public static Place getPlacementFromUser(BufferedReader reader, String  hint, int playerID){
        Place place = null;
        while(true){
            try {
                System.out.println(hint);
                String str = reader.readLine();
                String[] split = str.split(" ");
                if(split.length == 2){
                    place = new Place(playerID, getCamelCase(split[0]), Integer.parseInt(split[1]), 0);
                    return place;
                }
            } catch (Exception e) {
                System.out.println(hint);
            }
        }

    }

    /**
     * This is used to get the input from the user for choice
     * string format Choice, it's a single character
     *
     * @param reader the source to read from
     * @param hint the hint information to give to users
     * @return the action, it's either m, a or f
     */
    public static String getChoiceFromUser(BufferedReader reader, String hint){
        while(true){
            try{
                System.out.println(hint);
                String str = reader.readLine();
                char choice = str.charAt(0);
                if(str.length() == 1){
                    if(choice == 'M' || choice == 'm'){
                        return "M";
                    }else if(choice == 'A' || choice == 'a'){
                        return "A";
                    }else if(choice == 'F' || choice == 'f'){
                        return "F";
                    }
                }
            } catch (Exception e) {
                System.out.println(hint);
            }
        }
    }

    /**
     * This is used to get the input from Move action
     *
     * @param reader
     * @param hint
     * @param playerID
     * @return the move entity for this action
     */
    public static Move getMoveFromUser(BufferedReader reader, String hint, int playerID){
        Move move = null;
        while(true){
            try{
                System.out.println(hint);
                String str = reader.readLine();
                String[] split = str.split(" ");
                if(split.length == 3){
                    move = new Move(playerID, getCamelCase(split[0]), getCamelCase(split[1]), Integer.parseInt(split[2]), 1);
                    return move;
                }
            } catch (Exception e) {
                System.out.println(hint);
            }
        }
    }

    /**
     * This is used to get the input from Move action
     *
     * @param reader
     * @param hint
     * @param playerID
     * @return the move entity for this action
     */
    public static Attack getAttackFromUser(BufferedReader reader, String hint, int playerID){
        Attack attack = null;
        while(true){
            try{
                System.out.println(hint);
                String str = reader.readLine();
                String[] split = str.split(" ");
                if(split.length == 3){
                    attack = new Attack(playerID, getCamelCase(split[0]), getCamelCase(split[1]), Integer.parseInt(split[2]),0);
                    return attack;
                }
            } catch (Exception e) {
                System.out.println(hint);
            }
        }
    }

    /**
     * Get the camel case for the str
     * like bEijiNg -> Beijing
     *
     * @param str the string to convert
     * @return the camel case for this string
     */
    public static String getCamelCase(String str){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(i == 0){
                if(ch >= 97 && ch <= 122){
                    ch -= 32;
                }
            }else{
                if(ch >= 65 && ch <= 90){
                    ch += 32;
                }
            }
            res.append(ch);
        }
        return res.toString();
    }


}
