package TestGithubAPI;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface GithubClientAPI {

    @GET("users/{user}/repos")
    Call<List<GitHubRepositoryModel>> getListRepositories(
            @Path("user") String user
    );

    @POST("user/repos")
    Call<GitHubRepositoryModel> createRepository(
            @Body GitHubRepositoryModel repo
    );

    @DELETE("repos/{user}/{repo}")
    Call<GitHubRepositoryModel> deleteRepository(
            @Path("user") String user, @Path("repo") String repo
    );

    @GET("repos/{user}/{repo}")
    Call<GitHubRepositoryModel> getRepository(
            @Path("user") String user, @Path("repo") String repo
    );
}