package Managers;


public class CourseHelper extends PageManager {

    protected CourseHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void searchCourses(String searchText){
        allCoursesPage
                .searchSmth(searchText)
                .waitSearchRes(searchText);
    }

    public void openCourseSubMenu(){
        allCoursesPage.openFirstCourseSubMenu();
    }

    public boolean findLearnMoreBtn(){
        return allCoursesPage.findLearnMoreBtn();
    }

}
