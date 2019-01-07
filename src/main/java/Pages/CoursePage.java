package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage extends Page {

    @FindBy(xpath = "//h1")
    private WebElement courseH1Text;

    public CoursePage(PageManager pages){
        super(pages);
    }

    @Step("Get H1 text on course page")
    public String getTextH1OnFirstElemPage(){
        return courseH1Text.getText();
    }
}
