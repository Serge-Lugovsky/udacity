package Managers;

public class CourseHelper extends PageManager {

    public CourseHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void searchCourses(){
        allCoursesPage.searchSmth("Android");
    }

    public void openCourseSubMenu(){
        allCoursesPage.openFirstCourseSubMenu();
    }

    public boolean findLearnMoreBtn(){
        return allCoursesPage.findLearnMoreBtn();
    }

}
