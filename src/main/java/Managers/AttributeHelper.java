package Managers;

public class AttributeHelper extends PageManager {

    public AttributeHelper(AppManager manager){
        super(manager.getDriver());
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
