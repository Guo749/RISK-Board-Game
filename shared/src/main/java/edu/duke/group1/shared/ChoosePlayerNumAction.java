package edu.duke.group1.shared;



/**
 * This class is the action to choose the playernum of a game
 * playernum is between 2 and 5
 */
public class ChoosePlayerNumAction extends Action {
    private int playerNum;

    public ChoosePlayerNumAction(int playerNum){
        this.playerNum = playerNum;
    }

    @Override
    public void doAction(AbstractMap map) { }

    public int getPlayerNum() {
        return playerNum;
    }

    @Override
    public String getString(){
        return "";
    }
}
