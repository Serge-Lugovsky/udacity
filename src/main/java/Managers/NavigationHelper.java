package Managers;

public class NavigationHelper extends PageManager {

    NavigationHelper(AppManager manager){
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

    public void goLinkPage(String linkName){
        allCoursesPage.goToNavbarLinkPage(linkName);
    }

}
