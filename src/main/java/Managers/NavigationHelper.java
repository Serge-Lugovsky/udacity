package Managers;

public class NavigationHelper extends PageManager {

    public NavigationHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void goLoginPage(){
        homePage
            .goToLoginPage();
    }

    public void moveToUserInfo(){
        accountPage
                .clickSettingsButton()
                .clickPersonalInfoLink();
    }

    public void goAllCoursesPage(){
        accountPage
                .goToCatalog();
    }

    public void goFirstCoursePage(){
        allCoursesPage
                .goFirstCourseLink();
    }

}
