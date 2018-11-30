import Managers.AppManager;
import Managers.SingletonAppManager;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;


public class TestBase{
    AppManager app = SingletonAppManager.getInstance().manager;

    @AfterSuite
    public void lodOut(){
        app.getAccountHelper().logOutFromAccount();
        Assert.assertTrue(app.getAccountHelper().verifyLogOut());
        System.out.println("LOGOUT SUCCESS!");
        app.getDriver().quit();
    }

}
