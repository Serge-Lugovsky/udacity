package Base;

import Managers.AppManager;
import Managers.SingletonAppManager;
import org.testng.Assert;
import org.testng.annotations.*;

import static Utils.AllureEnvironmentWriter.environmentWriter;
//import static Utils.CopyDirectories.*;


public class TestBase{
    public AppManager app = SingletonAppManager.getInstance().manager;

    @BeforeSuite(alwaysRun = true, description = "Write allure environment file.")
    public void writeEnvironmentForAllure(){
        environmentWriter();
    }

    @BeforeMethod(alwaysRun = true, description = "Login")
    public void login(){
        app.getUserHelper().popUpClose();
        app.getNavigationHelper().goLoginPage();
        app.getUserHelper().loginAs(app.getUser());
        Assert.assertTrue(app.getUserHelper().verifyAuth());
    }

    @AfterMethod(alwaysRun = true, description = "Logout")
    public void logOut(){
        app.getUserHelper().logOutFromAccount();
        Assert.assertTrue(app.getUserHelper().verifyLogOut());
    }

    @AfterSuite(alwaysRun = true, description = "Close browser and make allure report")
    public void tearDown(){
        app.getDriver().quit();
// ==================|For local use|====================
//        makeAllureReport();
//        copyResultsDir();
//        copyHistoryDir();
    }

}