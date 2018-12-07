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
       allCoursesPage.getCourseLinkText();
    }

    public String firstCourseLinkText(){
        return allCoursesPage.getTextFirstCourseLink();
    }

    public String getCourseH1Text(){
        return coursePage.getTextH1OnFirstElemPage();
    }
}
