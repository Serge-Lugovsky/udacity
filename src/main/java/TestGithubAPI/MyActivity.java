package TestGithubAPI;

import Utils.PropertyLoader;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MyActivity {
    private String userName = PropertyLoader.loadProperty("GIT_HUB_USER_NAME");
    private String password = PropertyLoader.loadProperty("GIT_HUB_PASSWORD");
    private GithubClientAPI loginService = RetrofitService.createService(GithubClientAPI.class, userName, password);

    @Step("Get repository {repo.name}")
    protected GitHubRepositoryModel getRepo(GitHubRepositoryModel repo){
        Response<GitHubRepositoryModel> getRepoCall = null;
        try {
            getRepoCall = loginService.getRepository(userName, repo.getName()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getResponceStatus(getRepoCall);

        if (getRepoCall.isSuccessful()){
            return getRepoCall.body();
        }

        return null;
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

        if (createRepoCall.isSuccessful()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Step("Get list of all repositories")
    protected List<String> getListRepo(){
        Response <List<GitHubRepositoryModel>> getRepoListCall = null;
        List<String> allRepos;
        try {
            getRepoListCall = loginService.getListRepositories().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getResponceStatus(getRepoListCall);

        if (getRepoListCall.isSuccessful()){
            allRepos = getRepoListCall.body().stream()
                                .map(GitHubRepositoryModel::getName)
                                .collect(Collectors.toList());
            System.out.println(allRepos);
            return allRepos;
        }

        return null;
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

        if (deleteRepoCall.isSuccessful()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Attachment(value = "Response data :", type = "application/json", fileExtension = ".yml")
    private static String getResponceStatus(Object object){
        return "\n=============| Response |=============:\n" + object;
    }
}
