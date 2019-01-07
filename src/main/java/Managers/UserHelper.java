package Managers;

import Models.User;

public class UserHelper extends PageManager {

    protected UserHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void popUpClose(){
        homePage.popUpClose();
    }

    public void loginAs(User user){
        loginPage
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .signInClick();
    }

    public boolean verifyAuth() {
        return accountPage.verifyLogin();
    }

    public void logOutFromAccount(){
        accountPage.logOutAccount();
    }

    public boolean verifyLogOut(){
        return accountPage.verifyLogOut();
    }

}
