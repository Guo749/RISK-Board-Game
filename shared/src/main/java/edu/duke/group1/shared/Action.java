package edu.duke.group1.shared;


import java.io.Serializable;

/**
 * This is the abstract class for all actions
 * adapted using visitor pattern
 * every action will have doAction
 */
public abstract  class Action implements Serializable {
     public abstract void doAction(AbstractMap map);

     public abstract String getString();
}
