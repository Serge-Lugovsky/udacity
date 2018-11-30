package Managers;

public class AccountHelper extends PageManager {

    public AccountHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void moveToUserInfo(){
        accountPage
                .clickSettingsButton()
                .clickPersonalInfoLink();
    }

    public void logOutFromAccount(){
        accountPage.logOutAccount();
    }

    public boolean verifyLogOut(){
        return accountPage.verifyLogOut();
    }

    public String getFirstName(){
        return accountPage.getUserFirstName();
    }

    public String getLastName(){
        return accountPage.getUserLastName();
    }

    public String getEmail(){
        return accountPage.getUserEmailAddress();
    }
}
