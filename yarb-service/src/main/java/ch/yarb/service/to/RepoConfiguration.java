package ch.yarb.service.to;


public class RepoConfiguration {

  private final String repoUrl;
  private final String userName;
  private final String password;

  public RepoConfiguration(String repoUrl, String userName, String password) {
    this.repoUrl = repoUrl;
    this.userName = userName;
    this.password = password;
  }

  public String getRepoUrl() {
    return this.repoUrl;
  }

  public String getUserName() {
    return this.userName;
  }

  public String getPassword() {
    return this.password;
  }

}
