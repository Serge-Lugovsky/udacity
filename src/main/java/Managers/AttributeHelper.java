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

    public void getFirstCourseLinkText(){
       allCoursesPage.saveCourseLinkText();
    }

    public String firstCourseLinkText(){
        return allCoursesPage.getCourseLinkText();
    }

    public String getCourseH1Text(){
        return coursePage.getTextH1OnFirstElemPage();
    }

    public void waitForElements(){
        allCoursesPage.getForAllElem();
    }

    public String getSearchResult(){
        return allCoursesPage.getValueOfSearch();
    }

}
