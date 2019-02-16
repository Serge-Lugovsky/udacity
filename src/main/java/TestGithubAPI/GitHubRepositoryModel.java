package TestGithubAPI;

import com.google.gson.annotations.SerializedName;

public class GitHubRepositoryModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String desc;

    public GitHubRepositoryModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc(){
        return desc;
    }
}