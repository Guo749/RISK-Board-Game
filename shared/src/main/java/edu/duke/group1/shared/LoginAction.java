package edu.duke.group1.shared;

/**
 * This class is the action to login
 * client will provide account and password
 */

public class LoginAction extends Action {
    private String account;
    private String password;
    public LoginAction(String account, String password){
        this.account = account;
        this.password = password;
    }

    @Override
    public void doAction(AbstractMap map) { }

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String getString(){
        return "";
    }


}
