import TestGithubAPI.GitHubRepositoryModel;
import TestGithubAPI.MyActivity;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GithubCreateDeleteRepositoryTest extends MyActivity {
    private GitHubRepositoryModel repo = new GitHubRepositoryModel("New-new-repo", "Test repository");

    @Test(description = "Create repository ", groups = {"createRepoGroup"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create repository on github.")
    public void createRepository(){
        createRepo(repo);
        Assert.assertEquals(getRepo(repo).getName(), getSavedRepo().getName(), "Failed create repository");
    }

    @Test(description = "Delete repositoty", dependsOnGroups = {"createRepoGroup"} )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete repository on github.")
    public void deleteRepository(){
        destroyRepo(repo);
        Assert.assertTrue(verifyDeleteRepo(repo), "Failed delete repository");
    }
}
