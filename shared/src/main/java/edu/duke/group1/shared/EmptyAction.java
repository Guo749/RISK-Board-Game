package edu.duke.group1.shared;


/**
 * This class is an action which the player does nothing
 */
public class EmptyAction extends Action {

    public EmptyAction(){
    }

    @Override
    public void doAction(AbstractMap map) {

    }

    @Override
    public String getString(){
        return "";
    }

}