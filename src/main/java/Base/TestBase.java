package Base;

import Managers.AppManager;
import Managers.SingletonAppManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static Utils.AllureEnvironmentWriter.setAllureEnvironment;
import static Utils.LogsWriter.writeBrowserLog;

//import static Utils.CopyDirectories.*;

public class TestBase{
    public AppManager app = SingletonAppManager.getInstance().manager;

    @BeforeSuite(alwaysRun = true)
    public void  writeAllureEnvironment(){
        setAllureEnvironment(app.getDriver());
    }

    @BeforeMethod(alwaysRun = true, description = "Login")
    public void login(){
        app.getUserHelper().popUpClose();
        app.getUserHelper().closeTopAndBottomBaners();
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
        writeBrowserLog(app.getDriver());
        app.getDriver().quit();
// ==================|For local use|====================
//        makeAllureReport();
//        copyResultsDir();
//        copyHistoryDir();
    }

}
