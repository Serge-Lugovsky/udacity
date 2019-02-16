package TestGithubAPI;

import Utils.PropertyLoader;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MyActivity {
    private String userName = PropertyLoader.loadProperty("GIT_HUB_USER_NAME");
    private String password = PropertyLoader.loadProperty("GIT_HUB_PASSWORD");
    private GithubClientAPI loginService = RetrofitService.createService(GithubClientAPI.class, userName, password);
    private static GitHubRepositoryModel savedRepo;

    protected GitHubRepositoryModel getSavedRepo(){
        return savedRepo;
    }

    @Step("Get repository {repo.name}")
    protected GitHubRepositoryModel getRepo(GitHubRepositoryModel repo){
        Response<GitHubRepositoryModel> getRepoCall = null;

        try {
            getRepoCall = loginService.getRepository(userName, repo.getName()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getResponceStatus(getRepoCall);
        return getRepoCall.body();
    }

    @Step("Check repository {repo.name} is deleted?")
    protected boolean verifyDeleteRepo(GitHubRepositoryModel repo){
        return getRepo(repo) == null;
    }

    @Step("Create repository {repo.name}")
    protected void createRepo(GitHubRepositoryModel repo) {
        Response<GitHubRepositoryModel> createRepoCall = null;
        try {
            createRepoCall = loginService.createRepository(repo).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getResponceStatus(createRepoCall);
        savedRepo = createRepoCall.body();
    }

    @Step("Get list of all repositories")
    protected void getListRepo(){
        Response<List<GitHubRepositoryModel>> repositoriesListCall = null;
        try {
            repositoriesListCall = loginService.getListRepositories(userName).execute();
            if (repositoriesListCall.body() != null) {
                repositoriesListCall.body().forEach(change -> System.out.println(change.getName()+" "+change.getId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        getResponceStatus(repositoriesListCall);
    }

    @Step("Delete repository {repo.name}")
    protected void destroyRepo(GitHubRepositoryModel repo){
        Response<GitHubRepositoryModel> deleteRepoCall = null;
        try {
            deleteRepoCall = loginService.deleteRepository(userName, repo.getName()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getResponceStatus(deleteRepoCall);
    }

    @Attachment(value = "Response data :", type = "application/json", fileExtension = ".yml")
    private static String getResponceStatus(Object object){
        return "\n=============| Response |=============:\n" + object;
    }
}
