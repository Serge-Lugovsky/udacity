import Managers.AppManager;
import Managers.SingletonAppManager;
import org.testng.Assert;
import org.testng.annotations.*;

import static Utils.CopyDirectories.copyResultAndHistoryDirs;
import static Utils.CopyDirectories.makeAllureReport;


public class TestBase{
    AppManager app = SingletonAppManager.getInstance().manager;

    @BeforeMethod(alwaysRun = true)
    public void login(){
        app.getUserHelper().popUpClose();
        app.getNavigationHelper().goLoginPage();
        app.getUserHelper().loginAs(app.getUser());
        Assert.assertTrue(app.getUserHelper().verifyAuth());
    }

    @AfterMethod(alwaysRun = true)
    public void logOut(){
        app.getUserHelper().logOutFromAccount();
        Assert.assertTrue(app.getUserHelper().verifyLogOut());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.getDriver().quit();
        makeAllureReport();
        copyResultAndHistoryDirs();
    }

    @BeforeGroups(groups = {"fullGroup"})
    public void informActionFullSuite(){
        System.out.println("Full Group Run!");
    }

    @BeforeGroups(groups = {"debugGroup"})
    public void informActionDebugSuite(){
        System.out.println("Debug Group Run!");
    }

}
