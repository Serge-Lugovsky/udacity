package Managers;

import Models.User;

public class UserHelper extends PageManager {

    public UserHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void loginAs(User user){
        homePage
                .popUpClose()
                .goToLoginPage();
        loginPage.inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .signInClick();
    }

    public boolean verifyAuth() {
        return accountPage.verifyAuth();
    }


}
